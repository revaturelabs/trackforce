Feature: Home Page Bottom Info Banner
  
  Background:
  	Given I connect to trackforce on Firefox
    Given the login page loads

  Scenario: I click on the Revature telephone number
    When I click on the telephone link
    Then I should see the telephone number link open on a browser
    
  Scenario: I click on the Revature fax number
    When I click on the fax link
    Then I should see the telephone number link open on a browser


  Scenario: I click on the Revature email
    When I click on the email link
    Then I should see the email link open on a browser

  Scenario: I click on the Revature website
    When I click on the website link
    Then I should see the Revature website link open on a browser