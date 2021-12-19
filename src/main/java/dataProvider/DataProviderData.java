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
        Object[][] table = new Object[rozetkaFilterList.size()][3];
        for (int i = 0; i < table.length; i++) {
            table[i][0] = rozetkaFilterList.get(i).getThing();
            table[i][1] = rozetkaFilterList.get(i).getBrand();
            table[i][2] = rozetkaFilterList.get(i).getAmount();
        }
        return table;
    }
}
