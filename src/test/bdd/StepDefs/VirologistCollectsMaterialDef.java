package test.bdd.StepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VirologistCollectsMaterialDef {
    @When("The player collect the material")
    public void thePlayerCollectTheMaterial() {
        InitializeBasicsDef.getPlayer().collect();
    }

    @Then("The player should have an amino-acid")
    public void thePlayerShouldHaveAnAminoAcid() {
        assert InitializeBasicsDef.getPlayer().getInv().getMaterials().getAmino() == 1;
        assert InitializeBasicsDef.getPlayer().getInv().getMaterials().getNukleo() == 0;
    }

    @Then("The player should have an nukleotid")
    public void thePlayerShouldHaveAnNukleotid() {
        assert InitializeBasicsDef.getPlayer().getInv().getMaterials().getAmino() == 0;
        assert InitializeBasicsDef.getPlayer().getInv().getMaterials().getNukleo() == 1;
    }
}
