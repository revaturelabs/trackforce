Feature: Testing the creation of various forms of users

  Background: 
    Given I connect to caliber
    And I login
    And I click on Create User Tab

  Scenario Outline: Testing the forms of creating valid users
    Given Create User Tab loads
    When I type in a valid username "<username>"
    And I type in a valid password"<password>"
    And I confirm the password"<password>"
    And I check the "<role>" role
    And I press submit
    Then A new User should be created

    Examples: 
      | username     | password       | role      |
      | NewAdmin     | NewAdmin1!     | admin     |
      | NewTrainer   | NewTrainer1!   | trainer   |
      | NewDelivery  | NewDelivery1!  | delivery  |
      | NewAssociate | NewAssociate1! | associate |
      | NewManager   | NewManager1!   | manager   |

  Scenario Outline: Testing creating an admin with an invalid inputs
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a valid username "<username>"
    And I type in a valid password"<password>"
    And I confirm the password"<confPassword>"
    And I check the "admin" role
    And I press submit
    Then A Pop Up Error should occur

    Examples: 
      | username                   | password   | confPassword |
      |                    0000000 | NewAdmin1! | NewAdmin1!   |
      | admin                      | NewAdmin1! | NewAdmin1!   |
      | thisIsOverTwentyCharacters | NewAdmin1! | NewAdmin1!   |
      | New Admin                  | NewAdmin1! | NewAdmin1!   |
      | NewAdmin!                  | NewAdmin1! | NewAdmin1!   |
      |                            | NewAdmin1! | NewAdmin1!   |
      | NewAdmin                   | New1!      | New1!        |
      | NewAdmin                   | NewAdmins1 | NewAdmins1   |
      | NewAdmin                   | NewAdmins! | NewAdmins!   |
      | NewAdmin                   | newadmin1! | newadmin1!   |
      | NewAdmin                   | NEWADMIN1! | NEWADMIN1!   |
      | NewAdmin                   |            |              |
      | NewAdmin                   | NewAdmin1! | Not the same |
      | NewAdmin                   | NewAdmin1! |              |

  Scenario Outline: Testing creating an associate with an invalid inputs
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a valid username "<username>"
    And I type in a valid password"<password>"
    And I confirm the password"<confPassword>"
    And I check the "associate" role
    And I press submit
    Then A Pop Up Error should occur

    Examples: 
      | username                   | password       | confPassword   |
      |                    0000000 | NewAssociate1! | NewAssociate1! |
      | assoc                      | NewAssociate1! | NewAssociate1! |
      | thisIsOverTwentyCharacters | NewAssociate1! | NewAssociate1! |
      | New Associate              | NewAssociate1! | NewAssociate1! |
      | NewAssociate!              | NewAssociate1! | NewAssociate1! |
      |                            | NewAssociate1! | NewAssociate1! |
      | NewAssociate               | New1!          | New1!          |
      | NewAssociate               | NewAssoc1      | NewAssoc1      |
      | NewAssociate               | NewAssoc!      | NewAssoc!      |
      | NewAssociate               | newassoc1!     | newassoc1!     |
      | NewAssociate               | NEWASSOC1!     | NEWASSOC1!     |
      | NewAssociate               |                |                |
      | NewAssociate               | NewAssoc1!     | Not the same   |
      | NewAssociate               | NewAssoc1!     |                |

  Scenario Outline: Testing creating a trainer with an invalid inputs
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a valid username "<username>"
    And I type in a valid password"<password>"
    And I confirm the password"<confPassword>"
    And I check the "trainer" role
    And I press submit
    Then A Pop Up Error should occur

    Examples: 
      | username                   | password     | confPassword |
      |                    0000000 | NewTrainer1! | NewTrainer1! |
      | trai                       | NewTrainer1! | NewTrainer1! |
      | thisIsOverTwentyCharacters | NewTrainer1! | NewTrainer1! |
      | New Trainer                | NewTrainer1! | NewTrainer1! |
      | NewTrainer!                | NewTrainer1! | NewTrainer1! |
      |                            | NewTrainer1! | NewTrainer1! |
      | NewTrainer                 | New1!        | New1!        |
      | NewTrainer                 | NewTrainer1  | NewTrainer1  |
      | NewTrainer                 | NewTrainer!  | NewTrainer!  |
      | NewTrainer                 | newtrainer1! | newtrainer1! |
      | NewTrainer                 | NEWTRAINER1! | NEWTRAINER1! |
      | NewTrainer                 |              |              |
      | NewTrainer                 | NewTrainer1! | Not the same |
      | NewTrainer                 | NewTrainer1! |              |

  Scenario Outline: Testing creating an delivery team user with an invalid inputs
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a valid username "<username>"
    And I type in a valid password"<password>"
    And I confirm the password"<confPassword>"
    And I check the "delivery" role
    And I press submit
    Then A Pop Up Error should occur

    Examples: 
      | username                   | password      | confPassword  |
      |                     000000 | NewDelivery1! | NewDelivery1! |
      | deliv                      | NewDelivery1! | NewDelivery1! |
      | thisIsOverTwentyCharacters | NewDelivery1! | NewDelivery!  |
      | New Delilvery              | NewDelivery1! | NewDelivery1! |
      | NewDelivery!               | NewDelivery1! | NewDelivery1! |
      |                            | NewDelivery1! | NewDelivery1! |
      | NewDelivery                | New1!         | New1!         |
      | NewDelivery                | NewDelivery1  | NewDelivery1  |
      | NewDelivery                | NewDelivery!  | NewDelivery!  |
      | NewDelivery                | newdelivery1! | newdelivery1! |
      | NewDelivery                | NEWDELIVERY1! | NEWDELIVERY1! |
      | NewDelivery                |               |               |
      | NewDelivery                | NewDelivery1! | Not the same  |
      | NewDelivery                | NewDelivery1! |               |

  Scenario Outline: Testing creating an manager with an invalid inputs
    Given I click on Create User Tab
    And Create User Tab loads
    When I type in a valid username "<username>"
    And I type in a valid password"<password>"
    And I confirm the password"<confPassword>"
    And I check the "manager" role
    And I press submit
    Then A Pop Up Error should occur

    Examples: 
      | username                   | password     | confPassword |
      |                     000000 | NewManager1! | NewManager1! |
      | manag                      | NewManager1! | NewManager1! |
      | thisIsOverTwentyCharacters | NewManager1! | NewManager1! |
      | New Manager                | NewManager1! | NewManager1! |
      | NewManager!                | NewManager1! | NewManager1! |
      |                            | NewManager1! | NewManager1! |
      | NewManager                 | New1!        | New1!        |
      | NewManager                 | NewManager1  | NewManager1  |
      | NewManager                 | NewManager!  | NewManager!  |
      | NewManager                 | newmanager1! | newadmin1!   |
      | NewManager                 | NEWMANAGER1! | NEWMANAGER1! |
      | NewManager                 |              |              |
      | NewManager                 | NewManger1!  | Not the same |
      | NewManager                 | NewManger1!  |              |
