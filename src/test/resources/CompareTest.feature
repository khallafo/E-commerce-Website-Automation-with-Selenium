Feature: Compare Products Test

  Scenario: Verify that products can be compared
    Given the user is on the login page
    When the user navigates to the mobile category
    And the user compares products
    Then the comparison page should display correct information
    And the user should be able to close the comparison popup
    And the user should be back on the mobile category page
