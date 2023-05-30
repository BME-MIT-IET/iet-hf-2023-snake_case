package test.bdd.StepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import src.Agent;
import src.GCode;
import src.StringConstants;

public class VirologistCraftsDef {

    @io.cucumber.java.en.When("the player tries to craft the Agent")
    public void thePlayerTriesToCraftForgetVirus(){
        if(InitializeBasicsDef.getPlayer().getInv().getGcodes().size() != 0) {
            InitializeBasicsDef.getPlayer().craft(InitializeBasicsDef.getPlayer().getInv().getGcodes().get(0));
        }
    }

    @When("the player collects the geneCode")
    public void thePlayerCollectsTheGeneCode() {
        InitializeBasicsDef.getPlayer().collect();
    }

    @Then("the player should have the forgetVirus")
    public void thePlayerShouldHaveTheForgetVirus() {
        assert InitializeBasicsDef.getPlayer().getInv().getAgents().size() > 0;

        assert InitializeBasicsDef.getPlayer().getInv().getAgents().get(0).getEffect().getMyEffect().equals(StringConstants.FORGETVIRUS);
    }

    @Then("the player should have the ParalyzeAgent")
    public void thePlayerShouldHaveTheParalyzeAgent() {
        assert InitializeBasicsDef.getPlayer().getInv().getAgents().size() > 0;

        assert InitializeBasicsDef.getPlayer().getInv().getAgents().get(0).getEffect().getMyEffect().equals(StringConstants.PARALYZE);
    }

    @Then("the player should not have any agents")
    public void thePlayerShouldNotHaveAnyAgents() {
        assert InitializeBasicsDef.getPlayer().getInv().getAgents().size() == 0;
    }
}
