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
      | username     | password      | role      |
      | NewAdmin     | NewAdmin!     | admin     |
      | NewTrainer   | NewTrainer!   | trainer   |
      | NewDelivery  | NewDelivery!  | delivery  |
      | NewAssociate | NewAssociate! | associate |
      | NewManager   | NewManager!   | manager   |

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
      | username  | password  | confPassword |
      |   0000000 | NewAdmin! | NewAdmin!    |
      | admin     | NewAdmin! | NewAdmin!    |
      | New Admin | NewAdmin! | NewAdmin!    |
      | NewAdmin! | NewAdmin! | NewAdmin!    |
      |           | NewAdmin! | NewAdmin!    |
      | NewAdmin  | NewA!     | NewA!        |
      | NewAdmin  | NewAdmins | NewAdmins    |
      | NewAdmin  | newadmin! | newadmin!    |
      | NewAdmin  | NEWADMIN! | NEWADMIN!    |
      | NewAdmin  |           |              |
      | NewAdmin  | NewAdmin! | Not the same |
      | NewAdmin  | NewAdmin! |              |

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
      | username      | password      | confPassword  |
      |       0000000 | NewAssociate! | NewAssociate! |
      | assoc         | NewAssociate! | NewAssociate! |
      | New Associate | NewAssociate! | NewAssociate! |
      | NewAssociate! | NewAssociate! | NewAssociate! |
      |               | NewAssociate! | NewAssociate! |
      | NewAssociate  | NewA!         | NewA!         |
      | NewAssociate  | NewAssoc      | NewAssoc      |
      | NewAssociate  | newassoc!     | newassoc!     |
      | NewAssociate  | NEWASSOC!     | NEWASSOC!     |
      | NewAssociate  |               |               |
      | NewAssociate  | NewAssoc!     | Not the same  |
      | NewAssociate  | NewAssoc!     |               |

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
      | username    | password    | confPassword |
      |     0000000 | NewTrainer! | NewTrainer!  |
      | trai        | NewTrainer! | NewTrainer!  |
      | New Trainer | NewTrainer! | NewTrainer!  |
      | NewTrainer! | NewTrainer! | NewTrainer!  |
      |             | NewTrainer! | NewTrainer!  |
      | NewTrainer  | NewT!       | NewT!        |
      | NewTrainer  | NewTrainer  | NewTrainer   |
      | NewTrainer  | newtrainer! | newtrainer!  |
      | NewTrainer  | NEWTRAINER! | NEWTRAINER!  |
      | NewTrainer  |             |              |
      | NewTrainer  | NewTrainer! | Not the same |
      | NewTrainer  | NewTrainer! |              |

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
      | username      | password     | confPassword |
      |        000000 | NewDelivery! | NewDelivery! |
      | deliv         | NewDelivery! | NewDelivery! |
      | New Delilvery | NewDelivery! | NewDelivery! |
      | NewDelivery!  | NewDelivery! | NewDelivery! |
      |               | NewDelivery! | NewDelivery! |
      | NewDelivery   | NewD!        | NewD!        |
      | NewDelivery   | NewDelivery  | NewDelivery  |
      | NewDelivery   | newdelivery! | newdelivery! |
      | NewDelivery   | NEWDELIVERY! | NEWDELIVERY! |
      | NewDelivery   |              |              |
      | NewDelivery   | NewDelivery! | Not the same |
      | NewDelivery   | NewDelivery! |              |

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
      | username    | password    | confPassword |
      |      000000 | NewManager! | NewManger!   |
      | manag       | NewManager! | NewManager!  |
      | New Manager | NewManager! | NewManager!  |
      | NewManager! | NewManager! | NewManager!  |
      |             | NewManager! | NewManager!  |
      | NewManager  | NewMa       | NewMa        |
      | NewManager  | NewManager  | NewManager   |
      | NewManager  | newmanager! | newadmin!    |
      | NewManager  | NEWMANAGER! | NEWMANAGER!  |
      | NewManager  |             |              |
      | NewManager  | NewManger!  | Not the same |
      | NewManager  | NewManger!  |              |
