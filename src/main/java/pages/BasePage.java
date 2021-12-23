package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;

import java.util.List;
import java.util.NoSuchElementException;

import static utils.WebDriverSingleton.getDriver;

public abstract class BasePage {
    WebDriverWait wait;
    Actions actions;

    {
        wait = new WebDriverWait(getDriver(), PropertiesReader.getExplicityWaitValue());
        actions = new Actions(getDriver());
    }

    public WebElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return getDriver().findElements(locator);
    }

    public void scrollToElement(By locator) {
        actions.moveToElement(getDriver().findElement(locator));
    }

    public void waitVisibilityIgnorExc(By locator) {
        wait.ignoring(NumberFormatException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isAnyElementFound(List<WebElement> elements, By by) throws InterruptedException {
        boolean flag = false;
        if (elements.size() > 0)
            flag = true;
        else {
            for (int i = 0; (i < PropertiesReader.getExplicityWaitValue()); i++) {
                Thread.sleep(1000);
                elements = getDriver().findElements(by);
            }
        }
        return flag;
    }

    public boolean isDisplayed(By locator) {
        try {
            return getElement(locator)
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitUntillPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

}



