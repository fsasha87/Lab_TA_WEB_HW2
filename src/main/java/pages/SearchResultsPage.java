package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.WebDriverSingleton.getDriver;

public class SearchResultsPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(String.valueOf(SearchResultsPage.class));

    By brandField = By.xpath("(//input[@name='searchline'])[1]");
    By brandCheckbox = By.xpath("//div[@data-filter-name='producer']//*[@type='checkbox']/parent::a");
    By sortButton = By.cssSelector("select");
    By selectOption = By.cssSelector("option[value='5: action']");
    By firstElementBucket = By.xpath("(//div[@class='goods-tile__prices']//button)[1]");

    public SearchResultsPage enterBrandField(String brand) {
        waitPresenceOfElementLocated(brandField);
        getDriver().findElement(brandField).sendKeys(brand + Keys.RETURN);
        LOG.info(String.format("Brand '%s' was selected.", brand));
        return this;
    }

    public SearchResultsPage clickCheckbox() throws InterruptedException {

        Thread.sleep(5000);
        List<WebElement> listCheckbox = getDriver().findElements(brandCheckbox);
        waitUntillPageLoad();
        String str = listCheckbox.get(0).getAttribute("href");
        String[] strings = str.split("=");
        listCheckbox.get(0).click();
        LOG.info(String.format("Checkbox '%s' was selected.", strings[1]));
        return this;
    }

    public SearchResultsPage selectSortOption() {
        moveToElement(sortButton);
        clickWithJavaScript(sortButton);
        clickWithJavaScript(selectOption);
        LOG.info("Sort option was selected.");
        return this;
    }

    public SearchResultsPage clickFirstElementButton() {
        waitElementToBeClickable(firstElementBucket);
        clickWithJavaScript(firstElementBucket);
        LOG.info("First element's bucket is clicked.");
        return this;
    }


}
