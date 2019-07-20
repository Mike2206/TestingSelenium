package com.mike.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MyFirstTest {

    @Test
    public void startWebdriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().to("https://www.amazon.in");
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("Books");
        driver.findElement(By.cssSelector("#nav-search > form > div.nav-right > div > input")).click();
        driver.findElement(By.cssSelector("#n\\/1318132031 > span:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
        driver.close();


    }
}
