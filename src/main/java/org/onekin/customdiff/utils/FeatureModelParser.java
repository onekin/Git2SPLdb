package org.onekin.customdiff.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.CustomDiff;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import java.io.File;
import java.io.IOException;


public class FeatureModelParser {

    String fmContent;
    private static final Logger logger = LogManager.getLogger(FeatureModelParser.class);

    public static String getFMRootElement() {

        try {
            File fXmlFile = new File(CustomDiff.featureModelPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setAttribute(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            dbFactory.setAttribute(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            String root = doc.getDocumentElement().getNodeName();
            logger.info("FM root:" + root);
            return root;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            logger.error(e);
        }
        return "";
    }

    public static String getParentFeatureOfFeatureByName(String featureChildName) {
        String parentFeature = "";
        try {
            File fXmlFile = new File(CustomDiff.featureModelPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setAttribute(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            dbFactory.setAttribute(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            logger.info("Finding parent for feature-->" + featureChildName);

            NodeList nList = doc.getElementsByTagName("cm:element");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    logger.info("Element id : " + eElement.getAttribute("cm:id"));
                    logger.info("Element cm:name : " + eElement.getAttribute("cm:name"));
                    if (eElement.getAttribute("cm:name").equals(featureChildName)) {
                        for (int i = 0; i < eElement.getElementsByTagName("cm:relation").getLength(); i++) {
                            Node node = eElement.getElementsByTagName("cm:relation").item(i);
                            NamedNodeMap attributes = node.getAttributes();
                            logger.info("attributes.getNamedItem:" + attributes.getNamedItem("cm:type").toString());
                            if (attributes.getNamedItem("cm:type").toString().equals("cm:type=\"ps:parent\"")) {
                                String parentId = eElement.getElementsByTagName("cm:relation").item(i).getTextContent();
                                logger.info("parentId: " + parentId);
                                parentId = parentId.split("./")[1].trim();
                                parentFeature = getFeatureNameByID(parentId);
                            }

                        }
                        logger.info("Parent-->" + parentFeature);
                        return parentFeature;
                    }

                }
            }
            logger.info("No Parent fount-->" + parentFeature);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            logger.error(e);
        }
        return parentFeature;
    }

    private static String getFeatureNameByID(String parentId) {
        String featureName = "";

        try {

            File fXmlFile = new File(CustomDiff.featureModelPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setAttribute(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
            dbFactory.setAttribute(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            logger.info("----------------------------");
            logger.info("Searching for:" + parentId);
            NodeList nList = doc.getElementsByTagName("cm:element");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    logger.info("Node id:" + eElement.getAttribute("cm:id"));
                    logger.info("Node name:" + eElement.getAttribute("cm:name"));
                    logger.info("Equals?  " + (eElement.getAttribute("cm:id")).equals(parentId));
                    if (parentId.trim().equals(eElement.getAttribute("cm:id").trim())) {

                        featureName = eElement.getAttribute("cm:name").trim();
                        return featureName;
                    }


                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            logger.error(e);
        }
        logger.info("No parent found idparent:" + parentId);
        return featureName;
    }

}
