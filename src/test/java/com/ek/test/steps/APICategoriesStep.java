package com.ek.test.steps;

import com.ek.test.framework.ServiceImplementation.GetCategories;
import com.ek.test.framework.helpers.PropertyHelper;
import com.ek.test.framework.helpers.RestServiceHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class APICategoriesStep {
    private static boolean idFlag = false;
    private static boolean nameFlag = false;

    /*TC01*/
    @Given("^Categories REST Service is available$")
    public void categories_REST_Service_is_available() throws Throwable {
        Assert.assertTrue("ERROR: Service NOT available",RestServiceHelper.getRESTserviceResponse(PropertyHelper.getProperty("categoryServiceURL")));
    }

    @When("^I hit the Service to get all available categories$")
    public void i_hit_the_Service_to_get_all_available_categories() throws Throwable {
        GetCategories.callRESTAPIGetCategories();
    }

    @Then("^I should get the success response code '(\\d+)' from the 'Categories' Service$")
    public void i_should_get_the_success_response_code_from_the_Categories_Service(int expectedCode) throws Throwable {
        Assert.assertTrue("ERROR: Service Status Code NOT proper",GetCategories.verifyServiceStatusCode(expectedCode));
    }

    @Then("^There should be (\\d+) child nodes under the tag 'children_data'$")
    public void there_should_be_child_nodes_under_the_tag_children_data(int expCount) throws Throwable {
        Assert.assertTrue("ERROR: Minimum Category Validation Failed",GetCategories.getCategoryCount() > expCount);
    }

    /*TC_02*/
    @Given("^I have a successful response from 'Categories' Service$")
    public void i_have_a_successful_response_from_Categories_Service() throws Throwable {
        categories_REST_Service_is_available();
        i_hit_the_Service_to_get_all_available_categories();
    }

    @When("^I verify the 'id' tag under all the nodes$")
    public void i_verify_the_id_tag_under_all_the_nodes() throws Throwable {
        idFlag = GetCategories.verifyIdTagAvailable();
    }

    @Then("^I should able to see the 'id' tag under all the nodes$")
    public void i_should_able_to_see_the_id_tag_under_all_the_nodes() throws Throwable {
        Assert.assertTrue("ERROR: ID tag value check failed",idFlag);
    }

    /*TC03*/
    @When("^I verify the 'name' tag under all the nodes$")
    public void i_verify_the_name_tag_under_all_the_nodes() throws Throwable {
        nameFlag = GetCategories.verifyNameTagAvailable();
    }

    @Then("^I should able to see the 'name' tag under all the nodes$")
    public void i_should_able_to_see_the_name_tag_under_all_the_nodes() throws Throwable {
        Assert.assertTrue("ERROR: NAME tag value check failed",nameFlag);
    }

}
