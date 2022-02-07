package dataProvider;

import model.RozetkaFilter;
import model.RozetkaFilters;
import org.testng.annotations.DataProvider;
import utils.JaxbReader;

import java.util.List;

public class DataProviderData {

    @DataProvider(name = "dP1", parallel = true)
    public Object[][] dataProviderMethod() {
        JaxbReader jaxbReader = new JaxbReader();
        RozetkaFilters rozetkaFilters = jaxbReader.convert();
        List<RozetkaFilter> rozetkaFilterList = rozetkaFilters.getRozetkaFilters();
        return rozetkaFilterList.stream().map(i -> new Object[]{i.getThing(), i.getBrand(), i.getAmount()}).toArray(Object[][]::new);
    }
}