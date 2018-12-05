package com.herokuapp;

import com.learning.base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class SecurePage extends BasePage {

    By secureMessage = By.xpath("//*[contains(text(),'You logged into a secure area!')]");
    By logoutButton = By.xpath("//a[@href='/logout']");

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    public SecurePage(WebDriver driver, Map<String, String> testConfig, Logger log) {
        super(driver, testConfig, log);
    }

    public boolean isLogoutButtonVisible(){
        return find(logoutButton).isDisplayed();
    }




}
