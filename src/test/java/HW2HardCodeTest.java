import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HW2HardCodeTest {
    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.webDriver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
//        System.setProperty("webdriver.gecko.webDriver", "src/main/resources/geckodriver.exe");
//        WebDriver webDriver = new FirefoxDriver();


        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://rozetka.com.ua/");
        WebDriverWait wait = (new WebDriverWait(webDriver,20));//создали некий элемент ожидания
        Actions actions = new Actions(webDriver);
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;

        By sortButton = By.xpath("//select");
        By selectOption = By.cssSelector("option[value='5: action']");
        By homeButton = By.xpath("(//a[@class='breadcrumbs__link'])[1]");

        WebElement categoryElectronics = webDriver.findElement(By.xpath("(//main-page-sidebar//a[@class='menu-categories__link'])[2]"));
        categoryElectronics.click();
        WebElement tvButton  = webDriver.findElement(By.cssSelector("a[title='Телевизоры']"));
        tvButton.click();
        WebElement checkboxLG = webDriver.findElement(By.xpath("//*[@id='LG']/parent::a"));
        checkboxLG.click();
        webDriver.findElement(sortButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(selectOption));
        webDriver.findElement(selectOption).click();
        By firstElementBucket = By.xpath("(//div[@class='goods-tile__prices']//button)[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(firstElementBucket));
        jse.executeScript("arguments[0].click()", webDriver.findElement(firstElementBucket));

        actions.moveToElement(webDriver.findElement(homeButton)).build().perform();
        jse.executeScript("arguments[0].click()", webDriver.findElement(homeButton));
        WebElement categoryBT = webDriver.findElement(By.xpath("(//main-page-sidebar//a[@class='menu-categories__link'])[4]"));
        jse.executeScript("arguments[0].click()", categoryBT);
        WebElement vacuumsButton = webDriver.findElement(By.cssSelector("a[title='Пылесосы']"));
        vacuumsButton.click();
        By checkBoxKarcher = By.xpath("//*[@id='Karcher']/parent::a");
        wait.until(ExpectedConditions.elementToBeClickable(checkBoxKarcher));
        webDriver.findElement(checkBoxKarcher).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(sortButton));
        webDriver.findElement(sortButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectOption));
        jse.executeScript("arguments[0].click()", webDriver.findElement(selectOption));
        jse.executeScript("arguments[0].click()", webDriver.findElement(firstElementBucket));

        actions.moveToElement(webDriver.findElement(homeButton)).build().perform();
        webDriver.findElement(homeButton).click();
        WebElement categoryAutoGoods = webDriver.findElement(By.xpath("(//main-page-sidebar//a[@class='menu-categories__link'])[6]"));
        categoryAutoGoods.click();
        WebElement autoRegistersButton = webDriver.findElement(By.cssSelector("a[title='Видеорегистраторы']"));
        autoRegistersButton.click();
        WebElement checkboxGazer = webDriver.findElement(By.xpath("//*[@id='Gazer']/parent::a"));
        wait.until(ExpectedConditions.elementToBeClickable(checkboxGazer));
        checkboxGazer.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(sortButton));
        webDriver.findElement(sortButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectOption));
        webDriver.findElement(selectOption).click();
        jse.executeScript("arguments[0].click()", webDriver.findElement(firstElementBucket));

        WebElement mainBucket = webDriver.findElement(By.xpath("//li[contains(@class, 'header-actions__item--cart')]//button"));
        mainBucket.click();
        Thread.sleep(5000);
        List<WebElement> itemsInBucket = webDriver.findElements(By.cssSelector("a.cart-product__title"));
        wait.until(ExpectedConditions.visibilityOfAllElements(itemsInBucket));
        Assert.assertEquals(itemsInBucket.size(), 3, "There not 3 items in bucket");
        WebElement totalSumInBucket = webDriver.findElement(By.cssSelector("div.cart-receipt__sum-price span"));
        String s = totalSumInBucket.getText();
        Assert.assertTrue(Integer.parseInt(s)<100000, "Sum is not enough. Work more.");

        webDriver.quit();
    }
}

