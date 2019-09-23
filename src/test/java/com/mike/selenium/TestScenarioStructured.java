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
    TestScenarioStructuredMethods TestScenarioStructuredMethods;
    private Actions builder;

    @BeforeSuite
    public void setUpBeforeTestClass() {
        invokeBrowser();
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
        System.out.println("...Starting Test 1...");
        TestScenarioStructuredMethods.getListOfElementsByProductName();
        Assert.assertTrue(TestScenarioStructuredMethods.TestTittle("My Store"));
    }

    @Test(priority = 2)
    public void CheckTheLogo(){
        System.out.println("...Starting Test 2...");
        Assert.assertTrue(TestScenarioStructuredMethods.checkTheLogo(),"Logo not found");
    }

    @Test(priority = 3)
    public void FindWebElements(){
        System.out.println("...Starting Test 3...");
        List<WebElement> elements = driver.findElements(new By.ByXPath(".//*[@id=\"block_top_menu\"]"));
        for (WebElement allLinks:elements){
            allLinks.getText();
            System.out.println(allLinks.getText());
        }

      /*  String text = "text do znalezienia";
        boolean czyZnalezionyText = TestScenarioStructuredMethods.textInElement(TestScenarioStructuredMethods.logo,text);
        if(czyZnalezionyText){
            System.out.println("Element " + text + " zostal odnaleziony poprawnie.");
        } else  {
            System.out.println("Element " + text + " nie zostal odnaleziony.");
        }*/
    }

    @Test(priority = 4)
    public void CountingWebElements(){
        System.out.println("...Starting Test 4...");
        int CountElements = driver.findElements(new By.ByXPath(".//*[@id=\"block_top_menu\"]/ul/li")).size();
        System.out.println(CountElements);
    }

    @Test(priority = 5)
    public void GoingToWomen(){
        System.out.println("...Starting Test 5...");
        TestScenarioStructuredMethods.WomenCategoryButton.click();
        Assert.assertTrue(TestScenarioStructuredMethods.TestTittle("Women - My Store"));
    }

    @Test(priority = 6)
    public void ChoosingSizeM(){
        System.out.println("...Starting Test 6...");
        TestScenarioStructuredMethods.SizeMCheckbox.click();
        Assert.assertTrue(TestScenarioStructuredMethods.SizeMCheckbox.isSelected());
    }

    @Test(priority = 7)
    public void GoToTShirts(){
        System.out.println("...Starting Test 7...");
        driver.navigate().back();
        TestScenarioStructuredMethods.TShirtButton.click();
        Assert.assertTrue(TestScenarioStructuredMethods.TestTittle("T-shirts - My Store"));
    }

    @Test(priority = 8)
    public void CheckThePriceAndName(){
        System.out.println("...Starting Test 8...");
        TestScenarioStructuredMethods.waitAndCheckVisibilityOfWebelement(TestScenarioStructuredMethods.Prize);
        TestScenarioStructuredMethods.waitAndCheckVisibilityOfWebelement(TestScenarioStructuredMethods.Name);
        TestScenarioStructuredMethods.Prize.getText();
        TestScenarioStructuredMethods.Name.getText();
        System.out.println(TestScenarioStructuredMethods.Name.getText());
        System.out.println(TestScenarioStructuredMethods.Prize.getText());
    }

    @Test(priority = 9)
    public void AddingProductToCart(){
        System.out.println("...Starting Test 9...");
        js.executeScript("window.scrollTo(0,500)");
        builder.moveToElement(TestScenarioStructuredMethods.Cart);
        builder.perform();
        TestScenarioStructuredMethods.AddToCart.click();
        TestScenarioStructuredMethods.waitAndCheckVisibilityOfWebelement(TestScenarioStructuredMethods.CartFrame);
        Assert.assertTrue(TestScenarioStructuredMethods.textInElement(TestScenarioStructuredMethods.CartFrame,"Product successfully added to your shopping cart"));
        boolean item = driver.getPageSource().contains("There is 1 item in your cart.");
        if (item){
            System.out.println("There is just 1 product in your cart");
        } else {
            System.out.println("There are 2 or more products in your cart");
        }
    }

    @Test(priority = 10)
    public void CheckingIfProductIsAdded(){
        System.out.println("...Starting Test 10...");
        gWait.until(ExpectedConditions.elementToBeClickable(TestScenarioStructuredMethods.ContinueShoppingButton)).click();
        js.executeScript("window.scrollTo(0,-500)");
        builder.moveToElement(TestScenarioStructuredMethods.CartDropDown);
        builder.perform();
        TestScenarioStructuredMethods.pageSource("Faded Short");
    }

    @Test(priority = 11)
    public void ProceedToCheckout(){
        System.out.println("...Starting Test 11...");
        TestScenarioStructuredMethods.ButtonOrderCart.click();
        TestScenarioStructuredMethods.textInElement(TestScenarioStructuredMethods.CartTitle, "SHOPPING-CART SUMMARY");
        js.executeScript("window.scrollTo(0,500)");
        TestScenarioStructuredMethods.ProceedToCheckoutButton.click();
    }

    @Test(priority = 12)
    public void SearchForDress(){
        System.out.println("...Starting Test 12...");
        TestScenarioStructuredMethods.waitAndCheckVisibilityOfWebelement(TestScenarioStructuredMethods.SearchButton);
        TestScenarioStructuredMethods.SearchBar.clear();
        TestScenarioStructuredMethods.SearchBar.sendKeys("Printed Dress");
        TestScenarioStructuredMethods.SearchButton.click();
        TestScenarioStructuredMethods.DressesCategory.click();
        TestScenarioStructuredMethods.TestTittle("Dresses - My Store");
    }
}
