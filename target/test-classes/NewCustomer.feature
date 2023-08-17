Feature: New Customer and Account Creation

  Scenario: Sign in and create a new customer with valid details
    Given the user is on the login page1
    When the user signs in with valid credentials1
    Then the manager ID should be displayed1

    Given the user is on the Add Customer page
    When the user creates a new customer with details
    Then the customer ID is generated

    Given the user is on the Add Account page
    When the user adds an account with customer ID and balance
    Then the account should be added successfully

