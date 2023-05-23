package View;

import src.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Control {

    private Board board;
    private Virologist player;

    private GameWindow gameWindow;
    private View view;

    public Control(Board board, GameWindow gameWindow, View view){
        this.board = board;
        this.gameWindow = gameWindow;
        this.view = view;
        player = board.getVirologusok().get(0);
    }

    public void InvalidateBasicPanel(){
        Materials mat = player.getInv().getMaterials();
        ArrayList<Equipment> eq = player.getInv().GetEquipments();
        ArrayList<Agent> agents = player.getInv().getAgents();
        ArrayList<GCode> gcodes = player.getInv().GetGcodes();
        Effects effects = player.GetEffects();

        gameWindow.HideEveryComponentInOtherPanel(gameWindow.getBasicPanel());
        view.reDrawBasicPanel(mat, eq, agents, gcodes, gameWindow.getBasicPanel(), effects);
    }

}
