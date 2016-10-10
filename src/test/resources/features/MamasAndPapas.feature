Feature: To verify the various functionalities in the Mamasandpapas.ae website
  As a User
  I should be able to connect via Facebook
  To MamasAndPapas Website

  @TC01_MamasPapasWebTest
  Scenario Outline: To check the Facebook Connect for the authorized users
    Given I am a registered User of "mamasandpapas.ae" website
    And I navigate to the "mamasandpapas.ae" Website
    When 'Sign In/Register' button is available on the Home Page
    And I clicks on 'Sign In/Register' button
    And Clicks on the 'Facebook Connect' button
    And I enter the "<Username>", "<Password>" in the Login page
    And Click on the Login Button
    Then I should successfully logged into the Mamasandpapas home page on facebook

    Examples:
      |Username                                 |Password |
      |ktwxjgb_sharpeman_1465123980@tfbnw.net   |1234qwe  |
      |maioiik_smithwitz_1465123955@tfbnw.net   |1234qwe  |
      |zointpr_thurnson_1465123943@tfbnw.net    |1234qwe  |
      |open_reyhddn_user@tfbnw.net              |1234qwe  |

  @TC02_MamasPapasWebTest
  Scenario: To check the Search functionality with Valid data
    Given I am a registered User of "mamasandpapas.ae" website
    And I navigate to the "mamasandpapas.ae" Website
    When I perform a Search with value "red"
    Then I should able to see the Search Result page along with matching results
    And "View More" button should be available if Search result count is more than "12"

  @TC03_MamasPapasWebTest
  Scenario: To check the View More button funtionality
    Given I am a registered User of "mamasandpapas.ae" website
    And I navigate to the "mamasandpapas.ae" Website
    When I perform a Search with value "red" which always gives me more than "12" records in the Search Result
    Then I should able to see the "View More" button on the Search Result page
    When I clicks on "View More" button
    Then More results should get populated on the Search Result page

  @TC04_MamasPapasWebTest
  Scenario: To check the Search functionality with Invalid data
    Given I am a registered User of "mamasandpapas.ae" website
    And I navigate to the "mamasandpapas.ae" Website
    When I perform a Search with value "noitemwiththiskeyword"
    Then I should see "we're sorry we couldn't find anything to match your search" alert message on the screen

  @TC05_MamasPapasWebTest
  Scenario: To check the product Details page
    Given I am on Search Result page
      |mamasandpapas.ae|red|
    And Clicks on the second item from the Search Result page
    Then I should able to see the 'Product Details' page
    And 'Title' should be available
    And At least one image should be available for the selected item
    And Quantity Selection bar should be displyed
    And Add to Wishlist button should also displayed


  @TC06_MamasPapasWebTest
  Scenario: To check the Multiple Thumbnails in the Product Details page
    Given I am on Search Result page
      |mamasandpapas.ae|red|
    When Clicks on the second item from the Search Result page
    Then I should be able to see the multiple thumbnails if available
    And Each image should unique

  @TC07_MamasPapasWebTest
  Scenario: To check the 'Add to Wishlist' functionality without Login
    Given I am on Search Result page
      |mamasandpapas.ae|red|
    When Clicks on the first item from the Search Result page
    And Trying to purchase more than '10' Quantity of the selected item
    Then System should not accept the value more than '10'

  @TC08_MamasPapasWebTest
  Scenario: To check the 'Add to Wishlist' functionality without Login
    Given I am on Search Result page
      |mamasandpapas.ae|red|
    When Clicks on the first item from the Search Result page
    And Clicks on 'ADD TO WISHLIST' button
    Then I should able to see the Login popup

  @TC09_MamasPapasWebTest
  Scenario: To check the 'Add to Wishlist' button toggle functionality for a Logged in User
    Given I am successfully logged in to the "mamasandpapas.ae" website over facebook
      |ktwxjgb_sharpeman_1465123980@tfbnw.net|1234qwe|
    When I perform a Search with value "red"
    And Clicks on the first item from the Search Result page
    And Clicks on 'ADD TO WISHLIST' button
    Then "ADD TO WISHLIST" button should toggle to "REMOVE FROM WISHLIST"