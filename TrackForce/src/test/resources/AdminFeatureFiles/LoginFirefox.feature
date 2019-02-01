#Author: your.email@your.domain.com
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
@FirefoxTest
Feature: Tests login for each user type on Firefox

	Background: 
		Given I connect to trackforce on Firefox
		Given the login page loads
  
  Scenario Outline: 
  	When I input login credential username as "<username> "
  	And input credential password as "<password>"
  	Then login succeeds and navigates away from the login page
  	
  Examples:
   |username| password|
   |TestAdmin| TestAdmin|
   |Trainer| Trainer|
   |bobstage| bobstage|
   |cyril| cyril|
   |salestest| salestest|
   
   #Actually tests both incorrect input and input where only one field is incorrect 
  Scenario Outline: 
  	When I input an incorrect username as "<badusername>"
  	And I input an incorrect password as "<badpassword>"
  	Then I should be on the login page
  	
  Examples: 
  	|badusername|badpassword|
  	|notAUsername|notAPassword|
  	|""|""|
  	|null|null|
  	|bobstage|null|
  	|null|bobstage|
  	|TestAdmin|bobstage|
  	|cyril|TestAdmin|
  	