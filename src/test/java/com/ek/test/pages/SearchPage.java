package com.ek.test.pages;

import com.ek.test.framework.helpers.WaitHelper;
import com.ek.test.framework.helpers.WebElementHelper;
import com.ek.test.framework.hooks.ScenarioHook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyDownAction;

import java.util.List;

/**
 * Created by Administrator on 9/9/2016.
 */
public class SearchPage extends BasePage{

    public void performItemSearch(String text) throws Exception {
        try{
            WebElementHelper.enterTextWithEnterKey(getElement("SearchField"),text,true);
            WaitHelper.waitForPageLoad();
            WaitHelper.waitForJStoLoad();
        }catch (Exception e){
            throw new Exception("ERROR: While Perform Search operation");
        }
    }

    public boolean verifyDefaultSearchResultCount(int count) throws Exception {
        try{
            List<WebElement> resultItem = getElements("ResultItem");
            if(resultItem.size()==count){
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While verifying the default record count");
        }
    }

    public int getSearchResultCount() throws Exception {
        try{
            WaitHelper.waitForJStoLoad();
            WaitHelper.waitForPageLoad();
            List<WebElement> resultItem = getElements("ResultItem");
            return resultItem.size();
        }catch (Exception e){
            throw new Exception("ERROR: While getting the search record count");
        }
    }

    public boolean isSearchResultPageDisplayed() throws Exception {
        try{
            WaitHelper.waitForJStoLoad();
            WaitHelper.waitForPageLoad();
            return WebElementHelper.isElementDisplayed(getElement("ResultHeader"));
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Search Result Page availability");
        }
    }

    public boolean isViewMoreOptionDisplayed(int defRecCount) throws Exception {
        try{
            scrollDownSearchResultPage();
            if(getSearchResultCount() >= defRecCount){
                return WebElementHelper.isElementDisplayed(getElement("ViewMore"));
            }
            else
            {
                ScenarioHook.getScenario().write("View MOre Button NOT applicable in this flow");
                return true;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Validating the View More button");
        }
    }

    public boolean verifyViewMoreButton() throws Exception {
        try{
            return WebElementHelper.isElementDisplayed(getElement("ViewMore"));
        }catch (Exception e){
            throw new Exception("ERROR: While Validating the View More button in an expected condition");
        }
    }

    public void clicksViewMoreButton() throws Exception {
        try{
            WebElementHelper.clickElement(getElement("ViewMore"));
        }catch (Exception e){
            throw new Exception("ERROR: While Clicks on the View More Button");
        }
    }

    public boolean verifyLoadMoreFeature(int defCount) throws Exception {
        try{
            scrollDownSearchResultPage();
            int currentCount = getSearchResultCount();
            ScenarioHook.getScenario().write("Search Count after first attempt - "+currentCount);
            if(currentCount > defCount){
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Load More functionality");
        }
    }

    public void scrollDownSearchResultPage() throws Exception {
        try{
            WaitHelper.waitForPageLoad();
            WaitHelper.waitForJStoLoad();
            WebElement footerElement = getElement("NewsLetter");
            new Actions(PageFactory.getDriver()).moveToElement(footerElement).click().perform();
            new Actions(PageFactory.getDriver()).moveToElement(footerElement).click().perform();
        }catch (Exception e){
            //Do nothing);
        }
    }

    public boolean verifyAlertMessage(String expectedMessage) throws Exception {
        try{
            String actualMsg = WebElementHelper.getElementValue(getElement("AlertMessage"));
            ScenarioHook.getScenario().write("Actual Message - "+actualMsg);
            if(actualMsg.contains(expectedMessage)){
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the alert message");
        }
    }

    public void selectFirstItem() throws Exception {
        try{
            WaitHelper.waitForPageLoad();
            WaitHelper.waitForJStoLoad();
            List<WebElement> resultItem = getElements("ResultItem");
            if(resultItem.size() > 0){
                WebElementHelper.clickElement(resultItem.get(0));
                WaitHelper.waitForPageLoad();
                WaitHelper.waitForJStoLoad();
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Selecting the Product");
        }
    }

    public void selectSecondItem() throws Exception {
        try{
            List<WebElement> resultItem = getElements("ResultItem");
            if(resultItem.size() > 1){
                WebElementHelper.clickElement(resultItem.get(1));
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Selecting the Second Product");
        }
    }
}
