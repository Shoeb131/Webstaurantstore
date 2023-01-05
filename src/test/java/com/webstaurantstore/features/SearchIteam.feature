
Feature: Searching item in the webstaurantstore.com site
 

  Scenario: Searching stainless work table
    Given I navigate to the webstaurantstore site
    When I search for "stainless work table"
    And Verify search result has the word "Table" in its title
    And Add the last of found items to Cart
    Then I Empty the Cart