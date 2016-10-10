package com.ek.test.framework.helpers;

import com.ek.test.pages.PageFactory;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.Keys.TAB;

public class WebElementHelper {

    public static final int WEBELEMENT_DEFAULT_TIMEOUT = 20;

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void enterText(WebElement element, String value) throws Exception {
        clickElement(element);
        element.clear();
        element.sendKeys(value);
        element.sendKeys(TAB);
    }

    public static void enterTextWithEnterKey(WebElement element, String value, boolean enterKey) throws Exception {
        clickElement(element);
        element.sendKeys(value);
        if (enterKey) {
            element.sendKeys(ENTER);
            WaitHelper.waitFor(500);
            WaitHelper.waitForPageLoad();
            WaitHelper.waitForJStoLoad();
        }
    }


    public static void clickElement(WebElement element) throws Exception {
        int seconds = WEBELEMENT_DEFAULT_TIMEOUT;
        long time = 1000 * seconds;
        boolean timeout = false;
        while (!timeout && time > 0) {
            try {
                element.click();
                timeout = true;
                Thread.sleep(100);
            } catch (Exception e) {
                timeout = false;
                Thread.sleep(100);
                time = time - 100;
            }
        }
        if (!timeout) {
            throw new Exception("Element not clickable at the moment");
        }
        // WaitHelper.waitForJStoLoad();
    }

    public static String getElementValue(WebElement element) throws Exception {
        WaitHelper.waitUntilVisible(element);
        return getText(element);
    }

    public static String getElementValue2(WebElement element) throws Exception {
        WaitHelper.waitUntilVisible(element);
        return element.getAttribute("value");
    }

    public static String getAttributeValue(WebElement element, String attribute) throws Exception {
        WaitHelper.waitUntilVisible(element);
        return element.getAttribute(attribute);
    }

    public static String getText(WebElement element) throws Exception {
        int seconds = WEBELEMENT_DEFAULT_TIMEOUT;
        long time = 1000 * seconds;
        boolean timeout = false;
        String text = null;
        while (!timeout && time > 0) {
            try {
                text = element.getText();
                if (!StringUtils.isEmpty(text)) {
                    Thread.sleep(100);
                    timeout = true;
                }
            } catch (Exception e) {
                timeout = false;
                Thread.sleep(100);
                time = time - 100;
            }
        }
        if (!timeout) {
            throw new Exception("Failed to retrieve text from the element");
        }
        return text;
    }
}
