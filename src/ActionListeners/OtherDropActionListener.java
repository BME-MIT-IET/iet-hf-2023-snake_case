package ActionListeners;

import View.Control;
import commands.Drop;
import src.Board;
import src.Equipment;

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

    @Override
    public void actionPerformed(ActionEvent e){
        Drop dob = new Drop();
        String[] args;
        if(eq.getName().equals("axe")){
            String bemenet = "drop virologist0 axe";
            args = bemenet.split(" ");
        }
        else if(eq.getName().equals("cape")){
            String bemenet = "drop virologist0 cape";
            args = bemenet.split(" ");
        }
        else if(eq.getName().equals("gloves")){
            String bemenet = "drop virologist0 gloves";
            args = bemenet.split(" ");
        }
        else if(eq.getName().equals("bag")){
            String bemenet = "drop virologist0 bag";
            args = bemenet.split(" ");
        }
        else{
            System.out.println("I can't find that equipment!");
            return;
        }
        dob.drop(args, board);
        gomb.setVisible(false);
        control.InvalidateBasicPanel();
    }
}
