package decorator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;

import static utils.WebDriverSingleton.getDriver;

public class CheckBox extends ElementDecorator {
    private static final Logger LOG = Logger.getLogger(String.valueOf(CheckBox.class));

    public CheckBox(WebElement element) {
        super(element);
    }

    public void setCheckBox(boolean shouldBeClicked) {
        if ((element.isSelected()) && (!shouldBeClicked) | (!element.isSelected() | shouldBeClicked)) {
            element.click();
            LOG.info("Element is clicked using decorator'");
        } else {
            LOG.info(String.format("Element is already in state 'checked'" + shouldBeClicked));
        }
    }

    public void waitTillOneElementIsDisplayes(By by) {
        new WebDriverWait(getDriver(), PropertiesReader.getExplicityWaitValue())
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.numberOfElementsToBe(by, 1));
        LOG.info("One Checkbox is displayed");
    }

}
