Feature: Home Page Tab
  I want to use this template for my feature file
  
  Background:
  	Given I connect to trackforce
    Given the login page loads

  Scenario: I click on the Revature telephone number
    When I click on the telephone link
    Then I should see the telephone number link open on a browser

  Scenario: I click on the Revature email
    When I click on the email link
    Then I should see the email link open on a browser

  Scenario: I click on the Revature website
    When I click on the website link
    Then I should see the Revature website link open on a browser