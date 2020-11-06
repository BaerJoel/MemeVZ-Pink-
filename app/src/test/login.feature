Feature: Logging in
  As a user
  I want to login with my details
  So that I can configure the server

  Scenario: User is not logged in
    Given no current user
    When I access a page
    Then the login form should be shown
    And I should not be authorized

  Scenario Outline: User enters wrong password
    Given a registered user with the email <email> with password <password> exists
    And I am on the "/login" page
    When I fill in "email" <email>
    And I fill in "password" <password>
    And I press "Sign in"
    Then the login form should be shown
    And I should see "Bad email or password"
    And I should not be signed in
    Examples:
      | email        | password |
      | abv@mail.com | 123456   |

  Scenario Outline: User is registered and enters correct password
    Given a registered user with the email <email> with password <password> exists
    And I am on the "/login" page
    When I fill in email <email>
    And I fill in "wrongPassword" <wrongPassword>
    And I press "Sign in"
    Then the login form should be shown
    And I should see "User is logged in"
    And I should  be authorized
    And I should  be signed in
    Examples:
      | email        | password | wrongPassword |
      | abv@mail.com | 123456   | 12345         |

  Scenario Outline: User enters not existing email
    Given a user with the email <email> with password <password> exists
    And I am on the "/login" page
    When I fill in "wrongEmail" <wrongEmail>
    And I fill in "password" <password>
    And I press "Sign in"
    Then the login form should be shown
    And I should see "User does not exist"
    And I should not be signed in
    Examples:
      | email        | password | wrongEmail    |
      | abv@mail.com | 123456   | sbv@mail.com  |