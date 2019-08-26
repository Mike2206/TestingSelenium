package com.mike.selenium;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestScenarioStructuredMethods extends initial {

    public TestScenarioStructuredMethods() {
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//*[@id=\"header_logo\"]")
    WebElement logo;
    @FindBy (xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")
    WebElement CartDropDown;
    @FindBy (xpath = "//*[@id=\"button_order_cart\"]/span")
    WebElement ButtonOrderCart;
    @FindBy (xpath = "//*[@id=\"search_query_top\"]")
    WebElement SearchBar;
    @FindBy (xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/button")
    WebElement SearchButton;
    @FindBy (xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")
    WebElement DressesCategory;
    @FindBy (css = "#center_column > ul > li > div > div.left-block > div > a.product_img_link > img")
    WebElement Cart;


    public boolean test1() {
      boolean result;
        boolean title = driver.getTitle().contains("My Store");
        if(title){
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    public boolean waitAndCheckVisibilityOfWebelement(WebElement element) {
        try{
            gWait.until(ExpectedConditions.visibilityOf(element));
            return true;

        } catch (TimeoutException e1) {
            System.out.println("Element not found in specified time.");
            return false;
        }
        catch (Exception e2) {
            System.out.println("Element not found.");
            System.out.println(e2.getMessage());
            System.out.println(e2.getStackTrace());
            return false;
        }
    }

    public boolean checkTheLogo() {

        return waitAndCheckVisibilityOfWebelement(logo);
    }

}
