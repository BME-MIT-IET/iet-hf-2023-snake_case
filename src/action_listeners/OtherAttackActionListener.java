package action_listeners;

import View.Control;
import commands.Attack;
import src.Board;
import src.StringConstants;
import src.Virologist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OtherAttackActionListener implements ActionListener {
    private Board board;
    private Control control;
    private Virologist kit;
    private int kitID;
    private String mivel;
    private JPanel otherPanel;
    private JButton confirm;

    /*Buttons*/
    ArrayList<JRadioButton> virButt;
    ArrayList<Integer> indexes; /*a megtalalt virologus melyik indexen van a board virologusok listajaban*/
    JRadioButton virologist;
    JRadioButton agent0;
    JRadioButton agent1;
    JRadioButton agent2;
    JRadioButton agent3;
    JRadioButton agent4;
    JRadioButton axe;
    int axeIndex;

    public OtherAttackActionListener(Board board, JPanel otherPanel, Control control){
        this.board = board;
        this.otherPanel = otherPanel;
        this.kit = null;
        this.mivel = null;
        this.confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        this.control = control;
        virButt = new ArrayList<>();
        indexes = new ArrayList<>();
    }

    public void initButtons(){
        /*Roviditesek*/
        ArrayList<Virologist> virologusok = board.getVirologusok();
        ArrayList<Virologist> virologusokAMezon = board.getVirologusok().get(0).getField().getVirologists();

        /*Gombcsoport hogy ne lehessen egyszerre tobbet bejelolni*/
        ButtonGroup agensGroup = new ButtonGroup();

        makeEnoughButtons(virologusokAMezon, virologusok);

        for(int i = 0; i < virologusok.get(0).getInv().getAgents().size(); i++){
            switch(i){
                case 0:
                    agent0 = new JRadioButton(virologusok.get(0).getInv().getAgents().get(0).getEffect().getMyEffect());
                    agensGroup.add(agent0);
                    agent0.addActionListener(this);
                    agent0.setBackground(Color.GREEN);
                    otherPanel.add(agent0);
                    break;
                case 1:
                    agent1 = new JRadioButton(virologusok.get(0).getInv().getAgents().get(1).getEffect().getMyEffect());
                    agensGroup.add(agent1);
                    agent1.addActionListener(this);
                    agent1.setBackground(Color.GREEN);
                    otherPanel.add(agent1);
                    break;
                case 2:
                    agent2 = new JRadioButton(virologusok.get(0).getInv().getAgents().get(2).getEffect().getMyEffect());
                    agensGroup.add(agent2);
                    agent2.addActionListener(this);
                    agent2.setBackground(Color.GREEN);
                    otherPanel.add(agent2);
                    break;
                case 3:
                    agent3 = new JRadioButton(virologusok.get(0).getInv().getAgents().get(3).getEffect().getMyEffect());
                    agensGroup.add(agent3);
                    agent3.addActionListener(this);
                    agent3.setBackground(Color.GREEN);
                    otherPanel.add(agent3);
                    break;
                case 4:
                    agent4 = new JRadioButton(virologusok.get(0).getInv().getAgents().get(4).getEffect().getMyEffect());
                    agensGroup.add(agent4);
                    agent4.addActionListener(this);
                    agent4.setBackground(Color.GREEN);
                    otherPanel.add(agent4);
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        for(int i = 0; i < virologusok.get(0).getInv().GetEquipments().size(); i++){
            if(virologusok.get(0).getInv().GetEquipments().get(i).getName().equals("axe")){
                axeIndex = i;
                axe = new JRadioButton("axe");
                agensGroup.add(axe);
                axe.addActionListener(this);
                axe.setBackground(Color.YELLOW);
                otherPanel.add(axe);
            }
        }
        otherPanel.add(confirm);
    }

    private void makeEnoughButtons(ArrayList<Virologist> virologusokAMezon, ArrayList<Virologist> virologusok){
        /*Gombcsoport hogy ne lehessen egyszerre tobbet bejelolni*/
        ButtonGroup virologusGroup = new ButtonGroup();
        /*for ciklus arra hogy hany gomb legyen a panelon(annyi ahany virologus rajta all)*/
        for(int i = 0; i < virologusokAMezon.size(); i++){
            for(int e = 0; e < virologusok.size(); e++){
                if(virologusok.get(e) == virologusokAMezon.get(i)){
                    if(virologusok.get(e).getEffects().searchForEffect(StringConstants.BEARVIRUS)){
                        virologist = new JRadioButton("Bear");
                    }
                    else if(e == 0){
                        virologist = new JRadioButton("Self");
                    }
                    else {
                        virologist = new JRadioButton("Virologist");
                    }
                    virologusGroup.add(virologist);
                    virologist.addActionListener(this);
                    virologist.setBackground(Color.RED);
                    virButt.add(virologist);
                    otherPanel.add(virologist);
                    indexes.add(e);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setWhichVirologistGotChoosen(e);
        setWhichEffectIsBeingUsed(e);

        if(e.getSource() == confirm){
            checkIfAttackCanBePerformed();
        }
        control.invalidateBasicPanel();
    }

    /*Kikeresi, hogy melyik effect lett kivalasztva*/
    private void setWhichEffectIsBeingUsed(ActionEvent e){
        if(e.getSource() == agent0){
            mivel = board.getVirologusok().get(0).getInv().getAgents().get(0).getEffect().getMyEffect();
        }else if(e.getSource() == agent1){
            mivel = board.getVirologusok().get(0).getInv().getAgents().get(1).getEffect().getMyEffect();
        }else if(e.getSource() == agent2){
            mivel = board.getVirologusok().get(0).getInv().getAgents().get(2).getEffect().getMyEffect();
        }else if(e.getSource() == agent3){
            mivel = board.getVirologusok().get(0).getInv().getAgents().get(3).getEffect().getMyEffect();
        }else if(e.getSource() == agent4){
            mivel = board.getVirologusok().get(0).getInv().getAgents().get(4).getEffect().getMyEffect();
        }
        else if(e.getSource() == axe){
            mivel = "axe";
        }
        else{
            mivel = null;
        }
    }

    /*vegignezzuk az osszes gombot hogy melyikre erkezhetett az esemeny*/
    private void setWhichVirologistGotChoosen(ActionEvent e){
        for(int i = 0; i < virButt.size(); i++){
            if(e.getSource() == virButt.get(i)){
                /*Ha megvan kiszedjuk az indexet az indexes listabol es megvan a virologus akinek a gombjara nyomtak*/
                kit = board.getVirologusok().get(indexes.get(i));
                kitID = indexes.get(i);
            }
        }
    }

    private void checkIfAttackCanBePerformed(){
        /*Megnezi, hogy ervenyes adatokat kapott-e*/
        if(kit != null && mivel != null){
            /*Ha a tamadott nem medve, de fejszevel tamadunk*/
            if(mivel.equals(StringConstants.AXE) && !kit.getEffects().searchForEffect(StringConstants.BEARVIRUS)){
                for(int i = 0; i < otherPanel.getComponentCount(); i++) {
                    otherPanel.getComponent(i).setVisible(false);
                }
                JLabel notbearlabel = new JLabel("The attacked virologist is not a bear, therefore you can't use the axe against him.");
                otherPanel.add(notbearlabel);
            }
            /*Happy path*/
            else {
                Attack atk = new Attack();
                String[] args;
                String bemenet = "attack virologist0 virologist" + kitID + " " + mivel;
                args = bemenet.split(" ");
                atk.attack(args, board);
                for (int i = 0; i < otherPanel.getComponentCount(); i++) {
                    otherPanel.getComponent(i).setVisible(false);
                }
                JLabel attacklabel = new JLabel("I attacked a virologist.");
                otherPanel.add(attacklabel);
            }
        }
    }
}
