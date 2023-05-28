package test.bdd.StepDefs;

import effects.Paralyzed;
import io.cucumber.java.en.And;
import src.Effect;
import src.Field;
import src.Virologist;
import src.Warehouse;

public class InitializeBasicsDef {
    private static Warehouse warehouse;
    public static Warehouse getWarehouse(){ return warehouse;}
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
}
