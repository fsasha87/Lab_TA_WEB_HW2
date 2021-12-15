package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.DriverFactory.getDriver;

public abstract class BasePage {
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor jse;

    {
        wait = new WebDriverWait(getDriver(), 10);
        actions = new Actions(getDriver());
        jse = (JavascriptExecutor) getDriver();
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



