import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesReader;
import utils.WebDriverSingleton;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.WebDriverSingleton.getDriver;

public abstract class BaseTest {
    private static final Logger LOG = Logger.getLogger(String.valueOf(BaseTest.class));

    @BeforeMethod
    public void setUp() {
        getDriver().get(new PropertiesReader().getUrl());
    }

    @AfterMethod(enabled = true, alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            printScreenshot();
            WebDriverSingleton.close();
        } else {
            WebDriverSingleton.close();
            LOG.info("WebDriver is closed");
        }
    }

    public void printScreenshot() {
        Date dateNow = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");
        String fileName = format1.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("D:\\Screenshots\\" + fileName));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
