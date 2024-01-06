Feature: Verify Saving an Order as PDF

  Scenario: User verifies saving an order as PDF
    Given the user is on the login page
    When User opens the login or create account page
    And User logs in with email "mahmoudkhallaf277@gmail.com" and password "123456"
    And User navigates to the Account page
    And User opens the Orders page
    Then User should see an order with status "Pending"
    When User views an order
    And User prints the order as PDF
    And User switches to the PDF print window
    And User takes a screenshot of the PDF
    Then User should have a screenshot of the PDF saved
