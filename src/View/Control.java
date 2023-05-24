package View;

import src.*;

import java.util.ArrayList;

public class Control {

    private final Board board;
    private final Virologist player;

    private final GameWindow gameWindow;
    private final View view;

    public Control(Board board, GameWindow gameWindow, View view){
        this.board = board;
        this.gameWindow = gameWindow;
        this.view = view;
        player = board.getVirologusok().get(0);
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
