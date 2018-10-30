Feature: Tests for the login page

 Background:
    Given I connect to trackforce
    And the login page loads

  Scenario: Log out as Admin
    When I submit the correct admin login information
    And I click Log out
    Then I should be on the login page

