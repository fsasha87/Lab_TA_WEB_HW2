package decorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static utils.WebDriverSingleton.getDriver;

public class Button extends ElementDecorator {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Button.class));
    WebElement element;

    public Button(WebElement element) {
        super(element);
        this.element = element;
    }

    public void safeClick() {
        try {
            super.click();
            LOG.info("Clicked by 'safeclick' on first time.");
        } catch (ElementClickInterceptedException e) {
            new WebDriverWait(getDriver(), PropertiesReader.getExplicityWaitValue()).until(ExpectedConditions.elementToBeClickable(element));
            super.click();
            LOG.info("Clicked by 'safeclick' on second time.");
        }
    }

    public void clickWithJavaScript() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click()", element);
    }

    public void clickWithFluentWaiter() {
        new FluentWait<>(getDriver())
                .withTimeout(PropertiesReader.getFluentWaitValue(), TimeUnit.SECONDS)
                .pollingEvery(PropertiesReader.getFluentPollValue(), TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        element.click();
    }

}
