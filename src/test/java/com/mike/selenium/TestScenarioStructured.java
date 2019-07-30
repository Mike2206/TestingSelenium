package com.mike.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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
    private static JavascriptExecutor js;

    @BeforeSuite
    public void startWebdriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        url = "http://automationpractice.com";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
    }

    @AfterSuite
    public void tearDown(){
        driver.manage().deleteAllCookies();
        //driver.close();
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

    @Test(priority = 8)
    public void CheckThePriceAndName(){
        WebElement prize = driver.findElement(By.cssSelector("#center_column > ul > li > div > div.right-block > div.content_price > span"));
        WebElement name = driver.findElement(By.cssSelector("#center_column > ul > li > div > div.right-block > h5 > a"));
        prize.getText();
        name.getText();
        System.out.println(name.getText());
        System.out.println(prize.getText());
    }

    @Test(priority = 9)
    public void AddingProductToCart(){
        js.executeScript("window.scrollTo(0,500)");
        WebElement cart = driver.findElement(By.cssSelector("#center_column > ul > li > div > div.left-block > div > a.product_img_link > img"));
        Actions builder = new Actions(driver);
        builder.moveToElement(cart);
        builder.build().perform();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]")).isDisplayed());

    }

}
