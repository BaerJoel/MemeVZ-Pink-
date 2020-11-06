Feature: Logging out
  As a user
  I want to log out
  So that I am on the "/login" page

  Scenario: User wants to log out
    Given current user
    When press "log out"
    Then the login form should be shown
    And I should be logged out