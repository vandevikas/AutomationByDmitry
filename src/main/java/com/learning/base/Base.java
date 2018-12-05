package com.learning.base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Base {

    protected WebDriver driver;
    protected HashMap<String, String> testConfig = new HashMap<>();
    protected Logger log;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "environment", "platform"})
    protected void setup(@Optional("chrome") String browser, @Optional("local") String environment, @Optional String platform, ITestContext itc) {
        String testName = itc.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
        log.info("Initilizing browser: " + browser);
        BrowserDriverFactory browserFactory = new BrowserDriverFactory(browser);
        if (environment.equals("grid")) {
            driver = browserFactory.createDriverGrid();
        } else if (environment.equals("sauce")) {
            driver = browserFactory.createDriverSauce(platform, testName);
        } else {
            driver = browserFactory.createDriver();
        }
        log.info("Initilizing " + browser + " success");
        log.info("Initilizing driver success");
        testConfig.put("browser", browser);
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        log.info("Killing driver instance and Closing browser");
        driver.quit();
    }

    @DataProvider(name = "negativeLoginData")
    public static Object[][] negativeLoginData() {
        return new Object[][]{
                {"IncorrectUserName", "SuperSecretPassword!", "Your username  invalid!"},
                {"tomsmith", "IncorrectPassword", "Your password is invalid!"},
                {"IncorrectUserName", "IncorrectPassword", "Your username is invalid!"}
        };
    }


    public void takeScreenshot(String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "//test-output//screenshots//" + fileName + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<LogEntry> getBrowserLog() {
        LogEntries log = driver.manage().logs().get("browser");
        List<LogEntry> logList = log.getAll();
        return logList;
    }
}
