Feature: Balance Enquiry

  Scenario: Sign in and perform balance enquiry
    Given the user is on the login page
    When the user signs in with valid credentials
    Then the manager ID should be displayed

    Given the user is on the Balance Enquiry page
    When the user performs a balance enquiry with account number
    Then the balance information should be displayed
