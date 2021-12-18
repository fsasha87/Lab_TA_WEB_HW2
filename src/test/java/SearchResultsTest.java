import model.RozetkaFilter;
import model.RozetkaFilters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BucketPage;
import pages.MainPage;
import pages.SearchResultsPage;
import utils.JaxbReader;

import java.util.List;

public class SearchResultsTest extends BaseTest {

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


    @Test(dataProvider = "dP1", enabled = true)
    public void verifyPriceOfSearchedCommodity(String thing, String brand, int amount) throws InterruptedException {
        new MainPage()
                .typeSearchField(thing)
                .clickSearchField();
        new SearchResultsPage()
                .enterBrandField(brand)
                .clickCheckbox()
                .selectSortOption()
                .clickFirstElementButton();
        new BucketPage()
                .openBucket()
                .verifyAmountWithSoftAssert(amount)
                .verifyAmount(amount);
    }


    @Test(enabled = true)
    @Parameters({"Thing", "Brand", "Amount"})
    public void verifyPriceOfSearchedCommodityWithParams(String thing, String brand, int amount) throws InterruptedException {
        new MainPage()
                .typeSearchField(thing)
                .clickSearchField();
        new SearchResultsPage()
                .enterBrandField(brand)
                .clickCheckbox()
                .selectSortOption()
                .clickFirstElementButton();
        new BucketPage()
                .openBucket()
                .verifyAmountWithSoftAssert(amount)
                .verifyAmount(amount);
    }

}
