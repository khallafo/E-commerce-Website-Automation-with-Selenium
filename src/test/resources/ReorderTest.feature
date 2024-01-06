Feature: Verify Reordering a Product

  Scenario: User verifies the reorder of a product
    Given the user is on the login page
    When User opens the login or create account page
    And User logs in with email "roma89@mail.com" and password "000000"
    And User navigates to the Account page
    And User reorders a product
    And User updates the quantity to 10
    And User proceeds to checkout
    And User selects billing information
    And User sets the checkout information
    And User proceeds with the checkout process
    And User completes the order
    Then User should see the order success message with order details
