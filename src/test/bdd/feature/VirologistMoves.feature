Feature: Virologist Moves

  Scenario: Successful Move
    Given there is a field
    And there is a player on the Field
    And two fields are neighbors
    When the player tries to move to a field
    Then the player should be moved to another field

  Scenario: Unsuccessful Move
    Given there is a field
    And there is a player on the Field
    When the player tries to move to a field
    Then the player should not be moved to another field

  Scenario: Paralyzed Move
    Given there is a field
    And there is a player on the Field
    And two fields are neighbors
    And the player is paralyzed
    When the player tries to move to a field
    Then the player should not be moved to another field