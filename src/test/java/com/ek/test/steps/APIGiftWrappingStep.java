package com.ek.test.steps;

import com.ek.test.framework.ServiceImplementation.GiftWrappingImpl;
import com.ek.test.framework.helpers.PropertyHelper;
import com.ek.test.framework.helpers.RestServiceHelper;
import com.ek.test.framework.hooks.ScenarioHook;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Administrator on 9/14/2016.
 */
public class APIGiftWrappingStep {

    private static String header1;
    private static String header2;
    private static String header3;
    private static String header4;
    private static boolean currencyFlg;
    private static boolean priceFlg;

    /*TC01*/
    @When("^Gift Wrapper REST Service is available$")
    public void gift_Wrapper_REST_Service_is_available() throws Throwable {
       ScenarioHook.scenario.write("Service Url - "+PropertyHelper.getProperty("giftWrapperServiceURL"));
    }

    @When("^I hit to service with proper credential to get the available Gift Wrappers$")
    public void i_hit_to_service_with_proper_credential_to_get_the_available_Gift_Wrappers(List<String> authorization) throws Throwable {
        header1 = authorization.get(0);
        header2 = authorization.get(1);
        header3 = authorization.get(2);
        header4 = authorization.get(3);
        GiftWrappingImpl.callRESTAPIGiftWrapper(header1,header2,header3,header4);
    }

    @Then("^Service should return the successful response code '(\\d+)'$")
    public void service_should_return_the_successful_response_code(int statusCode) throws Throwable {
        Assert.assertTrue("ERROR: Unexpected Service Response Code", GiftWrappingImpl.verifyServiceStatusCode(statusCode));
    }

    @Then("^Minimum '(\\d+)' item should available under the 'Item' array node$")
    public void minimum_item_should_available_under_the_Item_array_node(int expItemCount) throws Throwable {
        int actualItems = GiftWrappingImpl.getItemsCount();
        Assert.assertTrue("ERROR: Expected Minimum Items NOT found", actualItems >=expItemCount);
        ScenarioHook.getScenario().write("Available Items - "+actualItems);
    }

    /*TC02*/
    @When("^I have a successful response from Gift Wrapping REST API$")
    public void i_have_a_successful_response_from_Gift_Wrapping_REST_API(List<String> authorization) throws Throwable {
        i_hit_to_service_with_proper_credential_to_get_the_available_Gift_Wrappers(authorization);
        service_should_return_the_successful_response_code(200);
    }

    @When("^I check the 'base_currency' tag in the response$")
    public void i_check_the_base_currency_tag_in_the_response() throws Throwable {
        currencyFlg = GiftWrappingImpl.verifyBaseCurrency();
    }

    @Then("^I should see the tag without null value$")
    public void i_should_see_the_tag_without_null_value() throws Throwable {
        Assert.assertTrue("ERROR: Base Currency Code Validation failed",currencyFlg);
    }

    /*TC03*/
    @When("^I check the 'base_price' tag in the response$")
    public void i_check_the_base_price_tag_in_the_response() throws Throwable {
        priceFlg = GiftWrappingImpl.verifyBasePrice();
    }

    @Then("^I should see the Best Price tag without null value$")
    public void i_should_see_the_Best_Price_tag_without_null_value() throws Throwable {
        Assert.assertTrue("ERROR: Base Price Validation failed",priceFlg);
    }
}
