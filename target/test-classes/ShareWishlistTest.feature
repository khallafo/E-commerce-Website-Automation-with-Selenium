Feature: Verify Sharing Wishlist

  Scenario: User verifies sharing a wishlist
    Given the user is on the login page
    When User opens the login or create account page
    And User logs in with email "mahmoudkhallaf277@gmail.com" and password "123456"
    And User navigates to the TV page
    And User adds a product to wishlist
    And User opens the Share Wishlist page
    And User shares the wishlist
    Then User should see a success message that the wishlist has been shared
