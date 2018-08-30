Feature: Home Page Tab
  I want to use this template for my feature file

 Background: 
    Given I connect to caliber

  Scenario: I click on the Revature telephone number
    Given I navigate to the Home Page
    When I click on the telephone link
    Then I should see the telephone number link open on a browser

  Scenario: I click on the Revature email
   Given I navigate to the Home Page
    When I click on the email link
    Then I should see the email link open on a browser

  Scenario: I click on the Revature website
   Given I navigate to the Home Page
    When I click on the website link
    Then I should see the Revature website link open on a browser