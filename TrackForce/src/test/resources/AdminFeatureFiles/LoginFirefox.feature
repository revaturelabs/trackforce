@FirefoxTest
Feature: Tests login for each user type on Firefox

	Background: 
		Given I connect to trackforce on Firefox
		Given the login page loads
  
  Scenario Outline: Each user type is able to login with their credentials
  	When I input login credential username as "<username> "
  	And input credential password as "<password>"
  	Then login succeeds and navigates away from the login page
  Examples:
   |username|password|
   |TestAdmin|TestAdmin|
   |Trainer|Trainer|
   |bobstage|bobstage|
   |cyril|cyril|
   |salestest|salestest|
   
  Scenario Outline: Incorrect login information or correct information that is mismatched between users
  	When I input an incorrect username as "<badusername>"
  	And I input an incorrect password as "<badpassword>"
  	Then I should be on the login page
  Examples: 
  	|badusername|badpassword|
  	|notAUsername|notAPassword|
  	| | |
  	|null|null|
  	|bobstage| |
  	| |bobstage|
  	|TestAdmin|Trainer|
  	|cyril|TestAdmin|
  	
  Scenario: I log in with correct information and logout
  	When I submit the correct admin login information
    And I click Log out
    Then I should be on the login page
  	