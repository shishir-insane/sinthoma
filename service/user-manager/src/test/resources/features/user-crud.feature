# User Create Feature Definition Template
Feature: Create a user with userName john.doe

  Scenario: Save a new user with mandatory attributes
    Given client wants to create new user with the following attributes
      | userId | userName	 | firstName | lastName | emailId            | password | imagePath |
      |   1000 | john.doe  | John      | Doe      | john.doe@email.com |   123456 |           |
    When POST call to endpoint /users with given attributes is made
    Then the response is successful
    And it has 1 user with userName john.doe

  Scenario: Fetch a user using primary key userName
    Given client wants to get a user with userName john.doe
    When POST call to endpoint /users with id 1000 is made
    Then the response is successful
    And it has 1 user with userName john.doe
