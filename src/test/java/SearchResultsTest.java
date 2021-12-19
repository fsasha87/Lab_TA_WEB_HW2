import dataProvider.DataProviderData;
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

    @Test(dataProvider = "dP1", dataProviderClass = DataProviderData.class, enabled = true)
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
