package com.ek.test.framework.hooks;



import com.ek.test.framework.helpers.PropertyHelper;

import com.ek.test.pages.PageFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.core.annotation.Order;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

// manages browser lifecycle
public class BrowserManagement {

    public static WebDriver driver;
    public static String downLoadDirectory;
    public static String TestCaseName = null;


    @Before(value = {"~@NoBrowser"}, order = 1)
    public void prepareBrowser() {
        try {
            driver = getWebdriver();
            driver.get(PropertyHelper.getBaseUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openURL(String url) throws Exception {
        try{
            driver.get(url);
        }catch (Exception e){
            throw new Exception("ERROR: While Opening the URL - "+url);
        }
    }

    @After(value = {"~@NoBrowser"}, order = 100)
    public void closeBrowser(Scenario scenario) throws Exception {
        try {
            if (scenario.isFailed()) {
                scenario.embed(((TakesScreenshot) PageFactory.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
            }
        } catch (Exception e) {
            //throw new Exception("Failed to take screen shot \n" + e.getMessage());
        } finally {
            driver.quit();
        }

    }


    public WebDriver getWebdriver() throws Exception {
        WebDriver driver;
        Boolean useGrid = Boolean.valueOf(PropertyHelper.getProperty("GridRun"));
        String browserType = PropertyHelper.getProperty("environment.BrowserType");
        String hubURl = PropertyHelper.getProperty("HubUrl");
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = getChromeDriver();
        } else if (browserType.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            driver = getFirefoxDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
        PageFactory.setDriver(driver);
        return driver;
    }

    private FirefoxDriver getFirefoxDriver() throws Exception {
        File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        Thread.sleep(500);
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 4);

        profile.setPreference("network.automatic-ntlm-auth.trusted-uris", PropertyHelper.getBaseUrl());
        profile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", "true");
        profile.setPreference("network.negotiate-auth.trusted-uris", PropertyHelper.getBaseUrl());
        profile.setPreference("network.negotiate-auth.allow-non-fqdn", "true");

        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", "./target");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-gzip");
        FirefoxDriver driver = new FirefoxDriver(ffBinary, profile);
        driver.manage().window().maximize();
        return driver;
    }

    private ChromeDriver getChromeDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/drivers"+"/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

}
