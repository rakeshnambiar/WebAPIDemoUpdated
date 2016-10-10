Feature: To verify the various functionalities in the Gift Wrapping REST API

  @TC01_APIGiftWrappingTest   @NoBrowser
  Scenario: To check minimum '1' item is available or not
    When Gift Wrapper REST Service is available
    When I hit to service with proper credential to get the available Gift Wrappers
      |98ues8ev9h4483lakbkv5d3iol0h4cv2|3r0atn649l383pn9p9ho4qdismnm4pth|v0c6arxqo8pu54s4xqwjh5nt6nnkfupy|g17mq5w59v9epkt92m0cd4v10rh3vudf|
    Then Service should return the successful response code '200'
    And Minimum '1' item should available under the 'Item' array node


  @TC02_APIGiftWrappingTest   @NoBrowser
  Scenario: To check 'base_currency' in the response
    When I have a successful response from Gift Wrapping REST API
      |98ues8ev9h4483lakbkv5d3iol0h4cv2|3r0atn649l383pn9p9ho4qdismnm4pth|v0c6arxqo8pu54s4xqwjh5nt6nnkfupy|g17mq5w59v9epkt92m0cd4v10rh3vudf|
    When I check the 'base_currency' tag in the response
    Then I should see the tag without null value


  @TC03_APIGiftWrappingTest   @NoBrowser
  Scenario: To check 'base_price' in the response
    When I have a successful response from Gift Wrapping REST API
      |98ues8ev9h4483lakbkv5d3iol0h4cv2|3r0atn649l383pn9p9ho4qdismnm4pth|v0c6arxqo8pu54s4xqwjh5nt6nnkfupy|g17mq5w59v9epkt92m0cd4v10rh3vudf|
    When I check the 'base_price' tag in the response
    Then I should see the Best Price tag without null value