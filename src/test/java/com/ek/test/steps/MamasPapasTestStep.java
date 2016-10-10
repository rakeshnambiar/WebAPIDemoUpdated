package com.ek.test.steps;

import com.ek.test.framework.helpers.WaitHelper;
import com.ek.test.framework.helpers.WebElementHelper;
import com.ek.test.framework.hooks.ScenarioHook;
import com.ek.test.pages.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Created by Administrator on 9/9/2016.
 */
public class MamasPapasTestStep {
    private static HomePage homePage;
    private static FaceBookPage faceBookPage;
    private static SearchPage searchPage;
    private static int defaultCount;
    private static ProductDetailPage productPage;

    @Before
    public void setUp(){
        homePage = PageFactory.getHomePage();
        faceBookPage = PageFactory.getFaceBookPage();
        searchPage = PageFactory.getSearchPage();
        productPage = PageFactory.getProductPage();
    }


    /*TC_01*/
    @Given("^I am a registered User of \"(.*?)\" website$")
    public void i_am_a_registered_User_of_website(String webURL) throws Throwable {
        ScenarioHook.getScenario().write("User Login initiated to - "+webURL);
        WaitHelper.waitFor(5000);
        ScenarioHook.takeScreenshot();
        homePage.closeAdds();
    }

    @Given("^I navigate to the \"(.*?)\" Website$")
    public void i_navigate_to_the_Website(String webURL) throws Throwable {
        Assert.assertTrue("ERROR: Website Address Check failed", PageFactory.getDriver().getCurrentUrl().contains(webURL));
    }
    @When("^I clicks on 'Sign In/Register' button$")
    public void i_clicks_on_Sign_In_Register_button() throws Throwable {
        homePage.clickOnSignLink();
    }

    @When("^'Sign In/Register' button is available on the Home Page$")
    public void sign_In_Register_button_is_available_on_the_Home_Page() throws Throwable {
        ScenarioHook.takeScreenshot();
        Assert.assertTrue("ERROR: Sign In Link NOT available on the Home Page",homePage.isSignInLinkDisplayed());
    }

    @When("^Clicks on the 'Facebook Connect' button$")
    public void clicks_on_the_Facebook_Connect_button() throws Throwable {
        homePage.clicksOnFaceBookConnectButton();
        ScenarioHook.takeScreenshot();
    }

    @When("^I enter the \"(.*?)\", \"(.*?)\" in the Login page$")
    public void i_enter_the_in_the_Login_page(String username, String password) throws Throwable {
        faceBookPage.enterUserCredential(username,password);
        ScenarioHook.takeScreenshot();
    }

    @When("^Click on the Login Button$")
    public void click_on_the_Login_Button() throws Throwable {
        faceBookPage.clicksOnFaceBookLogin();
        ScenarioHook.takeScreenshot();
    }

    @Then("^I should successfully logged into the Mamasandpapas home page on facebook$")
    public void i_should_successfully_logged_into_the_Mamasandpapas_home_page() throws Throwable {
        ScenarioHook.takeScreenshot();
        Assert.assertTrue("ERROR: Homepage over facebook NOT available",faceBookPage.verifyHomePage());
    }

    /*TC02*/
    @When("^I perform a Search with value \"(.*?)\"$")
    public void i_perform_a_Search_with_value(String text) throws Throwable {
        searchPage.performItemSearch(text);
        ScenarioHook.takeScreenshot();
    }

    @Then("^I should able to see the Search Result page along with matching results$")
    public void i_should_able_to_see_the_Search_Result_page_along_with_matching_results() throws Throwable {
        WaitHelper.waitForPageLoad();
        WaitHelper.waitForJStoLoad();
        Assert.assertTrue("ERROR: Search Result Page NOT available",searchPage.isSearchResultPageDisplayed());
        Assert.assertTrue("ERROR: No results found for a valid Search criteria",searchPage.getSearchResultCount() > 0);
        ScenarioHook.takeScreenshot();
    }

    @Then("^\"(.*?)\" button should be available if Search result count is more than \"(.*?)\"$")
    public void button_should_be_available_if_Search_result_count_is_more_than(String buttonName, String defRecCount) throws Throwable {
        Assert.assertTrue("ERROR: Verification failed for - "+buttonName,searchPage.isViewMoreOptionDisplayed(Integer.parseInt(defRecCount)));
        ScenarioHook.takeScreenshot();
    }

    /*TC_03*/
    @When("^I perform a Search with value \"(.*?)\" which always gives me more than \"(.*?)\" records in the Search Result$")
    public void i_perform_a_Search_with_value_which_always_gives_me_more_than_records_in_the_Search_Result(String searchText, String defRecCount) throws Throwable {
        searchPage.performItemSearch(searchText);
        ScenarioHook.takeScreenshot();
    }

    @Then("^I should able to see the \"(.*?)\" button on the Search Result page$")
    public void i_should_able_to_see_the_button_on_the_Search_Result_page(String button) throws Throwable {
        searchPage.verifyViewMoreButton();
    }

    @When("^I clicks on \"(.*?)\" button$")
    public void i_clicks_on_button(String button) throws Throwable {
        defaultCount = searchPage.getSearchResultCount();
        ScenarioHook.getScenario().write("Default record Count - "+defaultCount);
        ScenarioHook.takeScreenshot();
        searchPage.scrollDownSearchResultPage();
        searchPage.clicksViewMoreButton();
    }

