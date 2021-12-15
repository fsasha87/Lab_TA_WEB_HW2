package utils;

import model.RozetkaFilter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbReader {
    public RozetkaFilter convert() {
        RozetkaFilter rozetkaFilter = null;
        try {
            File file = new File("src/main/resources/rozetkaFilter.xml");
            JAXBContext context = JAXBContext.newInstance(RozetkaFilter.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            rozetkaFilter = (RozetkaFilter) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return rozetkaFilter;
    }
}
