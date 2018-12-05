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

public class WaitExampleTest extends Base {

    @Test
    public void WaitingExampleTest() {

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement start = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Start']")));
        start.click();

        WebElement loading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']")));
        Assert.assertTrue(loading.isDisplayed());

        wait.until(ExpectedConditions.invisibilityOf(loading));

        WebElement helloWorld = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
        Assert.assertTrue(helloWorld.getText().equals("Hello World!"));

    }
}