package com.mike.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class TestScenarioStructured extends initial{
    initial initial;
    TestScenarioStructuredMethods TestScenarioStructuredMethods;
    private Actions builder;

    @BeforeSuite
    public void setUpBeforeTestClass() {
        initial = new initial();
        initial.invokeBrowser();
        builder = new Actions(driver);
        TestScenarioStructuredMethods = new TestScenarioStructuredMethods();
    }

    @AfterSuite
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test(priority = 1)
    public void CheckTittle(){
        Assert.assertTrue(TestScenarioStructuredMethods.test1());
    }

    @Test(priority = 2)
    public void CheckTheLogo(){
        Assert.assertTrue(TestScenarioStructuredMethods.checkTheLogo(),"Logo not found");
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
        builder.moveToElement(TestScenarioStructuredMethods.Cart);
        builder.perform();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]")).click();
        Assert.assertTrue(driver.getPageSource().contains("Product successfully added to your shopping cart"));
        boolean item = driver.getPageSource().contains("There is 1 item in your cart.");
        if (item){
            System.out.println("There is just 1 product in your cart");
        } else {
            System.out.println("There are 2 or more products in your cart");
        }
    }

    @Test(priority = 10)
    public void CheckingIfProductIsAdded(){
        gWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span/span"))).click();
        js.executeScript("window.scrollTo(0,-500)");
        builder.moveToElement(TestScenarioStructuredMethods.CartDropDown);
        builder.perform();
        Assert.assertTrue(driver.getPageSource().contains("Faded Short"));
    }

    @Test(priority = 11)
    public void ProceedToCheckout(){
        TestScenarioStructuredMethods.ButtonOrderCart.click();
        gWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"cart_title\"]"), "SHOPPING-CART SUMMARY"));
        js.executeScript("window.scrollTo(0,500)");
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p[2]/a[1]")).click();
    }

    @Test(priority = 12)
    public void SearchForDress(){
        TestScenarioStructuredMethods.SearchBar.clear();
        TestScenarioStructuredMethods.SearchBar.sendKeys("Printed Dress");
        TestScenarioStructuredMethods.SearchButton.click();
        TestScenarioStructuredMethods.DressesCategory.click();
        Assert.assertTrue(driver.getTitle().contains("Dresses - My Store"));
    }
}
