package com.learning.tests;

import com.herokuapp.LoginPage;
import com.herokuapp.SecurePage;
import com.learning.base.Base;
import com.learning.base.CsvDataHandle;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class FirstTest extends Base {

    @Test
    @Parameters({"username", "password"})
    public void firstTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver, testConfig, log);
        loginPage.openLoginPage();

        SecurePage securePage = loginPage.positiveLogin(username, password);

        Assert.assertTrue(securePage.isLogoutButtonVisible());
        Assert.assertTrue(securePage.getPageSource().contains("You logged into a secure area!"));
    }

    @Test(dataProvider = "negativeLoginTestWithDataProvivder")
    public void negativeLoginTestWithDataProvivder(String username, String password, String messagae) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        loginPage.negativeLogin(username, password);
        Assert.assertTrue(loginPage.errorMessage().contains(messagae), "Expected: " + messagae + "\nActual: " + loginPage.errorMessage());
    }

    @Test(dataProvider = "negativeLoginTestWithCSV", dataProviderClass = CsvDataHandle.class)
    public void negativeLoginTestWithCSV(Map<String, String> testData) {

        String username = testData.get("username");
        String password = testData.get("password");
        String messagae = testData.get("expectedError");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        loginPage.negativeLogin(username, password);
        Assert.assertTrue(loginPage.errorMessage().contains(messagae), "Expected: " + messagae + "\nActual: " + loginPage.errorMessage());
    }

    @Test
    @Parameters({"username", "password"})
    public void differentBrowserBehaviourTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        SecurePage securePage = loginPage.positiveLogin(username, password);

        String expectedMsg = "You logged into a secure area!";
        if (testConfig.get("browser").equals("IE")) {
            expectedMsg = "You logged into a secure area!";
            //Lets say if browser is IE then the message is different.
            //Or if browser is different then perform this click action or you can add anything.
        }

        Assert.assertTrue(securePage.isLogoutButtonVisible());
        Assert.assertTrue(securePage.getPageSource().contains(expectedMsg));
    }

}
