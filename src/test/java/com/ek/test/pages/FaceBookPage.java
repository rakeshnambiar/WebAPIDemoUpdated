package com.ek.test.pages;

import com.ek.test.framework.helpers.WaitHelper;
import com.ek.test.framework.helpers.WebElementHelper;

/**
 * Created by Administrator on 9/9/2016.
 */
public class FaceBookPage extends BasePage{
    public void enterUserCredential(String userName, String password) throws Exception {
        try{
            WaitHelper.waitForPageLoad();
            WaitHelper.waitForJStoLoad();
            WebElementHelper.enterText(getElement("UserName"), userName);
            WebElementHelper.enterText(getElement("Password"), password);
        }catch (Exception e){
            throw new Exception("ERROR: While Entering the User Credential on FaceBook Page");
        }
    }

    public void clicksOnFaceBookLogin() throws Exception {
        try{
            WaitHelper.waitForPageLoad();
            WebElementHelper.clickElement(getElement("LoginBtn"));
            WaitHelper.waitForPageLoad();
        }catch (Exception e){
            throw new Exception("ERROR: While Clicks on the Facebook Login Button");
        }
    }

    public boolean verifyHomePage() throws Exception {
        try{
            return WebElementHelper.isElementDisplayed(getElement("Logo"));
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Home Page on Facebook");
        }
    }

    public boolean verifyLoginPageNotAvailable() throws Exception {
        try{
            if(!WebElementHelper.isElementDisplayed(getElement("LoginBtn"))){
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Facebook Login Page NOT available");
        }
    }
}
