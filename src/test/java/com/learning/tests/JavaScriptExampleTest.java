package com.learning.tests;

import com.learning.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class JavaScriptExampleTest extends Base {
    @Test
    public void StaleElementExampleTest() {
        driver.get("https://the-internet.herokuapp.com/large");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("window.scrollBy(0,250)","");

        WebElement table = driver.findElement(By.id("large-table"));
        jsExecutor.executeScript("arguments[0].scrollIntoView();", table);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}