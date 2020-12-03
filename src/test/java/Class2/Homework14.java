package Class2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Homework14 {

    @Test
    public void verifyLowHighTemp() throws InterruptedException {

        /**
         * Testcase-1. Verify the low/high temp on Today timeline
         * Launch the darksky.net
         * scroll to Today timeline
         * Click on Today
         * find the locator for both low/high temp
         * Verify if both low/high temp match
         */

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");

        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("scrollBy(0,700);");
        Thread.sleep(500);

        driver.findElement(By.xpath("//a[@data-day='0']")).click();
        WebElement wb = driver.findElement(By.xpath("//a[@data-day='0']//span[@class='minTemp']"));
        String minTemp = wb.getText();
        WebElement we = driver.findElement(By.xpath("//a[@data-day='0']//span[@class='minTemp']"));
        String maxTemp = we.getText();

        WebElement temp = driver.findElement(By.xpath("//div[@class='dayDetails revealed']//span[@class='highTemp swip']"));
        String highTemp = temp.getText();
        System.out.println(highTemp);
        int indexOfDegree = highTemp.indexOf(" ");
        System.out.println(indexOfDegree);
        String hTemp = highTemp.substring(0, indexOfDegree);
        System.out.println(hTemp);

        WebElement lwTemp = driver.findElement(By.xpath("//div[@class='dayDetails revealed']//span[@class='lowTemp swap']"));
        String lowTemp = lwTemp.getText();
        int indexOfDeg = lowTemp.indexOf(" ");
        String lTemp = lowTemp.substring(0, indexOfDeg);

        Assert.assertEquals(minTemp, hTemp, "The low temp does not match");
        //Assert.assertEquals(maxTemp, lTemp, "The high temp does not match");
    }

    @Test
    public void verifyNumberOfDates() {

        /**
         * Testcase-2. Verify the number of nights on black briefcase
         * checkin: tomorrow
         * checkout: 7 nights from tomorrow
         * 1.Launch the hotels.com
         * 2.Click on check in
         * 3.Click on todays date
         * 4.Click on check out
         * 5.Click on date after 7 days
         * 6.Using Date Class get date and using SimpleDateFormat format the date to get the checkIn
         * 7.Using the Calendar class add 7 days then using SimpleDateFormat format the date to get the checkOut
         * 8.Verify if the 'nights black briefcase' is calculating the nights correctly
         */

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com");

        driver.findElement(By.id("qf-0q-localised-check-in")).click();
        WebElement checkInElement = driver.findElement(By.xpath("//td[@data-date='2020-11-3']"));
        checkInElement.click();

        SimpleDateFormat format = new SimpleDateFormat("d");
        Date dt = new Date();
        String actualDate = format.format(dt);
        int checkIn = Integer.valueOf(actualDate);  //2

        driver.findElement(By.id("qf-0q-localised-check-out")).click();
        WebElement checkOutElement = driver.findElement(By.xpath("//td[@data-date='2020-11-10']"));
        checkOutElement.click();
        Calendar myCal = Calendar.getInstance();
        myCal.add(Calendar.DATE,7);

        Date date = myCal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("d");
        String actualCheckOutDate = df.format(date);
        int checkOut = Integer.valueOf(actualCheckOutDate);   //9


        WebElement nights = driver.findElement(By.xpath("//span[@class='widget-query-num-nights']"));
        String totalNights = nights.getText();
        int totalStay = Integer.valueOf(totalNights);
        int totalDays = checkOut-checkIn;   //7
        System.out.println(totalStay);
        System.out.println(totalDays);
        Assert.assertEquals(totalDays, totalStay, "The total in hotel stay nights is incorrect");

    }

    @Test
    public void verifyDetails() throws InterruptedException {

        /**
         * Testcase-3. Verify user details entered on Search page as on Homepage
         * Enter the user details as mentioned
         * Rooms-1
         * Adult-1
         * Children-1 (Ages:1-2)
         *
         * 1.Launch the hotels.com
         * 2.Enter all the details on the Homepage
         * 3.Verify entered details on the Search page
         */

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com");

        String text = "New York, New York, United States of America";
        driver.findElement(By.id("qf-0q-destination")).clear();
        driver.findElement(By.id("qf-0q-destination")).sendKeys("New");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> places = driver.findElements(By.xpath("//div[@class='autosuggest-category-result']"));

        for (WebElement place: places) {
            String destination = place.getText();
            if(text.equalsIgnoreCase(destination)) {
                place.click();
                break;
            }

        }
        driver.findElement(By.id("qf-0q-localised-check-in")).click();
        WebElement checkInElement = driver.findElement(By.xpath("//td[@data-date='2020-11-30']"));
        checkInElement.click();

        driver.findElement(By.id("qf-0q-localised-check-out")).click();
        WebElement checkOutElement = driver.findElement(By.xpath("//td[@data-date='2021-0-5']"));
        checkOutElement.click();

        WebElement roomsElm = driver.findElement(By.id("qf-0q-rooms"));
        Select rooms = new Select(roomsElm);
        rooms.selectByValue("1");

        WebElement adultsElm = driver.findElement(By.id("qf-0q-room-0-adults"));
        Select adults = new Select(adultsElm);
        adults.selectByIndex(1);

        WebElement childrenElm = driver.findElement(By.id("qf-0q-room-0-children"));
        Select children = new Select(childrenElm);
        children.selectByValue("2");

        WebElement age1Elm = driver.findElement(By.id("qf-0q-room-0-child-0-age"));
        Select age1 = new Select(age1Elm);
        age1.selectByIndex(3);

        WebElement age2Elm = driver.findElement(By.id("qf-0q-room-0-child-1-age"));
        Select age2 = new Select(age2Elm);
        age2.selectByIndex(2);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);

        WebElement roomElm = driver.findElement(By.id("q-rooms"));
        String roomsNum = roomsElm.getText();

        WebElement adultElm = driver.findElement(By.id("q-room-0-adults"));
        String adultNum = adultElm.getText();

        WebElement childElm = driver.findElement(By.id("q-room-0-children"));
        String childNum = childElm.getTagName();

        WebElement childAge1Elm = driver.findElement(By.id("q-room-0-child-0-age"));
        String childAge1 = childAge1Elm.getText();

        WebElement childAge2Elm = driver.findElement(By.id("q-room-0-child-1-age"));
        String childAge2 = childAge2Elm.getText();


        Assert.assertEquals(rooms, roomsNum, "The entered numbers on Search page don't match on Homepage");
        Assert.assertEquals(adults, adultNum, " Else the numbers don't match");




    }





}
