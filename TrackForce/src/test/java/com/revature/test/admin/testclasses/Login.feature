#Author: nicholas.e.mccomb@gmail.com

Feature: Tests for the login page
	
	Background:
	Given I connect to caliber

  Scenario: Log in as Administrator
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  Scenario: Log in as Staging
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  Scenario: Log in as Sales-Delivery
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  Scenario: Log in as a Trainer
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  Scenario: Log in as an Associate
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page 	
  	
	Scenario: Fail to enter username
  	Given the login page loads
		When I enter a correct password without a username
		And I click Submit
		Then I should remain on the login page

	Scenario: Fail to enter password
  	Given the login page loads
		When I enter a correct username without a password
		And I click Submit
		Then I should remain on the login page

	Scenario: Incorrect Username
  	Given the login page loads
		When I enter a correct password with an incorrect username
		And I click Submit
		Then I should remain on the login page
		
	Scenario: Incorrect Password
  	Given the login page loads
		When I enter a correct password with an incorrect username
		And I click Submit
		Then I should remain on the login page

	Scenario: Incorrect login
  	Given the login page loads
		When I enter an incorrect password with an incorrect username
		And I click Submit
		Then I should remain on the login page

