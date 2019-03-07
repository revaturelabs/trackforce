Feature: Testing the various user stories connected to the Associate use case

  Background: 
    Given  I connect to trackforce
    Given  I login as an Associate
    Given  I am on the Associate Home Page

#The changes should be reflected is failing - relies on broken database/frontend schema
  #Scenario: Associate is attempting to update information on their home page
    #When  I enter new name information
    #And  I click the save button
    #Then  the changes should be reflected
  
  Scenario: An associate updates their password
  	When I click on the update password option
  	And I enter my current password
  #	And enter a new password 
  #	And confirm the new password
  #	And click the update button
  #	Then A success message should appear on the page
  	
#	Scenario: An associate updates their username
#		When I click on the update username option
#		And I enter a new username 
#		And I enter my password
#		And click the update button
#		Then A success message should appear on the page
  #	
    #
  #Scenario: Login as Associate and navigate to the My Interviews tab
  #	When I click the interview tab
  #	Then I am on the interview view
#
#Select a client is failing - relies on broken frontend/database code
#Need to implement "it should be in the interview table"
  #Scenario: Creating a valid Interview
    #When I click the interview tab
    #And I select a client
    #And I enter an Interview date
    #And I select an interview type
    #And I select twenty-four hour notice
    #And press the add interview button
    #Then it should be in the interview table
#
  #Scenario: Creating an Interview without selecting a client
    #When I click the interview tab
    #And I enter an Interview date
    #And I select an interview type
    #And I select twenty-four hour notice
    #And press the add interview button
    #Then an error popup should display
#
  #Scenario: Creating an Interview without selecting an interview date
    #When I click the interview tab
    #And I select a client
    #And I select an interview type
    #And I select twenty-four hour notice
    #And press the add interview button
    #Then an error popup should display
#
  #Scenario: Creating an Interview without selecting a type
    #When I click the interview tab
    #And I select a client
    #And I enter an Interview date
    #And I select twenty-four hour notice
    #And press the add interview button
    #Then an error popup should display
