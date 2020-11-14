package Class1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class LabClass {

    List<String> names = new ArrayList<>();
    @Test
    public void testOne() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "/../");
        WebDriver driver = new ChromeDriver();
//        WebDriver fFDriver = new FirefoxDriver();
//        WebDriver sDriver = new SafariDriver();
        String url = "https://www.amazon.com/";
        driver.get(url);
        driver.findElement(By.className("nav-line-1-container")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("ap_email")).clear();
        driver.findElement(By.id("ap_email")).sendKeys("techno@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.name("password")).sendKeys("dgadg273618!");
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(1000);
        boolean result = driver.findElement(By.className("a-alert-heading")).isDisplayed();
        Assert.assertTrue(result);
        driver.quit();
    }
}
