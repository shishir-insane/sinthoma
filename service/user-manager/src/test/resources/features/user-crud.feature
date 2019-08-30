# User Create Feature Definition Template
Feature: CRUD on user with username john.doe

  Scenario: Save a new user with mandatory attributes
    Given client wants to create new user with the following attributes
      | id | username	 | firstName | lastName | emailId            | password | imagePath |
      |   1000 | john.doe  | John      | Doe      | john.doe@email.com |   123456 |           |
    When POST call to endpoint /users with given attributes is made
    Then the response is successful
    And it has 1 user with parameter username as john.doe
    
  Scenario: Save a new user with duplicate username attributes
    Given client wants to create new user with the following attributes
      | userId | username	 | firstName | lastName | emailId            | password | imagePath |
      |   1001 | john.doe  | John      | Doe      | john.doe@email.com |   123456 |           |
    When POST call to endpoint /users with given attributes is made
    Then the response fails

  Scenario: Fetch a user using primary key username
    Given client wants to get a user with username john.doe
    When GET call to endpoint /users/1000 with id 1000 is made
    Then the response is successful
    And it has 1 user with parameter username as john.doe
    
  Scenario: Update password of an existing user with username attributes
    Given client wants to update an existing user with the following attributes
      | id | username	 | firstName | lastName | emailId            | password | imagePath |
      |   1000 | john.doe  | John      | Doe      | john.doe@email.com |   ABCDEF |           |
    When PUT call to endpoint /users/1000 with given attributes is made
    Then the response is successful
    And it has 1 user with parameter username as john.doe