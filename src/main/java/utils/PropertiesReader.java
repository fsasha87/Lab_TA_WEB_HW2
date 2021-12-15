package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    Properties property = new Properties();

    public PropertiesReader() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fis);
        } catch (IOException e) {
            System.err.println("Properties file does not exist");
        }
    }

    public String getUrl() {
        String URL = property.getProperty("URL");
        return URL;
    }

    public String getDriverName() {
        String driverName = property.getProperty("CHROME_DRIVER_NAME");
        return driverName;
    }

    public String getDriverLocation() {
        String driverLocation = property.getProperty("CHROME_DRIVER_LOCATION");
        return driverLocation;
    }
}
