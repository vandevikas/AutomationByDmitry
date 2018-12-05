package com.herokuapp;

import com.learning.base.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class LoginPage extends BasePage {
    private String loginPageUrl = "https://the-internet.herokuapp.com/login";

    By userNameLocator = By.id("username");
    By passwordLocator = By.cssSelector("#password");
    By loginLocator = By.xpath("//button[@type='submit']");
    By errorMessage = By.xpath("//div[@id='flash']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage(WebDriver driver, Map<String, String> testConfig, Logger log) {
        super(driver, testConfig, log);
    }

    public void openLoginPage() {
        openUrl(loginPageUrl);
    }

    public void login(String username, String password) {
        find(userNameLocator).sendKeys(username);
        find(passwordLocator).sendKeys(password);
        if (!testConfig.isEmpty()) {
            if (testConfig.get("browser").equals("chrome")) {
                //perform this extra step
                System.out.println("I am " + testConfig.get("browser") + " browser.");
            }
        }
        find(loginLocator).click();
    }

    public LoginPage negativeLogin(String username, String password) {
        login(username, password);
        return new LoginPage(driver);
    }

    public SecurePage positiveLogin(String username, String password) {
        login(username, password);
        return new SecurePage(driver, testConfig, log);
    }

    public String errorMessage() {
        return find(errorMessage).getText();
    }
}
