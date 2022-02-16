package tests;

import dataProvider.DataProviderData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.businessobject.SearchResultsPageBO;
import pages.businessobject.Verifier;
import utils.TestNGListener;


@Epic("smoke tests")
@Feature("Search-results-page tests")
@Listeners(TestNGListener.class)
public class SearchResultsTest extends BaseTest {

    @Test(description = "Verify that price is less than amount (data from xml)",
            dataProvider = "dP1", dataProviderClass = DataProviderData.class, enabled = true)
    public void verifyPriceOfSearchedCommodityDP(String thing, String brand, int amount) {
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

    @Test(description = "Verify that price is less than amount. Positive test",
            enabled = true)
    @Description("Some description text in the below of page")
    public void verifyPriceOfSearchedCommodity() {
        new SearchResultsPageBO()
                .typeCategory("Laptop")
                .selectGoodByBrand("Lenovo");
        new Verifier()
                .verifyAmountWithSoftAssert(50000)
                .verifyAmount(50000);
    }

    @Test(description = "Verify that price is less than amount Negative test",
            enabled = true)
    public void verifyPriceOfSearchedIncorrectCommodity() {
        new SearchResultsPageBO()
                .typeCategory("dflsdfsd")
                .selectGoodByBrand("Lenovo");
        new Verifier()
                .verifyAmountWithSoftAssert(50000)
                .verifyAmount(50000);
    }

}
