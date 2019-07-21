package com.mike.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestScenarioStructured {
    //Variables
    private String url;
    private static WebDriver driver;

    @BeforeSuite
    public void startWebdriver() {
        url = "http://automationpractice.com/index.php?";
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test(priority = 1)
    public void CheckTittle(){
        driver.get(url);
        Assert.assertTrue(driver.getTitle().contains("My Store"));
    }

    @Test(priority = 2)
    public void FindWebElemtns(){
        List<WebElement> elements = driver.findElements(new By.ByXPath(".//*[@id=\"block_top_menu\"]"));
        for (WebElement allLinks:elements){
            allLinks.getText();
            System.out.println(allLinks.getText());

        }

    }
}
