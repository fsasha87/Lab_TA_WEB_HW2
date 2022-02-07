package pages;

import decorator.Button;
import decorator.CheckBox;
import decorator.TextInput;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(String.valueOf(SearchResultsPage.class));

    By brandField = By.xpath("(//input[@name='searchline'])[1]");
    TextInput brandFieldTextInput = new TextInput(getElement(brandField));
    By sortButton = By.cssSelector("select");
    By selectOption = By.cssSelector("option[value='5: action']");
    By firstElementBucket = By.xpath("(//div[@class='goods-tile__prices']//button)[1]");
    String brandCheckbox = "//a[@data-id='%s']";

    public SearchResultsPage enterBrandFieldAndClickCheckbox(String brand) {
        brandFieldTextInput.waitPresenceOfElementLocated(brandField);
        brandFieldTextInput.moveToElement(brandField);
        brandFieldTextInput.sendKeysInEmptyField(brand + Keys.RETURN);
        LOG.info(String.format("Brand '%s' was selected.", brand));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement brandCheckBox = getElement(By.xpath(String.format(brandCheckbox, brand)));
        String str = brandCheckBox.getAttribute("href");
        String[] strings = str.split("=");
        LOG.info(String.format("Checkbox '%s' was selected.", strings[strings.length - 1]));
        return this;
    }

    public SearchResultsPage selectSortOption() {
        scrollToElement(sortButton);
//        new Button(getElement(sortButton)).clickWithFluentWaiter();
        new Button(getElement(sortButton)).clickWithJavaScript();
        new Button(getElement(selectOption)).clickWithJavaScript();
        LOG.info("Sort option was selected.");
        return this;
    }

    public SearchResultsPage clickFirstElementButton() {
        new Button(getElement(firstElementBucket)).safeClick();
        LOG.info("First element's bucket is clicked.");
        return this;
    }

}
