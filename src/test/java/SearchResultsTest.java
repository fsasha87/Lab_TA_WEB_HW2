import model.RozetkaFilter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BucketPage;
import pages.MainPage;
import pages.SearchResultsPage;
import utils.JaxbReader;

public class SearchResultsTest extends BaseTest {

    @DataProvider(name = "dP1")
    public Object[][] dataProviderMethod() {
        JaxbReader jaxbReader = new JaxbReader();
        RozetkaFilter rozetkaFilter = jaxbReader.convert();
        return new Object[][]{{rozetkaFilter.getThing(), rozetkaFilter.getBrand(), rozetkaFilter.getAmount()}};
    }

    @Test(dataProvider = "dP1")
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
                .verifyAmount(amount);
    }

}
