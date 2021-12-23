package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.WebDriverSingleton.getDriver;

public class TestNGListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        printScreenshot();
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
