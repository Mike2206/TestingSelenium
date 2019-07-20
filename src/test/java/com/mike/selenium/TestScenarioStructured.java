package com.mike.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class TestScenarioStructured {
    //Variables
    private String url;
    private WebDriver driver = new FirefoxDriver();

    @BeforeSuite
    public void startWebdriver() {
        url = "http://automationpractice.com/index.php?";
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test(priority = 1)
    public void Test1(){
        driver.get(url);
        Assert.assertTrue(driver.getTitle().contains("My Store"));
    }

}
