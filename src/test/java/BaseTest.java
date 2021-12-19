import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
        getDriver().get(PropertiesReader.getUrl());
    }

    @AfterMethod(enabled = true, alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.close();
    }
}
