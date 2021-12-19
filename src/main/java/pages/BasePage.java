package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.PropertiesReader;

import static utils.WebDriverSingleton.getDriver;

public abstract class BasePage {
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor jse;
    SoftAssert softAssert;

    {
        wait = new WebDriverWait(getDriver(), PropertiesReader.getExplicityWaitValue());
        actions = new Actions(getDriver());
        jse = (JavascriptExecutor) getDriver();
        softAssert = new SoftAssert();
    }

    public void waitPresenceOfElementLocated(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void moveToElement(By locator) {
        actions.moveToElement(getDriver().findElement(locator));
    }

    public void clickWithJavaScript(By locator) {
        jse.executeScript("arguments[0].click()", getDriver().findElement(locator));
    }

    public void waitUntillPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

}



