package decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;

import static utils.WebDriverSingleton.getDriver;

public class TextInput extends ElementDecorator {
    public TextInput(WebElement element) {
        super(element);
    }

    public void sendKeysInEmptyField(String word) {
        element.clear();
        element.sendKeys(word);
    }

    public void waitPresenceOfElementLocated(By locator) {
        new WebDriverWait(getDriver(), PropertiesReader.getExplicityWaitValue()).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void moveToElement(By locator) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(locator));
    }

}
