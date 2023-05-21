package ActionListeners;

import View.Control;
import commands.Craft;
import src.Board;
import src.GCode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherCraftActionListener implements ActionListener {
    private GCode genetical;
    private Board board;

    private Control control;

    public OtherCraftActionListener(GCode geneticalcode, Board board, Control control){
        genetical = geneticalcode;
        this.board = board;
        this.control = control;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Craft elem = new Craft();
        String[] args;
        if(genetical.getEffect().equals("paralyze")){
            String bemenet = "craft virologist0 paralyze";
            args = bemenet.split(" ");
        }
        else if(genetical.getEffect().equals("forgetvirus")){
            String bemenet = "craft virologist0 forgetvirus";
            args = bemenet.split(" ");
        }
        else if(genetical.getEffect().equals("protectvirus")){
            String bemenet = "craft virologist0 protectvirus";
            args = bemenet.split(" ");
        }
        else if(genetical.getEffect().equals("virusdance")){
            String bemenet = "craft virologist0 virusdance";
            args = bemenet.split(" ");
        }
        else{
            System.out.println("I can't find that genetical code!");
            return;
        }

        elem.Craft(args, board);
        control.InvalidateBasicPanel();
    }
}
