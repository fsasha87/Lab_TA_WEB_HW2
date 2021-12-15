package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import static utils.DriverFactory.getDriver;

public class BucketPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(String.valueOf(BucketPage.class));

    By mainBucket = By.xpath("//li[contains(@class, 'header-actions__item--cart')]//button");
    By mainBucketAmount = By.cssSelector("div.cart-receipt__sum-price span");

    public BucketPage openBucket() {
        getDriver().findElement(mainBucket).click();
        LOG.info("Bucket window is opened.");
        return this;
    }

    public BucketPage verifyAmount(int amount) {
        String s = getDriver().findElement(mainBucketAmount).getText();
        LOG.info("Enough money to pay.");
        Assert.assertTrue(Integer.parseInt(s) < amount, "Sum is not enough. Work more.");
        return this;
    }
}
