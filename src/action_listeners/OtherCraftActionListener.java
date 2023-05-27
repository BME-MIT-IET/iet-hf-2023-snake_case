package action_listeners;

import View.Control;
import commands.Craft;
import src.Board;
import src.GCode;
import src.StringConstants;

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

    private static final String CRAFT_VIROLOGIST0 = "craft virologist0 ";

    @Override
    public void actionPerformed(ActionEvent e) {
        Craft elem = new Craft();
        String[] args;
        if(genetical.getEffect().equals(StringConstants.PARALYZE)){
            String bemenet = CRAFT_VIROLOGIST0+StringConstants.PARALYZE;
            args = bemenet.split(" ");
        }
        else if(genetical.getEffect().equals(StringConstants.FORGETVIRUS)){
            String bemenet = CRAFT_VIROLOGIST0+StringConstants.FORGETVIRUS;
            args = bemenet.split(" ");
        }
        else if(genetical.getEffect().equals(StringConstants.PROTECTVIRUS)){
            String bemenet = CRAFT_VIROLOGIST0+StringConstants.PROTECTVIRUS;
            args = bemenet.split(" ");
        }
        else if(genetical.getEffect().equals(StringConstants.DANCEVIRUS)){
            String bemenet = CRAFT_VIROLOGIST0+StringConstants.DANCEVIRUS;
            args = bemenet.split(" ");
        }
        else{
            System.out.println("I can't find that genetical code!");
            return;
        }

        elem.craft(args, board);
        control.invalidateBasicPanel();
    }
}
