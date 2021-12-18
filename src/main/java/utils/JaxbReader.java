package utils;

import model.RozetkaFilter;
import model.RozetkaFilters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbReader {
    public RozetkaFilters convert() {
        RozetkaFilters rozetkaFilters = null;
        try {
            File file = new File("src/main/resources/rozetkaFilters.xml");
            JAXBContext context = JAXBContext.newInstance(RozetkaFilters.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            rozetkaFilters = (RozetkaFilters) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return rozetkaFilters;
    }
}
