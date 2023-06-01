Feature: Virologist has Equipments

  Scenario: The virologist equips a cape
    Given there is a Cape
    Given there is a shelter
    And there is a player on the shelter
    When The player collects the equipment
    And tries to put the Equipement on
    Then The player should have the equipment on

  Scenario: The virologist equips Gloves
    Given there is a pair of Gloves
    Given there is a shelter
    And there is a player on the shelter
    When The player collects the equipment
    And tries to put the Equipement on
    Then The player should have the equipment on

  Scenario: The virologist equips an Axee
    Given there is an Axe
    Given there is a shelter
    And there is a player on the shelter
    When The player collects the equipment
    And tries to put the Equipement on
    Then The player should have the equipment on
