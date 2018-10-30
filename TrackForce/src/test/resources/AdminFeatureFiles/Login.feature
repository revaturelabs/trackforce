Feature: Tests for the login page

  Scenario: Log in as Administrator
    Given I connect to trackforce
    And the login page loads
    When I submit the correct admin login information
    And I click Submit
    Then I should be taken to the home page

  Scenario: Log in as Staging
    Given I connect to trackforce
    And the login page loads
    When I submit the correct manager login information
    And I click Submit
    Then I should be taken to the home page

  Scenario: Log in as Sales-Delivery
    Given I connect to trackforce
    And the login page loads
    When I submit the correct delivery login information
    And I click Submit
    Then I should be taken to the home page

  Scenario: Log in as a Trainer
    Given I connect to trackforce
    And the login page loads
    When I submit the correct trainer login information
    And I click Submit
    Then I should be taken to the trainer home page

  Scenario: Log in as an Associate
    Given I connect to trackforce
    And the login page loads
    When I submit the correct associate login information
    And I click Submit
    Then I should be taken to the associate home page

  Scenario: Fail to enter username
    Given I connect to trackforce
    And the login page loads
    When I submit a correct password without a username
    And I click Submit
    Then I should remain on the login page

  Scenario: Fail to enter password
    Given I connect to trackforce
    And the login page loads
    When I submit a correct username without a password
    And I click Submit
    Then I should remain on the login page

  Scenario: Incorrect Password
    Given I connect to trackforce
    And the login page loads
    When I submit a correct username with an incorrect password
    And I click Submit
    Then I should remain on the login page

  Scenario: Incorrect Username
    Given I connect to trackforce
    And the login page loads
    When I submit a correct password with an incorrect username
    And I click Submit
    Then I should remain on the login page

  Scenario: Incorrect login
    Given I connect to trackforce
    And the login page loads
    When I submit an incorrect password with an incorrect username
    And I click Submit
    Then I should remain on the login page

  Scenario: Log out as Admin
    Given I connect to trackforce
    And the login page loads
    When I submit the correct admin login information
    And I click Submit
    And I click Log out
    Then I should be on the login page

