package com.learning.tests;

import com.learning.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class AdvancedInteractionsTest extends Base {

    @Test
    public void newWindowTest() {
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.linkText("Click Here")).click();

        String firstWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> windows = allWindows.iterator();

        while (windows.hasNext()) {
            String current = windows.next().toString();
            if (!firstWindow.equals(current)) {
                driver.switchTo().window(current);
                break;
            }
        }
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().equals("New Window"));

        driver.close();
        driver.switchTo().window(firstWindow);

        Assert.assertTrue(driver.getTitle().equals("The Internet"));
    }

    @Test
    public void switchToFrameTest() {
        driver.get("https://the-internet.herokuapp.com/iframe");

        WebElement frameId = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frameId);

        WebElement textArea = driver.findElement(By.id("tinymce"));
        textArea.clear();
        //textArea.sendKeys("Will it type this???");

        //Typing through javascript
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].innerHTML = 'Will it overwrite'", textArea);

        Assert.assertTrue(textArea.getText().toString().equals("Will it overwrite"), textArea.getText().toString());
    }

    @Test
    public void uploadFileTest() {
        driver.get("https://the-internet.herokuapp.com/upload");

        String fileToUpload = "";

        WebElement chooseFile = driver.findElement(By.id("file-upload"));
        chooseFile.sendKeys("");


    }
}