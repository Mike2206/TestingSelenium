package com.mike.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeSuite
    public void startWebdriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        url = "http://automationpractice.com";
        driver.get(url);
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
        Assert.assertTrue(driver.getTitle().contains("My Store"));
    }

    @Test(priority = 2)
    public void CheckTheLogo(){
        Assert.assertTrue(driver.findElement(By.id("header_logo")).isDisplayed());
    }

    @Test(priority = 3)
    public void FindWebElements(){
        List<WebElement> elements = driver.findElements(new By.ByXPath(".//*[@id=\"block_top_menu\"]"));
        for (WebElement allLinks:elements){
            allLinks.getText();
            System.out.println(allLinks.getText());
        }
    }

    @Test(priority = 4)
    public void CountingWebElements(){
        int CountElements = driver.findElements(new By.ByXPath(".//*[@id=\"block_top_menu\"]/ul/li")).size();
        System.out.println(CountElements);
    }

    @Test(priority = 5)
    public void GoingToWomen(){
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();
        Assert.assertTrue(driver.getTitle().contains("Women - My Store"));
    }

    @Test(priority = 6)
    public void ChoosingSizeM(){
        driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_2\"]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_2\"]")).isSelected());
    }

    @Test(priority = 7)
    public void GoToTShirts(){
        driver.navigate().back();
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
        Assert.assertTrue(driver.getTitle().contains("T-shirts - My Store"));
    }

}
