#Author: nicholas.e.mccomb@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Tests for the login page

  @loginTag1
  Scenario: Log in as Administrator
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  @loginTag2
  Scenario: Log in as Staging
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  @loginTag3
  Scenario: Log in as Sales-Delivery
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  @loginTag4
  Scenario: Log in as a Trainer
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page
  	
  @loginTag5
  Scenario: Log in as an Associate
  	Given the login page loads
  	When I enter the correct admin login information
  	And I click Submit
  	Then I should be taken to the home page 	
  	
  @loginTag6 
	Scenario: Fail to enter username
  	Given the login page loads
		When I enter a correct password without a username
		And I click Submit
		Then an error should appear

  @loginTag7
	Scenario: Fail to enter password
  	Given the login page loads
		When I enter a correct username without a password
		And I click Submit
		Then an error should appear

  @loginTag8
	Scenario: Incorrect Username
  	Given the login page loads
		When I enter a correct password with an incorrect password
		And I click Submit
		Then an error should appear

  @loginTag9
	Scenario: Incorrect Password
  	Given the login page loads
		When I enter a correct password with an incorrect username
		And I click Submit
		Then an error should appear

  @loginTagX
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
