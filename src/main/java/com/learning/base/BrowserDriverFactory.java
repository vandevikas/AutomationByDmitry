package com.learning.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserDriverFactory {

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser;
    }

    public WebDriver createDriver() {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/browserExe/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;

            case "IE":
                System.setProperty("webdriver.ie.driver", "src/main/resources/browserExe/IEDriverServer.exe");
                driver.set(new InternetExplorerDriver());
                break;

            case "FF":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/browserExe/geckodriver.exe");
                driver.set(new FirefoxDriver());
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/browserExe/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
        }
        return driver.get();
    }

    public WebDriver createDriverGrid() {
        URL url = null;
        try {
            //IP of hub machine
            url = new URL("http://192.168.0.31:4444/wd/hub");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("Starting " + browser + " on grid");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        try {
            driver.set(new RemoteWebDriver(url, capabilities));
        } catch (WebDriverException e) {
            if (e.getMessage().contains("Error forwarding")) {
                System.out.println("Couldn't set: " + browser + " .It's unknown. Starting chrome instead");
                capabilities.setBrowserName("chrome");
                driver.set(new RemoteWebDriver(url, capabilities));
            }
        }
        return driver.get();
    }

    public WebDriver createDriverSauce(String platform, String testName) {
        System.out.println("[Setting up driver: " + browser + " on SauceLabs]");
        String username = "someUserName";
        String accessKey = "someKey";
        String url = "http://" + username + ":" + accessKey + "@ondemand.saucelabs.com:80/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        if (platform == null) {
            capabilities.setCapability("platform", "Windows 10");
        } else {
            capabilities.setCapability("platform", platform);
        }
        capabilities.setCapability("name", testName);
        try {
            driver.set(new RemoteWebDriver(new URL(url), capabilities));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver.get();
    }
}
