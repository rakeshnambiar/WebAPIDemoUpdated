Feature: To Verify the various functionalities of Categories REST API

  @TC01_APICategoriesTest  @NoBrowser
  Scenario: To Check 10 categories are available or not under 'children_data' tag
    Given Categories REST Service is available
    When I hit the Service to get all available categories
    Then I should get the success response code '200' from the 'Categories' Service
    And There should be 10 child nodes under the tag 'children_data'

  @TC02_APICategoriesTest  @NoBrowser
  Scenario: To Check mandatory tag 'id' under every node
    Given I have a successful response from 'Categories' Service
    When I verify the 'id' tag under all the nodes
    Then I should able to see the 'id' tag under all the nodes

  @TC03_APICategoriesTest  @NoBrowser
  Scenario: To Check mandatory tag 'name' under every node
    Given I have a successful response from 'Categories' Service
    When I verify the 'name' tag under all the nodes
    Then I should able to see the 'name' tag under all the nodes

