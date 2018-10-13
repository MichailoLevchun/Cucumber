Feature: Gmail message meneger test

  Scenario: Delete importent messages
    Given I login with credential username='andasndmnasm' password =' 123rty789'
    When I mark 3 messages as important
    And go to important
    And delete recently added messages
    Then check if deleted messages arent present on the page

  Scenario Outline: Delete importent messages for different users
    Given I login with credential username='<username>' password ='<password>'
    When I mark <messageNumber> messages as important
    And go to important
    And delete recently added messages
    Then check if deleted messages arent present on the page

    Examples: 
      | username         | password      | messageNumber |
      | andasndmnasm     | 123rty789     |             1 |
      | jiwdsfjf         | 123rty789     |             1 |
      | 457fdghsgfshg    | 123rty789     |             1 |
      | abbad5431        | 123rty789     |             1 |
