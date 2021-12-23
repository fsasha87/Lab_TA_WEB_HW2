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
    By brandCheckbox = By.xpath("//div[@data-filter-name='producer']//*[@type='checkbox']/parent::a");
    By sortButton = By.cssSelector("select");
    By selectOption = By.cssSelector("option[value='5: action']");
    By firstElementBucket = By.xpath("(//div[@class='goods-tile__prices']//button)[1]");

    public SearchResultsPage enterBrandField(String brand) {
        brandFieldTextInput.waitPresenceOfElementLocated(brandField);
        brandFieldTextInput.moveToElement(brandField);
        brandFieldTextInput.sendKeysInEmptyField(brand + Keys.RETURN);
        LOG.info(String.format("Brand '%s' was selected.", brand));
        return this;
    }

    public SearchResultsPage clickCheckbox() {
        new CheckBox(getElement(brandCheckbox)).waitTillOneElementIsDisplayes(brandCheckbox);
        List<WebElement> listCheckbox = getElements(brandCheckbox);
        new CheckBox(listCheckbox.get(0)).setCheckBox(true);
        String str = listCheckbox.get(0).getAttribute("href");
        String[] strings = str.split("=");
        LOG.info(String.format("Checkbox '%s' was selected.", strings[strings.length - 1]));
        return this;
    }

    public SearchResultsPage selectSortOption() {
        scrollToElement(sortButton);
        new Button(getElement(sortButton)).clickWithFluentWaiter();
//        new Button(getElement(sortButton)).clickWithJavaScript();
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
