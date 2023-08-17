Feature: Verify Sony Phone Price Equality

  Scenario: User verifies that Sony phone prices are equal
    Given the user is on the login page
    When User navigates to the Mobile page
    And User notes the price of a Sony phone
    And User opens the Sony phone's details page
    Then User should see that the price is the same

