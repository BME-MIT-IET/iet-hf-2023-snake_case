Feature: Virologist Collects Materials

  Scenario: Virologist collects aminoacid
    Given there is a warehouse with amino-acid
    And there is a player on the Warehouse
    When The player collect the material
    Then The player should have an amino-acid

  Scenario: Virologist collects nukleotid
    Given there is a warehouse with nukleotid
    And there is a player on the Warehouse
    When The player collect the material
    Then The player should have an nukleotid