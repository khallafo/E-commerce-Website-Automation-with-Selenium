Feature: Verify Mobile Product Sorting

  Scenario: User verifies mobile product sorting
    Given the user is on the login page
    When User navigates to the Mobile page
    Then User should see the Mobile category
    And User verifies the product sorting
