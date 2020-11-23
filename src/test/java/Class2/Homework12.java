package Class2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Homework12 {
    /**
     * Testcase 1: Verify the feels-like temp value is between low and high temp values at any zipcode
     * 1. Launch the darksky.net
     * 2.Enter the current zip code
     * 3.Capture the feels like the temp and store as String.
     * 4.Convert the String to int.
     * 5.Very using Assert if the feels like temp is between low and high.
     */

    @Test
    public void verifyTemp() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");
        driver.findElement(By.xpath("//input[@type='text']")).clear();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("11358");
        driver.findElement(By.className("searchButton")).click();
        WebElement df = driver.findElement(By.className("feels-like-text"));
        WebElement df1 = driver.findElement(By.className("low-temp-text"));
        WebElement df2 = driver.findElement(By.className("high-temp-text"));

        String feelsLike = df.getText();
        int indexOf = feelsLike.indexOf("°");
        String temp = feelsLike.substring(0, indexOf);
        int tempInt = Integer.valueOf(temp);

        String lowTemp= df1.getText();
        int indexOfLow = lowTemp.indexOf("°");
        String tempLow = lowTemp.substring(0, indexOfLow);
        int tempInt1 = Integer.valueOf(tempLow);

        String highTemp = df2.getText();
        int indexOfHigh = highTemp.indexOf("°");
        String tempHigh = highTemp.substring(0, indexOfHigh);
        int tempInt2 = Integer.valueOf(tempHigh);
        Assert.assertTrue(tempInt>tempInt1&&tempInt2>tempInt, "Feels like temp is between low and high");
    }

    /**
     * Testcase 2: Verify the dates on the Blog Page page appears in reverse chronological order
     * 1.Launch the Blog page on darksky.net
     * 2.Capture the dates and store them as String.
     * 3.Using SimpleDateFormat format the dates.
     * 4.Using parse method convert the String to Date.
     * 5.Verify using Assert if the dates in reverse chronological order.
     */

    @Test
    public void verifyDates() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://blog.darksky.net");
        WebElement df = driver.findElement(By.xpath("//time[contains(text(),'August 1, 2020')]"));
        WebElement df2 = driver.findElement(By.xpath("//time[contains(text(),'July 1, 2020')]"));
        WebElement df3 = driver.findElement(By.xpath("//time[contains(text(),'March 31, 2020')]"));
        String dtText = df.getText();
        String dfText = df2.getText();
        String dftText = df3.getText();

        Date now = new Date();
        String dateText = dtText;
        Date parsedDate = null;
        SimpleDateFormat dt = new SimpleDateFormat("MMMM d, yyyy");
        try {
            parsedDate = dt.parse(dateText);
            System.out.println(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dateText2 = dfText;
        Date parsedDate2 = null;
        try {
            parsedDate2 = dt.parse(dateText2);
            System.out.println(parsedDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dateText3 = dftText;
        Date parseDate3 = null;
        try {
            parseDate3 = dt.parse(dateText3);
            System.out.println(parseDate3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(parsedDate.after(parsedDate2), "Most recent date does not comes after parsedDate2 - ");
        Assert.assertTrue(parsedDate2.after(parseDate3), "Else it is not in reverse chronological order");
        Assert.assertTrue(parsedDate.after(parsedDate2)&& parseDate3.before(parsedDate2), "Else it is incorrect");

    }

    /**
     * Testcase 3: Verify the temperature value converts as expected as the the unit selected
     * 1.Launch the darksky.net
     * 2.Capture the current temperature using className locator and store it as String.
     * 3.Convert the String to int.
     * 4.Click on Convert button.
     * 5.Click on C° button.
     * 6.Capture teh C° temperature and store it as String.
     * 7.Convert the String to int.
     * 8.Using the Convert Formula verify if temperature value converts as expected as the unit selected.
     *
     */

    @Test
    public void tempConvertsAsExpected() {
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");
        WebElement df = driver.findElement(By.xpath("//span[@class='summary swap']"));
        String getTemp = df.getText();
        int indefOfDegree = getTemp.indexOf("°");
        String tempToString = getTemp.substring(0, indefOfDegree);
        int currentTempF = Integer.valueOf(tempToString);


        driver.findElement(By.xpath("//span[@class='label']")).click();
        driver.findElement(By.xpath("//li[@class='last selected highlighted']")).click();
        WebElement df1 = driver.findElement(By.xpath("//span[@class='summary swap']"));
        String tempText = df1.getText();
        int indexOfDegreeC = tempText.indexOf("°");
        String tempToStringC = tempText.substring(0, indexOfDegreeC);
        int currentTempC = Integer.valueOf(tempToStringC);

        int actualC = (int) Math.round((currentTempF - 32) * 5 / 9);
        int actualF = (int) Math.round(currentTempC * 9/5 + 32);
        Assert.assertEquals(currentTempC, actualC, "Current C° temperature is not equal to actual C°");
        Assert.assertEquals(currentTempF, actualF, "Current F° temperature is not equal to actual F°");


    }




}
