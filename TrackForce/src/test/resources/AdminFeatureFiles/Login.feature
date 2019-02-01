Feature: Tests for the login page on Chrome

  Background:
    Given I connect to trackforce
    Given the login page loads
  
  Scenario: Log in as Administrator
    When I submit the correct admin login information
    Then I should be taken to the home page
 
  Scenario: Log in as Staging
    When I submit the correct manager login information
    Then I should be taken to the home page

  Scenario: Log in as Sales-Delivery
    When I submit the correct delivery login information
    Then I should be taken to the home page

  Scenario: Log in as a Trainer
    When I submit the correct trainer login information
    Then I should be taken to the trainer home page

  Scenario: Log in as an Associate
    When I submit the correct associate login information
    Then I should be taken to the associate home page

  Scenario: Fail to enter username
    When I submit a correct password without a username
    Then I should remain on the login page

  Scenario: Fail to enter password
    When I submit a correct username without a password
    Then I should remain on the login page

  Scenario: Incorrect Password
    When I submit a correct username with an incorrect password
    Then I should remain on the login page

  Scenario: Incorrect Username
    When I submit a correct password with an incorrect username
    Then I should remain on the login page

  Scenario: Incorrect login
    When I submit an incorrect password with an incorrect username
    Then I should remain on the login page

  Scenario: Log out as Admin
    When I submit the correct admin login information
    And I click Log out
    Then I should be on the login page

