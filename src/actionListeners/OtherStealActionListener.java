package actionListeners;

import View.Control;
import commands.Steal;
import src.Board;
import src.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OtherStealActionListener implements ActionListener {
    private Board board;
    private Virologist target;
    private int targetID;
    private String item;
    private JPanel otherPanel;
    private JButton confirm;
    private Control control;

    JRadioButton virologist1;
    JRadioButton virologist2;
    JRadioButton cape;
    JRadioButton axe;
    JRadioButton bag;
    JRadioButton gloves;
    JRadioButton mat;

    public OtherStealActionListener(Board board, JPanel otherPanel, Control control) {
        this.board = board;
        this.otherPanel = otherPanel;
        this.target = null;
        this.item = null;
        this.confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        this.control = control;
    }

    public void InitButtons() {
        ArrayList<Virologist> virologusok = board.getVirologusok();
        ArrayList<Virologist> virologusokAMezon = board.getVirologusok().get(0).getField().getVirologists();
        ButtonGroup virologusGroup = new ButtonGroup();
        ButtonGroup eqGroup = new ButtonGroup();
        for (int i = 0; i < virologusokAMezon.size(); i++) {
            if (virologusok.get(1) == virologusokAMezon.get(i)){
                virologist1 = new JRadioButton("Virologist");
                virologusGroup.add(virologist1);
                virologist1.addActionListener(this);
                virologist1.setBackground(Color.RED);
                otherPanel.add(virologist1);
            } else if (virologusok.get(2) == virologusokAMezon.get(i)) {
                virologist2 = new JRadioButton("Virologist");
                virologusGroup.add(virologist2);
                virologist2.addActionListener(this);
                virologist2.setBackground(Color.BLUE);
                otherPanel.add(virologist2);
            }
        }
        cape = new JRadioButton("Cape");
        axe = new JRadioButton("Axe");
        gloves = new JRadioButton("Gloves");
        bag = new JRadioButton("Bag");
        cape.addActionListener(this);
        axe.addActionListener(this);
        bag.addActionListener(this);
        gloves.addActionListener(this);
        mat = new JRadioButton("Materials");
        mat.addActionListener(this);
        eqGroup.add(cape);
        eqGroup.add(axe);
        eqGroup.add(bag);
        eqGroup.add(gloves);
        eqGroup.add(mat);
        otherPanel.add(cape);
        otherPanel.add(axe);
        otherPanel.add(bag);
        otherPanel.add(gloves);
        otherPanel.add(mat);
        mat.setVisible(false);
        cape.setVisible(false);
        gloves.setVisible(false);
        bag.setVisible(false);
        axe.setVisible(false);
        otherPanel.add(confirm);
        confirm.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == virologist1){
            target = board.getVirologusok().get(1);
            targetID = 1;
            mat.setVisible(false);
            cape.setVisible(false);
            gloves.setVisible(false);
            bag.setVisible(false);
            axe.setVisible(false);
            confirm.setVisible(false);
            for(int i = 0; i < target.getInv().GetEquipments().size(); i++){
                if(target.getInv().GetEquipments().get(i).getName().equals("cape")){
                    cape.setVisible(true);
                }
                else if(target.getInv().GetEquipments().get(i).getName().equals("axe")){
                    axe.setVisible(true);
                }
                else if(target.getInv().GetEquipments().get(i).getName().equals("gloves")){
                    gloves.setVisible(true);
                }
                else if(target.getInv().GetEquipments().get(i).getName().equals("bag")){
                    bag.setVisible(true);
                }
            }
            mat.setVisible(true);
            confirm.setVisible(true);
        }
        else if(e.getSource() == virologist2){
            target = board.getVirologusok().get(2);
            targetID = 2;
            mat.setVisible(false);
            cape.setVisible(false);
            gloves.setVisible(false);
            bag.setVisible(false);
            axe.setVisible(false);
            confirm.setVisible(false);
            for(int i = 0; i < target.getInv().GetEquipments().size(); i++){
                if(target.getInv().GetEquipments().get(i).getName().equals("cape")){
                    cape.setVisible(true);
                }
                else if(target.getInv().GetEquipments().get(i).getName().equals("axe")){
                    axe.setVisible(true);
                }
                else if(target.getInv().GetEquipments().get(i).getName().equals("gloves")){
                    gloves.setVisible(true);
                }
                else if(target.getInv().GetEquipments().get(i).getName().equals("bag")){
                    bag.setVisible(true);
                }
            }
            mat.setVisible(true);
            confirm.setVisible(true);
        }
        else if(e.getSource() == cape){
            item = "cape";
        }else if(e.getSource() == axe){
            item = "axe";
        }else if(e.getSource() == gloves){
            item = "gloves";
        }else if(e.getSource() == bag){
            item = "bag";
        }
        else if(e.getSource() == mat){
            item = "material";
        }
        else if(e.getSource() == confirm){
            if(target != null && item != null){
                Steal stl = new Steal();
                String[] args;
                String bemenet = "steal virologist0 virologist" + targetID + " " + item;
                args = bemenet.split(" ");
                stl.steal(args, board);

            }
        }
        control.invalidateBasicPanel();
        //for(int i = 0; i < otherPanel.getComponentCount(); i++){
          //  otherPanel.getComponent(i).setVisible(false);
        // }
    }
}

