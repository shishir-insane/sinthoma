Feature: User
  Scenario: a few User
    Given I have users in my db
    When I wait hour
    Then my db should growl