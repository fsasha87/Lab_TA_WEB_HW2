package pages.businessobject;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.BucketPage;

public class Verifier {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Verifier.class));
    BucketPage bucketPage = new BucketPage();

    public Verifier verifyAmount(int amount) {
        int price = bucketPage.getPriceOfGood();
        LOG.info(String.format("Enough money to pay. Price %d is less than %d", price, amount));
        Assert.assertTrue(price < amount, "Sum is not enough. Work more.");
        return this;
    }

    public Verifier verifyAmountWithSoftAssert(int amount) {
        SoftAssert softAssert = new SoftAssert();
        int price = bucketPage.getPriceOfGood();
        LOG.info(String.format("Enough money to pay. Price %d is less than %d.  Checked with SoftAssert", price, amount));
        softAssert.assertTrue(price < amount, "Sum is not enough. Work more. Checked with SoftAssert");
        softAssert.assertAll();
        return this;
    }
}
