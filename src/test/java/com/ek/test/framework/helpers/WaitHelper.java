package com.ek.test.framework.helpers;

import com.ek.test.pages.PageFactory;
import com.google.common.base.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    public static final int WEBELEMENT_DEFAULT_TIMEOUT = 20;

    public static void waitFor(Integer timeout) throws InterruptedException {
        //TODO
        Thread.sleep(timeout);
    }

    public static void waitUntilVisible(WebElement element) {
        WebDriver driver = PageFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(WebElement element) {
        WebDriver driver = PageFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitForJStoLoad() {

        WebDriver driver = PageFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public static void waitForPageLoad() throws InterruptedException {
        WebDriver driver = PageFactory.getDriver();
        waitFor(2500);
        Wait<WebDriver> wait = new WebDriverWait(driver, WEBELEMENT_DEFAULT_TIMEOUT);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }
}
