package Class2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class Homework15 {

    @Test
    public void closeAdvertisement() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.naukri.com");

        Set<String> allHandles = driver.getWindowHandles();
        String lpageHandle = driver.getWindowHandle();
        for(String handle: allHandles) {
            if(!handle.equalsIgnoreCase(lpageHandle)) {
                driver.switchTo().window(handle);
                driver.close();
                break;
            }
        }

        String text = "testing tools";
        driver.findElement(By.id("qsb-keyword-sugg")).clear();
        driver.findElement(By.id("qsb-keyword-sugg")).sendKeys("testing");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='suggestor-location']")));
        List<WebElement> suggestion = driver.findElements(By.xpath("//div[@class='suggestor-location']"));

        for(WebElement suggest: suggestion) {
            String suggestedText = suggest.getText();
            if(text.equalsIgnoreCase(suggestedText)) {
                suggest.click();
                break;
            }
        }

        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Testing Tools Jobs']")).isDisplayed(), "Else this msg is not displayed");

        driver.quit();

    }
}
