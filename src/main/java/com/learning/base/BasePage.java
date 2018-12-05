package com.learning.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class BasePage {

    protected WebDriver driver;
    protected Map<String, String> testConfig = new HashMap<>();
    protected Logger log;

    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage(WebDriver driver, Map<String, String> testConfig, Logger log) {
        this.driver = driver;
        this.testConfig = testConfig;
        this.log = log;
    }

    protected void openUrl(String url) {
        driver.navigate().to(url);
        log.info("Opening page: " + url);
    }

    protected WebElement find(By locator) {
        waitForElement(locator);
        log.info("Element found and returning: " + locator);
        return driver.findElement(locator);
    }

    protected void waitForElement(By locator) {
        wait = new WebDriverWait(driver, 10);
        log.info("Waiting for presence of Element " + locator);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

}
