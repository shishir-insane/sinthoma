# User Creation Feature Definition Template
Feature: Create a user with id 1000
  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL
    Given user wants to create a new user with the following attributes
      | id  | firstName | lastName | emailId		 | password   | imagePath | userSettings  |
      | 100 | John	    | Doe	     | john.doe@email.com  			| 123456 		|       				|
    When user saves the new user 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'