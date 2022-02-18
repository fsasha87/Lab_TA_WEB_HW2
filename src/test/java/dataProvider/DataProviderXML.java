package dataProvider;

import model.RozetkaFilter;
import model.RozetkaFilters;
import org.testng.annotations.DataProvider;
import utils.XMLToObject;

import java.util.List;

public class DataProviderXML {

    @DataProvider(name = "DpXML", parallel = true)
    public static Object[][] xmlDataRead() {
        XMLToObject xmlToObject = new XMLToObject();
        RozetkaFilters rozetkaFilters = xmlToObject.convert();
        List<RozetkaFilter> rozetkaFilterList = rozetkaFilters.getRozetkaFilters();
        return rozetkaFilterList.stream().map(i -> new Object[]{i.getThing(), i.getBrand(), i.getAmount()}).toArray(Object[][]::new);

//        Object[][] table = new Object[rozetkaFilterList.size()][3];
//        for (int i = 0; i < table.length; i++) {
//            table[i][0] = rozetkaFilterList.get(i).getThing();
//            table[i][1] = rozetkaFilterList.get(i).getBrand();
//            table[i][2] = rozetkaFilterList.get(i).getAmount();
//        }
//        return table;

    }
}
