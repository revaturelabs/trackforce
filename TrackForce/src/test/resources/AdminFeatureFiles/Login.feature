#Author: nicholas.e.mccomb@gmail.com
Feature: Tests for the login page

  Background: 
    Given I connect to caliber
    And the login page loads

  Scenario: Log in as Administrator
    When I enter the correct admin login information
    And I click Submit
    Then I should be taken to the home page

  Scenario: Log in as Staging
    When I enter the correct manager login information
    And I click Submit
    Then I should be taken to the home page

  Scenario: Log in as Sales-Delivery
    When I enter the correct delivery login information
    And I click Submit
    Then I should be taken to the home page

  Scenario: Log in as a Trainer
    When I enter the correct trainer login information
    And I click Submit
    Then I should be taken to the home page

  Scenario: Log in as an Associate
    When I enter the correct associate login information
    And I click Submit
    Then I should be taken to the associate home page

  Scenario: Fail to enter username
    When I enter a correct password without a username
    And I click Submit
    Then I should remain on the login page

  Scenario: Fail to enter password
    When I enter a correct username without a password
    And I click Submit
    Then I should remain on the login page

  Scenario: Incorrect Password
    When I enter a correct username with an incorrect password
    And I click Submit
    Then I should remain on the login page

  Scenario: Incorrect Username
    When I enter a correct password with an incorrect username
    And I click Submit
    Then I should remain on the login page

  Scenario: Incorrect login
    When I enter an incorrect password with an incorrect username
    And I click Submit
    Then I should remain on the login page

  Scenario Outline: Log out as Admin
    When I enter the correct "<role>" login information
    And I click Submit
    And if I click Log out
    Then I should be on the login page

    Examples: 
      | role      |
      | admin     |
      | delivery  |
      | associate |
      | trainer   |
      | manager   |
