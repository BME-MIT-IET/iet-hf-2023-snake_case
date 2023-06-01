package test.bdd.StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VirologistEquipDef {


    @When("The player collects the equipment")
    public void thePlayerCollectsTheEquipment() {
        InitializeBasicsDef.getPlayer().collect();
    }

    @And("tries to put the Equipement on")
    public void triesToPutTheEquipementOn() {
        InitializeBasicsDef.getPlayer().equip(InitializeBasicsDef.getPlayer().getInv().GetEquipments().get(0));
    }


    @Then("The player should have the equipment on")
    public void thePlayerShouldHaveTheEquipmentOn() {
       assert  InitializeBasicsDef.getPlayer().getInv().GetEquipments().size() > 0;
    }

}
