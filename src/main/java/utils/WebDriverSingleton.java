package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static final Logger LOG = Logger.getLogger(String.valueOf(WebDriverSingleton.class));
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private WebDriverSingleton(){
    }

    public static WebDriver getDriver() {
        if (webDriverThreadLocal.get() != null){
            return webDriverThreadLocal.get();
        }
        WebDriver instance;
        PropertiesReader propertiesReader = new PropertiesReader();
        System.setProperty(propertiesReader.getDriverName(), propertiesReader.getDriverLocation());
        instance = new ChromeDriver();
        instance.manage().window().maximize();
        instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverThreadLocal.set(instance);
        LOG.info("WebDriver is created");
        return webDriverThreadLocal.get();
    }

    public static void close() {
        try {
        if (webDriverThreadLocal != null) {
            webDriverThreadLocal.get().quit();
        }
        }catch (Exception e){
            System.err.println("ERROR: Cannot close the driver");
        }finally {
            webDriverThreadLocal.remove();
        }
    }

}
