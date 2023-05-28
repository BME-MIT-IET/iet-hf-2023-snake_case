package test.bdd.StepDefs;

import src.Field;
import src.Virologist;

public class VirologistMovesDef {


    @io.cucumber.java.en.When("the player tries to move to a field")
    public void thePlayerTriesToMoveToAField(){
        InitializeBasicsDef.getPlayer().move(InitializeBasicsDef.getEndField());
    }
    @io.cucumber.java.en.Then("the player should be moved to another field")
    public void thePlayerShouldBeMovedToAnotherField(){
        Field currentField = InitializeBasicsDef.getPlayer().getField();
        assert currentField == InitializeBasicsDef.getEndField();
    }

    @io.cucumber.java.en.Then("the player should not be moved to another field")
    public void thePlayerShouldNotBeMovedToAnotherField(){
        Field currentField = InitializeBasicsDef.getPlayer().getField();
        assert currentField != InitializeBasicsDef.getEndField();
    }


}
