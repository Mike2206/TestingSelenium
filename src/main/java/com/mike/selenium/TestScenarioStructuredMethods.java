package com.mike.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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
    @FindBy (xpath = "//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]")
    WebElement AddToCart;
    @FindBy (xpath = "/html/body/div/div[2]/div/div[3]/div/p[2]/a[1]")
    WebElement ProceedToCheckoutButton;
    @FindBy (xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span/span")
    WebElement ContinueShoppingButton;
    @FindBy (css = "#center_column > ul > li > div > div.right-block > div.content_price > span")
    WebElement Prize;
    @FindBy (css = "#center_column > ul > li > div > div.right-block > h5 > a")
    WebElement Name;
    @FindBy (xpath = "//*[@id=\"block_top_menu\"]/ul/li[3]/a")
    WebElement TShirtButton;
    @FindBy (xpath = "//*[@id=\"layered_id_attribute_group_2\"]")
    WebElement SizeMCheckbox;
    @FindBy (xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]/a")
    WebElement WomenCategoryButton;
    @FindBy (xpath = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")
    WebElement CartFrame;
    @FindBy (xpath = "//*[@id=\"cart_title\"]")
    WebElement CartTitle;


    public boolean TestTittle(String text){
        boolean result;
        boolean tittle = driver.getTitle().contains(text);
        if(tittle) {
            result = true;
        }  else {
            result = false;
        }
        return result;
    }

    public boolean pageSource(String text){
        boolean result;
        boolean source = driver.getPageSource().contains(text);
        if(source){
            result = true;
        }   else {
            result = false;
        }
        return result;
    }

    public boolean textInElement(WebElement element, String text) {
        try {
            gWait.until(ExpectedConditions.textToBePresentInElement(element,text));
           // gWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div"),text)));
        } catch (TimeoutException e) {
            System.out.println("Nie udalo sie znalezc elementu w zadanym czasie.");
    }
        return true;
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

    public void getListOfElementsByProductName() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//a[contains(@class,'product-name')]"));

        for(WebElement el : listOfElements) {
            System.out.println(el.getText());
        }
        for (int i = 0; i< listOfElements.size();i++){
            listOfElements.get(i).getAttribute("value");
        }
    }

}
