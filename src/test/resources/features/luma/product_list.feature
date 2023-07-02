@smoke
Feature: Ascending order and flat vs best way

  Scenario: Webpage sorting list of product price and check shipping and tax rates
    When User lands on main page user hovers over Gear and clicks Bags
    Then User clicks on list view button and chooses price from sort by button and cliks show all
    And User validates whether the price option from the dropdown is showing in ascending order
    Then User adds one product to cart and clicks cart sign then clicks viewEdit cart link
    And User verifies country is United States and chooses state provides zipcode
      | State   | Wyoming |
      | zipCode | 60625   |
    Then User clicks on radio button for Flat Rate and Best Way retrieves Order Total for both



