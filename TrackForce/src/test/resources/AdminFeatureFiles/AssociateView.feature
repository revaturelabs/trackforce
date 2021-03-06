Feature: Testing the various user stories connected to the Associate use case

  Background: 
    Given  I connect to trackforce
    Given  I login as an Associate
    Given  I am on the Associate Home Page

  Scenario: Associate is attempting to update information on their home page
    When  I enter new name information
    And  I click the save button
    Then  the changes should be reflected
  
  Scenario: An associate updates their password
  	When I click on the update password option
  	And I enter my current password
  	And enter a new password 
    And confirm the new password
  	And click the update button
   Then A success message should appear on the page
  	
	Scenario: An associate updates their username
		When I click on the update username option
		And I enter a new username 
		And I enter my password
		And click the update button
		And I enter my old username
		And I enter my old password for user
		And click the update button	
		Then A success message should appear on the page 
  
  Scenario: Creating a valid Interview
    When I click the interview tab
    And I select a client
    And I select an interview type
    And I enter an Interview date
    And I select twenty-four hour notice
    When I get the interviews
    And press the add interview button
    Then a success popup should display

  Scenario: Creating an Interview without selecting a client
    When I click the interview tab
    And I select an interview type
    And I enter an Interview date
    When I get the interviews
    And press the add interview button
    Then an error popup should display

  Scenario: Creating an Interview without selecting an interview date
    When I click the interview tab
    And I select a client
    And I select an interview type
    And I select twenty-four hour notice
    When I get the interviews
    And press the add interview button
    Then an error popup should display


  Scenario: Creating an Interview without selecting a type
 		When I click the interview tab
    And I select a client
    And I enter an Interview date
    And I select twenty-four hour notice
    When I get the interviews
    And press the add interview button
    Then an error popup should display
    
   Scenario: Creating an interview at a time that is already booked
    When I click the interview tab
    And I select a client
    And I select an interview type
    And I enter an Interview date
    And I select twenty-four hour notice
    When I get the interviews
    And press the add interview button
    Then an error popup should display

   Scenario: Successfully updating an interview to a new date and time
   	When I click the interview tab
   	And I select an interview date to update
   	And I select an interview time to update
   	And I click update interview
   	Then an update success popup should display
   
   Scenario: Updating an interview to a time that is already booked
   	When I click the interview tab
   	And I select an interview date to update
   	And I select an interview time to update
   	And I click update interview
   	Then an update error should display
   
   Scenario: Updating an interview without selecting a date
   	When I click the interview tab
   	And I select an interview time to update
   	And I click update interview
   	Then an update error should display
   
   Scenario: Updating an interview without selecting a time
   	When I click the interview tab
   	And I select an interview date to update
   	And I click update interview
   	Then an update error should display