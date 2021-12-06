import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HardCodeTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rozetka.com.ua/");
        WebDriverWait wait = (new WebDriverWait(driver,20));//создали некий элемент ожидания
        Actions actions = new Actions(driver);
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        By sortButton = By.xpath("//select");
        By selectOption = By.cssSelector("option[value='5: action']");
        By homeButton = By.xpath("(//a[@class='breadcrumbs__link'])[1]");

        WebElement categoryElectronics = driver.findElement(By.xpath("(//main-page-sidebar//a[@class='menu-categories__link'])[2]"));
        categoryElectronics.click();
        WebElement tvButton  = driver.findElement(By.cssSelector("a[title='Телевизоры']"));
        tvButton.click();
        WebElement checkboxLG = driver.findElement(By.xpath("//*[@id='LG']/parent::a"));
        checkboxLG.click();
        driver.findElement(sortButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(selectOption));
        driver.findElement(selectOption).click();
        By firstElementBucket = By.xpath("(//div[@class='goods-tile__prices']//button)[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(firstElementBucket));
        jse.executeScript("arguments[0].click()", driver.findElement(firstElementBucket));

        actions.moveToElement(driver.findElement(homeButton)).build().perform();
        jse.executeScript("arguments[0].click()", driver.findElement(homeButton));
        WebElement categoryBT = driver.findElement(By.xpath("(//main-page-sidebar//a[@class='menu-categories__link'])[4]"));
        jse.executeScript("arguments[0].click()", categoryBT);
        WebElement vacuumsButton = driver.findElement(By.cssSelector("a[title='Пылесосы']"));
        vacuumsButton.click();
        By checkBoxKarcher = By.xpath("//*[@id='Karcher']/parent::a");
        wait.until(ExpectedConditions.elementToBeClickable(checkBoxKarcher));
        driver.findElement(checkBoxKarcher).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(sortButton));
        driver.findElement(sortButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectOption));
        jse.executeScript("arguments[0].click()", driver.findElement(selectOption));
        jse.executeScript("arguments[0].click()", driver.findElement(firstElementBucket));

        actions.moveToElement(driver.findElement(homeButton)).build().perform();
        driver.findElement(homeButton).click();
        WebElement categoryAutoGoods = driver.findElement(By.xpath("(//main-page-sidebar//a[@class='menu-categories__link'])[6]"));
        categoryAutoGoods.click();
        WebElement autoRegistersButton = driver.findElement(By.cssSelector("a[title='Видеорегистраторы']"));
        autoRegistersButton.click();
        WebElement checkboxGazer = driver.findElement(By.xpath("//*[@id='Gazer']/parent::a"));
        wait.until(ExpectedConditions.elementToBeClickable(checkboxGazer));
        checkboxGazer.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(sortButton));
        driver.findElement(sortButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectOption));
        driver.findElement(selectOption).click();
        jse.executeScript("arguments[0].click()", driver.findElement(firstElementBucket));

        WebElement mainBucket = driver.findElement(By.xpath("//li[contains(@class, 'header-actions__item--cart')]//button"));
        mainBucket.click();
        Thread.sleep(5000);
        List<WebElement> itemsInBucket = driver.findElements(By.cssSelector("a.cart-product__title"));
        wait.until(ExpectedConditions.visibilityOfAllElements(itemsInBucket));
        Assert.assertEquals(itemsInBucket.size(), 3, "There not 3 items in bucket");
        WebElement totalSumInBucket = driver.findElement(By.cssSelector("div.cart-receipt__sum-price span"));
        String s = totalSumInBucket.getText();
        Assert.assertTrue(Integer.parseInt(s)<100000, "Sum is not enough. Work more.");

        driver.quit();
    }
}

