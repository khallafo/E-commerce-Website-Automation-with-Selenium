Feature: Cart Functionality Test

  Scenario: Verify that quantity validation works correctly
    Given the user is on the login page
    When the user adds a Sony mobile to the cart
    And the user sets the quantity to 15000
    Then an error message should be displayed for exceeding the quantity limit
    And the user's cart should be empty

  Scenario: Verify discount application on the cart
    Given the user is on the login page
    When the user logs in with valid credentials
    And the user adds an iPhone to the cart
    And the user applies the discount coupon code
    Then the grand total in the cart should be correct
