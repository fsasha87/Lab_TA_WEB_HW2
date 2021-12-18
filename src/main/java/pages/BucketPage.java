package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static utils.WebDriverSingleton.getDriver;

public class BucketPage extends BasePage {
    private static final Logger LOG = Logger.getLogger(String.valueOf(BucketPage.class));

    By mainBucket = By.xpath("//li[contains(@class, 'header-actions__item--cart')]//button");
    By priceAmount = By.cssSelector("div.cart-receipt__sum-price span:nth-child(1)");

    public BucketPage openBucket() {
        getDriver().findElement(mainBucket).click();
        LOG.info("Bucket window is opened.");
        return this;
    }

    public BucketPage verifyAmount(int amount) {
        String s = getDriver().findElement(priceAmount).getText();
        int price = Integer.parseInt(s);
        LOG.info(String.format("Enough money to pay. Price %d is less than %d", price, amount));
        Assert.assertTrue(price < amount, "Sum is not enough. Work more.");
        return this;
    }

    public BucketPage verifyAmountWithSoftAssert(int amount) {
        SoftAssert softAssert = new SoftAssert();
        String s = getDriver().findElement(priceAmount).getText();
        int price = Integer.parseInt(s);
        LOG.info(String.format("Enough money to pay. Price %d is less than %d.  Checked with SoftAssert", price, amount));
        softAssert.assertTrue(price < amount, "Sum is not enough. Work more. Checked with SoftAssert");
        softAssert.assertAll();
        return this;
    }
}
