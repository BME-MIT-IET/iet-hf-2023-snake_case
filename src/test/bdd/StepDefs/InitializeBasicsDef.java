package test.bdd.StepDefs;

import effects.Paralyzed;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import src.*;

public class InitializeBasicsDef {
    private static Equipment equipment;
    public  static Equipment getEquipment(){
        return  equipment;
    }
    private static GCode gcode;
    public  static GCode getGcode(){ return gcode;}
    private static Warehouse warehouse;
    public static Warehouse getWarehouse(){ return warehouse;}
    private static Shelter shelter;
    public static Shelter getShelter(){ return shelter;}
    private static Laboratory lab;
    public static Laboratory getLaboratory(){ return lab;}
    private static Field startField;
    public static Field getStartField() {
        return startField;
    }

    private static Field endField;
    public static Field getEndField() {
        return endField;
    }
    private static Virologist player;
    public static Virologist getPlayer() {
        return player;
    }

    @io.cucumber.java.en.Given("there is a field")
    public void thereIsAField(){
        startField = new Field();
    }

    @io.cucumber.java.en.Given("there is a warehouse with amino-acid")
    public void thereIsAWarehouseWithAminoAcid(){
        warehouse = new Warehouse(0,'a',1);
    }

    @io.cucumber.java.en.Given("there is a warehouse with nukleotid")
    public void thereIsAWarehouseWithNukleotid(){
        warehouse = new Warehouse(0,'n',1);
    }
    @io.cucumber.java.en.Given("there is a player on the Field")
    public void thereIsAPlayerOnTheField(){
        player = new Virologist(0,0,0, startField);
    }

    @io.cucumber.java.en.Given("there is a player on the Warehouse")
    public void thereIsAPlayerOnTheWarehouse(){
        player = new Virologist(0,0,0, warehouse);
    }

    @io.cucumber.java.en.Given("two fields are neighbors")
    public void twoFieldsAreNeighbors(){
        endField = new Field();
        endField.addNeighbour(startField);
        startField.addNeighbour(endField);
    }

    @io.cucumber.java.en.Given("the player is paralyzed")
    public void thePlayerIsParalyzed() {
        player.getEffects().applyEffect(player, new Paralyzed());
    }

    @And("the player has {int} nukleotid and {int} aminoacid")
    public void thePlayerHasNukleotidAndAminoacid(int nukleotidCount, int aminoacidCount) {
        player.getInv().changeMaterial('n', nukleotidCount);
        player.getInv().changeMaterial('a', aminoacidCount);
    }

    @Given("there is a laboratory")
    public void thereIsALaboratory() {
        lab = new Laboratory(gcode,0);
    }

    @Given("there is a Forget Gcode")
    public void thereIsAForgetGcode() {
        gcode = new GCode(3,2, StringConstants.FORGETVIRUS);
    }

    @And("there is a player on the Laboratory")
    public void thereIsAPlayerOnTheLaboratory() {
        player = new Virologist(0,0,0, lab);
    }

    @Given("there is a Paralyze Gcode")
    public void thereIsAParalyzeGcode() {
        gcode = new GCode(1,5,StringConstants.PARALYZE);
    }

    @Given("there is a Cape")
    public void thereIsACape() {
        equipment = new Cape(0);
    }

    @Given("there is a shelter")
    public void thereIsAShelter() {
        shelter = new Shelter(equipment);
    }

    @And("there is a player on the shelter")
    public void thereIsAPlayerOnTheShelter() {
        player = new Virologist(0,0,0, shelter);
    }

    @Given("there is a pair of Gloves")
    public void thereIsAPairOfGloves() {
        equipment = new Gloves();
    }

    @Given("there is an Axe")
    public void thereIsAnAxe() {
        equipment = new Axe();
    }
}
