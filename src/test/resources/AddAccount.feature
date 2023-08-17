Feature: Add Account

  Scenario: Sign in and add a new account
    Given the user is on the login page
    When the user signs in with valid credentials
    Then the manager ID should be displayed

    Given the user is on the Add Account page
    When the user adds an account with account number and balance
    Then the account should be added successfully
