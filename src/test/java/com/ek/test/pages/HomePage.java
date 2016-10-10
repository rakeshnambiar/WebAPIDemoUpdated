package com.ek.test.pages;

import com.ek.test.framework.helpers.WaitHelper;
import com.ek.test.framework.helpers.WebElementHelper;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Administrator on 9/9/2016.
 */
public class HomePage extends BasePage{
    public void clickOnSignLink() throws Exception {
        try{
            WaitHelper.waitUntilClickable(getElement("SignInLink"));
            WebElementHelper.clickElement(getElement("SignInLink"));
            WaitHelper.waitForPageLoad();
            WaitHelper.waitForJStoLoad();
        }catch (Exception e){
            throw new Exception("ERROR: While Clicks on the Sign In Link");
        }
    }

    public boolean isSignInLinkDisplayed() throws Exception {
        try{
            WaitHelper.waitForJStoLoad();
            WaitHelper.waitForPageLoad();
            return WebElementHelper.isElementDisplayed(getElement("SignInLink"));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("ERROR: While Verifying the Sign In Link's availability");
        }
    }

    public void clicksOnFaceBookConnectButton() throws Exception {
        try{
            WebElementHelper.clickElement(getElement("FaceBookConnectBtn"));
        }catch (Exception e){
            throw new Exception("ERROR: While Clicks on Facebook Connect button");
        }
    }

    public boolean isFaceBookConnectDisplayed() throws Exception {
        try{
            return WebElementHelper.isElementDisplayed(getElement("FaceBookConnectBtn"));
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Facebook Connect button");
        }
    }

    public void closeAdds(){
        try{
            List<WebElement> closeButtons = getElements("AddClose");
            if(closeButtons.size() > 0){
                closeButtons.get(0).click();
                WaitHelper.waitForPageLoad();
            }
        }catch (Exception e){
           //Do Nothing
        }
    }
}
