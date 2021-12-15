import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;
import utils.PropertiesReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(new PropertiesReader().getUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            printScreenshot();
            DriverFactory.getDriver().quit();
        } else {
            DriverFactory.getDriver().quit();
        }
    }

    public void printScreenshot() {
        Date dateNow = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = format1.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("D:\\Screenshots\\" + fileName));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
