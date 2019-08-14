package com.mike.selenium;

public class TestScenarioStructuredMethods extends initial {

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
}
