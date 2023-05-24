package View;

import src.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View {

    //BasicLabels
    private JLabel materialsText;
    private JLabel materials;
    private JLabel equipmentText;
    private JLabel equipment;
    private JLabel geneticalCodesText;
    private JLabel geneticalCodes;
    private JLabel agentsText;
    private JLabel agents;
    private JLabel effectedEffectsText;
    private JLabel effectedEffects;
    public View(){

    }

    public void reDrawBasicPanel(Materials mat, ArrayList<Equipment> eq, ArrayList<Agent> agentsL, ArrayList<GCode> gcodes, JPanel basicPanel, Effects effects){
        equipmentText = new JLabel("The equipments you have: ");
        materialsText = new JLabel("The materials you have: ");
        agentsText = new JLabel("The agents you have: ");
        effectedEffectsText = new JLabel("The effects you are affected by: ");
        geneticalCodesText = new JLabel("The genetical codes you know: ");
        materials = new JLabel("none");
        effectedEffects = new JLabel("none");
        equipment = new JLabel("none");
        geneticalCodes = new JLabel("none");
        agents = new JLabel("none");

        //Basic panel reset
        basicPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        basicPanel.add(geneticalCodesText, c);
        c.gridy = 1;
        basicPanel.add(effectedEffectsText, c);
        c.gridy = 2;
        basicPanel.add(agentsText, c);
        c.gridy = 3;
        basicPanel.add(equipmentText, c);
        c.gridy = 4;
        basicPanel.add(materialsText, c);

        materials.setText(mat.toString());
        if(!eq.isEmpty()){
            String string = "";
            for(int i = 0; i < eq.size(); i++){
                if(i == eq.size() - 1){
                    string = string.concat(eq.get(i).getName());
                }
                else{
                    string = string.concat(eq.get(i).getName() + ", ");
                }
            }
            equipment.setText(string);
        }
        if(!agentsL.isEmpty()){
            String string = "";
            for(int i = 0; i < agentsL.size(); i++){
                if(i == agentsL.size() - 1){
                    string = string.concat(agentsL.get(i).getEffect().getMyEffect());
                }
                else{
                    string = string.concat(agentsL.get(i).getEffect().getMyEffect() + ", ");
                }
            }
            agents.setText(string);
        }
        if(!gcodes.isEmpty()){
            String string = "";
            for(int i = 0; i < gcodes.size(); i++){
                if(i == gcodes.size() - 1){
                    string = string.concat(gcodes.get(i).getEffect());
                }
                else{
                    string = string.concat(gcodes.get(i).getEffect() + ", ");
                }
            }
            geneticalCodes.setText(string);
        }
        if(!effects.getEffects().isEmpty()){
            String string = "";
            for(int i = 0; i < effects.getEffects().size(); i++){
                if(i == effects.getEffects().size() - 1){
                    string = string.concat(effects.getEffects().get(i).getMyEffect());
                }
                else{
                    string = string.concat(effects.getEffects().get(i).getMyEffect() + ", ");
                }
            }
            effectedEffects.setText(string);
        }

        c.gridx = 1;
        c.gridy = 0;
        basicPanel.add(geneticalCodes, c);
        c.gridy = 1;
        basicPanel.add(effectedEffects, c);
        c.gridy = 2;
        basicPanel.add(agents, c);
        c.gridy = 3;
        basicPanel.add(equipment, c);
        c.gridy = 4;
        basicPanel.add(materials, c);
    }
}
