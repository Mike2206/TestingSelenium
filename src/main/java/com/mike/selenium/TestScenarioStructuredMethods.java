package com.mike.selenium;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestScenarioStructuredMethods extends initial {

    // todo | brak inicjacji elementow PageFacotry - wydaje mi sie ze pisalismy cos takiego
    // todo | metoda poniżej wlasnie to robi
    public TestScenarioStructuredMethods() {
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//*[@id=\"header_logo\"]")
    WebElement logo;


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

    // todo | metoda czeka na wskazany w konstryktorze Webelement z użyciem globalnego waita. Jak nie znajdzie
    // todo | zwraca komunikat bledu
    public boolean waitAndCheckVisibilityOfWebelement(WebElement element) {
        try{
            gWait.until(ExpectedConditions.visibilityOf(element));
            return true;
            // todo | wszystkie waity z ExpectedConditions zwracają TimeoutException, drugi exception jest po to
            // todo | jakbys chcial sobie rozszerzyc ta metode abys na poczatku wiedzial jaki blad wystepuje
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

    // todo | metoda przekazuje Webelement do metody 'waitAndCheckVisibilityOfWebelement' i w niej jest robione sprawdzenie
    public boolean checkTheLogo() {
        return waitAndCheckVisibilityOfWebelement(logo);
    }

}
