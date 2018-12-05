package com.learning.tests;

import com.learning.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StaleExampleTest extends Base {

    @Test
    public void StaleElementExampleTest() {

        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement clickHere = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Click here")));
        clickHere.click();
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        Assert.assertTrue(message.isDisplayed());

        clickHere = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Click here")));
        clickHere.click();

        message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
        Assert.assertTrue(message.isDisplayed());


    }
}