Feature: Rate Meme
  As a user
  I want to rate a meme
  So that I can show whether I like it or not

  Scenario: User wants to like a meme by pressing the button
    Given current user
    And current meme
    When I press "like"
    Then the meme should get a like
    And a new meme appears

    Scenario: User wants to like a meme by swiping to the right
      Given current user
      And current meme
      When I swipe to the right
      Then the meme should get a like
      And a new meme appears

  Scenario: User wants to dislike a meme by pressing the button
    Given current user
    And current meme
    When I press "dislike"
    Then the meme should get a dislike
    And a new meme appears

  Scenario: User wants to dislike a meme by swiping to the right
    Given current user
    And current meme
    When I swipe to the lift
    Then the meme should get a dislike
    And a new meme appears