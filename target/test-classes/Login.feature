Feature: Log In Scenarios

  Scenario: User signs in with valid credentials
    Given the user is on the login page
    When the user signs in with valid credentials
    Then the manager ID should be displayed
    And a screenshot should be taken

  Scenario: User signs in with invalid credentials
    Given the user is on the login page
    When the user signs in with invalid credentials
    Then an alert should be displayed