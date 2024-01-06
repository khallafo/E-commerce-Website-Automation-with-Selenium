Feature: Verify Product Purchase

  Scenario: User verifies the purchase of products
    Given the user is on the login page
    When User opens the login or create account page
    And User logs in with email "mahmoudkhallaf277@gmail.com" and password "123456"
    And User navigates to the Wishlist page
    And User adds products to the cart
    And User proceeds to checkout
    And User selects billing information if available
    And User sets the checkout information
    And User verifies shipping cost
    And User proceeds with the checkout process
    And User verifies the total cost
    And User completes the order
    Then User should see a success message with order details