    @Then("^More results should get populated on the Search Result page$")
    public void more_results_should_get_populated_on_the_Search_Result_page() throws Throwable {
        Assert.assertTrue("ERROR: Load More feature NOT working as expected",searchPage.verifyLoadMoreFeature(defaultCount));
        ScenarioHook.takeScreenshot();
    }

    /*TC_04*/
    @Then("^I should see \"(.*?)\" alert message on the screen$")
    public void i_should_see_alert_message_on_the_screen(String expectedMsg) throws Throwable {
        Assert.assertTrue("ERROR: Alert Message NOT same as Expected",searchPage.verifyAlertMessage(expectedMsg));
    }


    /*TC_05*/
    @When("^Clicks on the second item from the Search Result page$")
    public void clicks_on_the_first_item_from_the_Search_Result_page() throws Throwable {
        searchPage.selectSecondItem();
    }

    @Then("^I should able to see the 'Product Details' page$")
    public void i_should_able_to_see_the_Product_Details_page() throws Throwable {
        Assert.assertTrue("ERROR: Product Details page NOT available",productPage.isProductDetailsPageDisplayed());
    }

    @Then("^'Title' should be available$")
    public void title_should_be_available() throws Throwable {
        Assert.assertTrue("ERROR: Product Title NOT available",productPage.verifyProductTitle());
    }

    @Then("^At least one image should be available for the selected item$")
    public void at_least_one_image_should_be_available_for_the_selected_item() throws Throwable {
        if(productPage.getProductImages().size() < 1){
            ScenarioHook.getScenario().write("Thumbnails NOT available");
            Assert.assertTrue("ERROR: Default Image NOT proper",productPage.verifyDefaultImageSource());
        }
        else
        {
            productPage.scrollDownThumbnails();
            ScenarioHook.getScenario().write("Multiple Images are found for the selected product");
        }
        ScenarioHook.takeScreenshot();
    }

    @Then("^Quantity Selection bar should be displyed$")
    public void quantity_Selection_bar_should_be_displyed() throws Throwable {
        Assert.assertTrue("ERROR: Quantity Bar NOT found",productPage.isQuantityBarDisplayed());
    }

    @Then("^Add to Wishlist button should also displayed$")
    public void add_to_Wishlist_button_should_also_displayed() throws Throwable {
        Assert.assertTrue("ERROR: Quantity Bar NOT found",productPage.isAddToFavouriteDisplayed());
        productPage.scrollDownFavButton();
        ScenarioHook.takeScreenshot();
    }

    @Given("^I am on Search Result page$")
    public void i_am_on_Search_Result_page(List<String> searchCriteria) throws Throwable {
        i_am_a_registered_User_of_website(searchCriteria.get(0));
        i_navigate_to_the_Website(searchCriteria.get(0));
        i_perform_a_Search_with_value(searchCriteria.get(1));
    }

    @Given("^Clicks on the first item from the Search Result page$")
    public void clicks_on_the_first2_item_from_the_Search_Result_page() throws Throwable {
        searchPage.selectFirstItem();
    }

    /*TC_06*/
    @Then("^I should be able to see the multiple thumbnails if available$")
    public void i_should_be_able_to_see_the_multiple_thumbnails_if_available() throws Throwable {
        at_least_one_image_should_be_available_for_the_selected_item();
    }

    @Then("^Each image should unique$")
    public void each_image_should_unique() throws Throwable {
        Assert.assertTrue("ERROR: Duplicate Image Found",productPage.compareImageSource());
    }

    /*TC_07*/
    @When("^Trying to purchase more than '(\\d+)' Quantity of the selected item$")
    public void trying_to_purchase_more_than_Quantity_of_the_selected_item(int maxQuantity) throws Throwable {
        productPage.addMaximumQuantity(maxQuantity);
    }

    @Then("^System should not accept the value more than '(\\d+)'$")
    public void system_should_not_accept_the_value_more_than(int maxQuantity) throws Throwable {
        Assert.assertTrue("ERROR: MAXIMUM Quantity Check Failed",productPage.verifyMaximumQuantity(maxQuantity));
    }

    /*TC_08*/
    @When("^Clicks on 'ADD TO WISHLIST' button$")
    public void clicks_on_ADD_TO_WISHLIST_button() throws Throwable {
        WaitHelper.waitForPageLoad();
        productPage.clickAddToFavouriteButton();
    }

    @Then("^I should able to see the Login popup$")
    public void i_should_able_to_see_the_Login_popup() throws Throwable {
        ScenarioHook.takeScreenshot();
        Assert.assertTrue("ERROR: Login Popup NOT available", homePage.isFaceBookConnectDisplayed());
    }

    /*TC_09*/
    @Given("^I am successfully logged in to the \"(.*?)\" website over facebook$")
    public void i_am_successfully_logged_in_to_the_website_over_facebook(String webURL, List<String> loginCredential) throws Throwable {
        i_navigate_to_the_Website(webURL);
        i_clicks_on_Sign_In_Register_button();
        clicks_on_the_Facebook_Connect_button();
        faceBookPage.enterUserCredential(loginCredential.get(0),loginCredential.get(1));
        ScenarioHook.takeScreenshot();
        click_on_the_Login_Button();
        ScenarioHook.takeScreenshot();
    }

    @Then("^\"(.*?)\" button should toggle to \"(.*?)\"$")
    public void button_should_toggle_to(String previousBtnText, String newBtnText) throws Throwable {
        Assert.assertTrue("ERROR: Toggle Functionality Failed",productPage.verifyToggleFunctionality(previousBtnText,newBtnText));
        ScenarioHook.takeScreenshot();
    }
}
