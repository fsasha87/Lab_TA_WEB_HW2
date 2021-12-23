package tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesReader;
import utils.WebDriverSingleton;

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

    @BeforeMethod (alwaysRun = true)
    public void beforeTest(final ITestContext testContext) {
        LOG.info(String.format("Test Case started: %s",testContext.getName()));
    }
    @AfterMethod (alwaysRun = true)
    public void afterTest(final ITestContext testContext) {
        LOG.info(String.format("Test Case ended: %s",testContext.getName()));
    }
}
