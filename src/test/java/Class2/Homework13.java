package Class2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework13 {
    /**
     * Testcase-1. User should be able to click on the notification
     * 1.Launch yahoo.com
     * 2.Move the mouse to bell icon
     * 3.click on the notification
     */
    @Test
    public void clickOnNotification() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.yahoo.com");
        try {
            Thread.sleep(4000);
        } catch(InterruptedException e) {

        }
        WebElement bellIcon = driver.findElement(By.id("ybarNotificationMenu"));
        Actions act = new Actions(driver);
        act.moveToElement(bellIcon).build().perform();
        WebElement notification = driver.findElement(By.className("yns-content"));
        act.moveToElement(notification).click(notification).build().perform();
    }

    /**
     * Testcase-2. User should get error on invalid date of birth
     * 1.Launch facebook.com
     * 2.Click on "Create new account" button
     * 3.Enter first name
     * 4.Enter last name
     * 5.abcd@test.com as email
     * 6.absdef as New password
     * 7.Enter date of birth
     * 8. Verify user see the error msg for gender.
     */
    @Test
    public void verifyErrorMsg() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com");
        driver.findElement(By.linkText("Create New Account")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@aria-label='First name']")).sendKeys("Nargis");
        driver.findElement(By.xpath("//input[@aria-label='Last name']")).sendKeys("Usman");
        driver.findElement(By.xpath("//input[@aria-label='Mobile number or email']")).sendKeys("iamgiza@mail.ru");
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys("iamgiza@mail.ru");
        driver.findElement(By.id("password_step_input")).sendKeys("123Nargis");

        WebElement monthElm = driver.findElement(By.id("month"));
        Select month = new Select(monthElm);
        month.selectByVisibleText("Jan");

        WebElement dayElm = driver.findElement(By.id("day"));
        Select day = new Select(dayElm);
        day.selectByValue("12");

        WebElement yearElm = driver.findElement(By.id("year"));
        Select year = new Select(yearElm);
        year.selectByVisibleText("1989");
        driver.findElement(By.name("websubmit")).click();
        WebElement df = driver.findElement(By.xpath("//div[text()='Please choose a gender. You can change who can see this later.']"));
        boolean isDisplayed = df.isDisplayed();
        Assert.assertTrue(isDisplayed, "Please choose a gender. You can change who can see this later.");
    }

}
