package customDiff.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import customDiff.CustomDiff;

import java.io.File;


public class FeatureModelParser {
	
	String fmContent;
	
 public static String getFMRootElement(){

		try{
			File fXmlFile = new File(CustomDiff.featureModelPath);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	    	String root = doc.getDocumentElement().getNodeName();
	    	System.out.println("FM root:"+root);
	    	return root;
     } catch (Exception e) {
 	e.printStackTrace();
     }
			return "";  
 }

	public static String getParentFeatureOfFeatureByName(String featureChildName){
		String parentFeature="";
		try{
			File fXmlFile = new File(CustomDiff.featureModelPath);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	
	    	System.out.println("Finding parent for feature-->" +featureChildName);
	
	    	NodeList nList = doc.getElementsByTagName("cm:element");
	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    		Node nNode = nList.item(temp);
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    			Element eElement = (Element) nNode;
	    			//System.out.println("Element id : " + eElement.getAttribute("cm:id"));
	    			//System.out.println("Element cm:name : " + eElement.getAttribute("cm:name"));
	    			if(eElement.getAttribute("cm:name").equals(featureChildName)){
	    			   for(int i=0; i<eElement.getElementsByTagName("cm:relation").getLength();i++){
	    				   Node node = eElement.getElementsByTagName("cm:relation").item(i);
	    				   NamedNodeMap attributes = node.getAttributes();
	    				   //System.out.println("attributes.getNamedItem:"+  attributes.getNamedItem("cm:type").toString());
	    				   if( attributes.getNamedItem("cm:type").toString().equals("cm:type=\"ps:parent\"")){
	    					   String parentId=eElement.getElementsByTagName("cm:relation").item(i).getTextContent();
	    					   System.out.println("parentId: "+parentId);
	    					   parentId=parentId.split("./")[1].trim();
	    	    			   parentFeature = getFeatureNameByID(parentId);
	    				   }

	    			   }
	    			   System.out.println("Parent-->" +parentFeature);
	    			   return parentFeature;
	    			}
	    			
	    		}
	    	}
	    	System.out.println("No Parent fount-->"+parentFeature);
        } catch (Exception e) {
    	e.printStackTrace();
        }
    return parentFeature;  
	}

	private static String getFeatureNameByID(String parentId) {
		String featureName="";
		
		try {

		    	File fXmlFile = new File(CustomDiff.featureModelPath);
		    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    	Document doc = dBuilder.parse(fXmlFile);

		    	//optional, but recommended
		    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		    	doc.getDocumentElement().normalize();
		     //	System.out.println("----------------------------");
		    //	System.out.println("Searching for:"+parentId);
		    	NodeList nList = doc.getElementsByTagName("cm:element");
		    	for (int temp = 0; temp < nList.getLength(); temp++){
		    		Node nNode = nList.item(temp);
		    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    			Element eElement = (Element) nNode;
		    		//	System.out.println("Node id:" + eElement.getAttribute("cm:id"));
		    		//	System.out.println("Node name:" + eElement.getAttribute("cm:name"));
		    		//	System.out.println("Equals?  "+(eElement.getAttribute("cm:id")).equals(parentId));
		    			if( parentId.trim().equals(eElement.getAttribute("cm:id").trim())){
		    				
		    				featureName=eElement.getAttribute("cm:name").trim();
		    				return featureName;
		    			}
		    				

		    		}
		    	}
		        } catch (Exception e) {
		    	e.printStackTrace();
		}
		System.out.println("No parent found idparent:" +parentId);
		return featureName;
	}
	
}
