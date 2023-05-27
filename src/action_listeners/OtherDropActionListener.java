package action_listeners;

import View.Control;
import commands.Drop;
import src.Board;
import src.Equipment;
import src.StringConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherDropActionListener implements ActionListener {
    private Board board;
    private Equipment eq;
    private JButton gomb;
    private Control control;

    public OtherDropActionListener(Equipment e, Board b, JButton g, Control control){
        board = b;
        this.eq = e;
        gomb = g;
        this.control = control;
    }

    private static final String DROP_VIROLOGIST0 = "drop virologist0 ";

    @Override
    public void actionPerformed(ActionEvent e){
        Drop dob = new Drop();
        String[] args;
        if(eq.getName().equals(StringConstants.AXE)){
            String bemenet = DROP_VIROLOGIST0+StringConstants.AXE;
            args = bemenet.split(" ");
        }
        else if(eq.getName().equals(StringConstants.CAPE)){
            String bemenet = DROP_VIROLOGIST0+StringConstants.CAPE;
            args = bemenet.split(" ");
        }
        else if(eq.getName().equals(StringConstants.GLOVES)){
            String bemenet = DROP_VIROLOGIST0+StringConstants.GLOVES;
            args = bemenet.split(" ");
        }
        else if(eq.getName().equals(StringConstants.BAG)){
            String bemenet = DROP_VIROLOGIST0+StringConstants.BAG;
            args = bemenet.split(" ");
        }
        else{
            System.out.println("I can't find that equipment!");
            return;
        }
        dob.drop(args, board);
        gomb.setVisible(false);
        control.invalidateBasicPanel();
    }
}
