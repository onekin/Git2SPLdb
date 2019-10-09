package customDiff;

import customDiff.utils.featuremodel.ConsulmodelType;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class FeatureModelParserTest {


    @Test
    public void featureModelReadTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("WebAnnotator.xfm").getFile());
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ConsulmodelType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ConsulmodelType consulmodelType = (ConsulmodelType) jaxbUnmarshaller.unmarshal(file);


            System.out.println(consulmodelType.getElements().getElement().get(0));
        } catch (
                JAXBException e) {
            e.printStackTrace();
        }
    }
}
