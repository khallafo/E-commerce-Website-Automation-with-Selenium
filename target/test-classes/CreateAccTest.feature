Feature: Create Account Functionality

  Scenario: User successfully creates an account
    Given the user is on the login page
    When User navigates to the Create Account page
    And User enters first name, last name, username, email, password, and confirm password
    And User submits the registration form
    Then User should be redirected to the Dashboard
    And User should see a success message

