Feature: Virologist Crafts Agent

  Scenario: Virologist craft forgetAgent
    Given there is a Forget Gcode
    Given there is a laboratory
    And there is a player on the Laboratory
    And the player has 2 nukleotid and 3 aminoacid
    When the player collects the geneCode
    And the player tries to craft the Agent
    Then the player should have the forgetVirus

  Scenario: Virologist craft paralyzeAgent
    Given there is a Paralyze Gcode
    Given there is a laboratory
    And there is a player on the Laboratory
    And the player has 5 nukleotid and 1 aminoacid
    When the player collects the geneCode
    And the player tries to craft the Agent
    Then the player should have the ParalyzeAgent

  Scenario: Virologist can't craft because he is paralyzed
    Given there is a Paralyze Gcode
    Given there is a laboratory
    And there is a player on the Laboratory
    And the player has 5 nukleotid and 1 aminoacid
    And the player is paralyzed
    When the player collects the geneCode
    And the player tries to craft the Agent
    Then the player should not have any agents

  Scenario: Virologist can't craft because he is doesn't have enough materials
    Given there is a Paralyze Gcode
    Given there is a laboratory
    And there is a player on the Laboratory
    When the player collects the geneCode
    And the player tries to craft the Agent
    Then the player should not have any agents