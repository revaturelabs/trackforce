@tag
Feature: Batch Feature
	I want to see if my batchListing.html and batchDetails.html are working  

@tag1
Scenario: Navigate to batch listing
Given I am at the homepage 
When  I click on batches on the navbar
Then I validate that it is batchListing.html page

@tag2
Scenario: Search batches by date 
Given I am at the batchListing.html page 
	When I input a date into the start date
	And I input a date into the end date 
Then I am able to see batches that are going on between these two dates 

@tag3
Scenario: Get batch details 
Given I am at the batchListing.html page
When I click on a batch name
Then I should see the different details about a batch
