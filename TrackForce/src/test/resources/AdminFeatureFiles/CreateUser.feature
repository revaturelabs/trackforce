Feature: Testing the creation of various forms of users

Background:
   Given I connect to caliber
   Given I login as an Administrator

  Scenario Outline: Testing the forms of creating valid users
    Given Create User Tab loads
    When I type in a valid username "<username>"
    And I type in a valid password"<password>"
    And I confirm the password"<password>"
    And I check the "<role>" role
    And I press submit
    Then A new User should be created

    Examples: 
      | username     | password       | role      				|
      | NewAdmin     | NewAdmin1!     | Administrator     |
      | NewTrainer   | NewTrainer1!   | Trainer   				|
      | NewDelivery  | NewDelivery1!  | Delivery/Sales  	|
      | NewAssociate | NewAssociate1! | Associate 				|
      | NewManager   | NewManager1!   | Staging Manager 	|