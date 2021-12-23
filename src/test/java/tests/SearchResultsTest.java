package tests;

import dataProvider.DataProviderData;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.businessobject.SearchResultsPageBO;
import pages.businessobject.Verifier;

//@Listeners(TestNGListener.class)
public class SearchResultsTest extends BaseTest {

    @Test(description = "Verify that price is less than amount (data from xml)",
            dataProvider = "dP1", dataProviderClass = DataProviderData.class, enabled = true)
    public void verifyPriceOfSearchedCommodity(String thing, String brand, int amount) {
        new SearchResultsPageBO()
                .typeCategory(thing)
                .selectGoodByBrand(brand);
        new Verifier()
                .verifyAmountWithSoftAssert(amount)
                .verifyAmount(amount);
    }

    @Test(description = "Verify that price is less than amount (data from parameters)",
            enabled = true)
    @Parameters({"Thing", "Brand", "Amount"})
    public void verifyPriceOfSearchedCommodityWithParams(String thing, String brand, int amount) {
        new SearchResultsPageBO()
                .typeCategory(thing)
                .selectGoodByBrand(brand);
        new Verifier()
                .verifyAmountWithSoftAssert(amount)
                .verifyAmount(amount);
    }
}
