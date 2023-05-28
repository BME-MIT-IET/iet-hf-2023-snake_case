package View;

import src.*;

import java.util.ArrayList;

public class Control {

    private final Virologist player;

    private final GameWindow gameWindow;
    private final View view;

    public Control(Virologist player, GameWindow gameWindow, View view){
        this.gameWindow = gameWindow;
        this.view = view;
        this.player = player;
    }

    public void invalidateBasicPanel(){
        Materials mat = player.getInv().getMaterials();
        ArrayList<Equipment> eq = player.getInv().GetEquipments();
        ArrayList<Agent> agents = player.getInv().getAgents();
        ArrayList<GCode> gcodes = player.getInv().getGcodes();
        Effects effects = player.getEffects();

        gameWindow.hideEveryComponentInOtherPanel(gameWindow.getBasicPanel());
        view.reDrawBasicPanel(mat, eq, agents, gcodes, gameWindow.getBasicPanel(), effects);
    }

}
