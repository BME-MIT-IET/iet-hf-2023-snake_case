package View;

import actionListeners.*;
import commands.EndTurn;
import commands.Start;
import src.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private JFrame mainMenu;
    private Board board;
    
    //Buttons
    private JButton endTurn;
    private JButton attack;
    private JButton steal;
    private JButton drop;
    private JButton collect;
    private JButton craft;
    private JButton save;
    private JButton exit;

    private MapGraphics mapPanel;
    private JPanel actionsPanelMain;
    private JPanel actionsPanelA;
    private JPanel actionsPanelB;
    private JPanel basicPanel;
    private JPanel otherPanel;
    //Labels
    private JLabel gcodes;

    private Control control;
    private View view;


    GameWindow(JFrame menu){
        mainMenu = menu;
        Start start = new Start();
        board = new Board();
        String[] args = {};
        try{
            start.start(args, board);
        }
        catch(Exception e){
            System.out.println(e);
        }
        view = new View();
    }

    public JPanel getBasicPanel(){
        return basicPanel;
    }

    //Minden elemet elrejt a panelben
    //Kvazi reseteli
    public void hideEveryComponentInOtherPanel(JPanel panel){
        if(panel.getComponentCount() == 0){
            return;
        }
        for(int i = 0; i < panel.getComponentCount(); i++) {
            panel.getComponent(i).setVisible(false);
        }
    }

    public void noMoreAction(){
        hideEveryComponentInOtherPanel(otherPanel);
        JLabel noAction = new JLabel("You don't have any more actions, End Turn.");
        otherPanel.add(noAction);
    }

    public Board getBoard(){
        return board;
    }

    public void craftActionListener(ActionEvent e){
        if(!board.getAction()){
            noMoreAction();
        }
        else {
            hideEveryComponentInOtherPanel(otherPanel);
            JButton paralyze = new JButton("paralyze");
            JButton forget = new JButton("forget");
            JButton protect = new JButton("protect");
            JButton virusdance = new JButton("virusdance");

            otherPanel.setLayout(new FlowLayout());

            ArrayList<GCode> geneticalCodes = board.getVirologusok().get(0).getInv().getGcodes();

            if (geneticalCodes.isEmpty()) {
                gcodes.setText("You don't know any genetical codes yet.");
                gcodes.setVisible(true);
                return;
            }

            for (int i = 0; i < geneticalCodes.size(); i++) {
                if (geneticalCodes.get(i).getEffect().equals(StringConstants.PARALYZE)) {
                    paralyze.addActionListener(new OtherCraftActionListener(geneticalCodes.get(i), board, control));
                    otherPanel.add(paralyze);
                } else if (geneticalCodes.get(i).getEffect().equals(StringConstants.FORGETVIRUS)) {
                    forget.addActionListener(new OtherCraftActionListener(geneticalCodes.get(i), board, control));
                    otherPanel.add(forget);
                } else if (geneticalCodes.get(i).getEffect().equals(StringConstants.PROTECTVIRUS)) {
                    protect.addActionListener(new OtherCraftActionListener(geneticalCodes.get(i), board, control));
                    otherPanel.add(protect);
                } else if (geneticalCodes.get(i).getEffect().equals(StringConstants.DANCEVIRUS)) {
                    virusdance.addActionListener(new OtherCraftActionListener(geneticalCodes.get(i), board, control));
                    otherPanel.add(virusdance);
                }
            }
        }
    }

    public void collectActionListener(ActionEvent e){
        if(!board.getAction()){
            noMoreAction();
        }
        else {
            hideEveryComponentInOtherPanel(otherPanel);
            gcodes.setText("You tried to collect form the field");
            gcodes.setVisible(true);
            OtherCollectActionListener elem = new OtherCollectActionListener(otherPanel, board, control);
            elem.actionPerformed(e);
        }
    }

    public void dropActionListener(ActionEvent e){
        if(!board.getAction()){
            noMoreAction();
        }
        else {
            hideEveryComponentInOtherPanel(otherPanel);
            JButton cape = new JButton("Cape");
            JButton axe = new JButton("Axe");
            JButton bag = new JButton("Bag");
            JButton gloves = new JButton("Gloves");
            otherPanel.setLayout(new FlowLayout());

            ArrayList<Equipment> items = board.getVirologusok().get(0).getInv().GetEquipments();

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(StringConstants.CAPE)) {
                    cape.addActionListener(new OtherDropActionListener(items.get(i), board, cape, control));
                    otherPanel.add(cape);
                } else if (items.get(i).getName().equals(StringConstants.AXE)) {
                    axe.addActionListener(new OtherDropActionListener(items.get(i), board, axe, control));
                    otherPanel.add(axe);
                } else if (items.get(i).getName().equals(StringConstants.BAG)) {
                    bag.addActionListener(new OtherDropActionListener(items.get(i), board, bag, control));
                    otherPanel.add(bag);
                } else if (items.get(i).getName().equals(StringConstants.GLOVES)) {
                    gloves.addActionListener(new OtherDropActionListener(items.get(i), board, gloves, control));
                    otherPanel.add(gloves);
                }
            }
        }
    }
    public void attackActionListener(ActionEvent e){
        if(!board.getAction()){
            noMoreAction();
        }
        else {
            hideEveryComponentInOtherPanel(otherPanel);
            OtherAttackActionListener atvisz = new OtherAttackActionListener(board, otherPanel, control);
            atvisz.InitButtons();
        }
    }

    public void stealActionListener(ActionEvent e){
        if(!board.getAction()){
            noMoreAction();
        }
        else {
            hideEveryComponentInOtherPanel(otherPanel);
            OtherStealActionListener atvisz = new OtherStealActionListener(board, otherPanel, control);
            atvisz.InitButtons();
        }
    }

    public void exitActionListener(ActionEvent e){
        System.exit(0);
    }

    public void disableAll(){
        attack.setEnabled(false);
        steal.setEnabled(false);
        drop.setEnabled(false);
        collect.setEnabled(false);
        craft.setEnabled(false);
        endTurn.setEnabled(false);
        save.setEnabled(false);
    }

    public void endTurnActionListener(ActionEvent e){
        hideEveryComponentInOtherPanel(otherPanel);
        EndTurn endturn = new EndTurn();
        String[] args;
        String bemenet = "endturn";
        args = bemenet.split(" ");
        if(board.getVirologusok().get(0).getEffects().searchForEffect(StringConstants.BEARVIRUS)){
            hideEveryComponentInOtherPanel(otherPanel);
            JLabel bearLabel = new JLabel("You have been infected with the bearvirus, the game is over for you.");
            otherPanel.add(bearLabel);
            disableAll();
        }
        for(int i = 0; i < board.getVirologusok().size(); i++){
            Virologist vir = board.getVirologusok().get(i);
            if(!vir.getEffects().searchForEffect(StringConstants.BEARVIRUS) && vir.getInv().getGcodes().size() == 4){
                disableAll();
                if(i == 0){
                    gcodes.setText("The player has earned an EPIC Victory Royale!");
                }
                else{
                    gcodes.setText("The player has lost!");
                }
                gcodes.setVisible(true);
                return;
            }
        }
        endturn.endTurn(args, board);
        JLabel endturnlabel = new JLabel("I have ended my turn.");
        otherPanel.add(endturnlabel);
        mapPanel.repaint();
        control.invalidateBasicPanel();
    }

    //A board elemeinek szerializálása, az egész board-ot nem tudtam egyben szerializálni.
    public void saveActionListener(ActionEvent e){
        try{
            FileOutputStream FileOut = new FileOutputStream("save.txt");
            ObjectOutputStream ObjectOut = new ObjectOutputStream(FileOut);
            ArrayList<Object> everyting = new ArrayList<>();
            everyting.add(board.getVirologusok());
            everyting.add(board.getMezok());
            everyting.add(board.getFelszerelesek());
            everyting.add(board.getGenetikaiKodok());
            ObjectOut.writeObject(everyting);
            ObjectOut.close();
            System.out.println("Save successful!");
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public void startGame(Menu menu){
        //Megnezi, hogy a start sikerult-e, elvileg soha nem kene az if-be bemenni, de jobb felni
        if(board.getMezok().isEmpty()){
            System.out.println("The game is not ready!");
            System.exit(1);
            return;
        }

        //GridBagLayout-ot kell szerintem hasznalni, mert akkor lesz szep
        this.setLayout(new GridBagLayout());

        //Labelek beallitasa
        //TORLENDI IDOVEL
        //JLabel textMap = new JLabel("MAP");
        JLabel textActions = new JLabel("ACTIONS");
        JLabel textBasicInfo = new JLabel("BASICINFO");
        JLabel textOther = new JLabel("OTHER");
        //VEGE

        gcodes = new JLabel("gcodes");
        gcodes.setVisible(false);

        //Buttons
        craft = new JButton("Craft");
        drop = new JButton("Drop");
        collect = new JButton("Collect");
        endTurn = new JButton("END TURN");
        attack = new JButton("Attack");
        steal = new JButton("Steal");
        save = new JButton("Save Game");
        exit = new JButton("Exit");

        //Buttons ActionListenerek hozzarendeles
        craft.addActionListener(this::craftActionListener);
        drop.addActionListener(this::dropActionListener);
        collect.addActionListener(this::collectActionListener);
        attack.addActionListener(this::attackActionListener);
        steal.addActionListener(this::stealActionListener);
        endTurn.addActionListener(this::endTurnActionListener);
        save.addActionListener(this::saveActionListener);
        exit.addActionListener(this::exitActionListener);

        //MapPanel es MouseActionListener letrehozasa
        mapPanel = new MapGraphics(board, this);//Elobb kell letrehozni, hogy lehessen ra hivatkozni
        mapPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //beallitja, hogy hova nyomott
                mapPanel.setClickedX(e.getX());
                mapPanel.setClickedY(e.getY());
                
                //Tesztkiiras
                System.out.println("You clicked: x:"+mapPanel.getClickedX()+" y:"+mapPanel.getClickedY());
                
                //Ha valamire nyomott, ujrarajzolas!            Egyebkent ez nem ide kell feltetlenul, de nem tuom hova tegyuk, egyenlore szerintem jo ide -Dani
                if(mapPanel.checkHit()) {
                    mapPanel.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        
        
        
        
        
        //Gomb igazitasok beallitasa(LEFT/CENTER/RIGHT)
        craft.setAlignmentX(Component.CENTER_ALIGNMENT);
        endTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
        drop.setAlignmentX(Component.CENTER_ALIGNMENT);
        collect.setAlignmentX(Component.CENTER_ALIGNMENT);
        steal.setAlignmentX(Component.CENTER_ALIGNMENT);
        attack.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Panelek letrehozasa

        actionsPanelMain = new JPanel();
        actionsPanelA = new JPanel();
        actionsPanelB = new JPanel();
        basicPanel = new JPanel();
        otherPanel = new JPanel();

        //Hatter szinek(atmeneti)
        actionsPanelMain.setBackground(Color.GREEN);
        basicPanel.setBackground(Color.YELLOW);
        otherPanel.setBackground(Color.GREEN);

        //Panel layoutok allitasa
        actionsPanelMain.setLayout(new BorderLayout());
        actionsPanelA.setLayout(new BoxLayout(actionsPanelA, BoxLayout.PAGE_AXIS));
        actionsPanelB.setLayout(new FlowLayout());


        //Meret beallitas
        mapPanel.setPreferredSize(new Dimension(800, 400));

        
        //Panelekhez gombok, egyeb vizualis dolgok addolasa
        actionsPanelB.add(textActions);
        actionsPanelA.add(craft);
        actionsPanelA.add(drop);
        actionsPanelA.add(collect);
        actionsPanelA.add(attack);
        actionsPanelA.add(steal);
        actionsPanelA.add(endTurn);
        actionsPanelA.add(save);
        actionsPanelA.add(exit);
        basicPanel.add(textBasicInfo);
        otherPanel.add(gcodes);

        //Panelekhez panelek addolasa
        actionsPanelMain.add(actionsPanelA, BorderLayout.CENTER);
        actionsPanelMain.add(actionsPanelB, BorderLayout.NORTH);

        //Specialis gridbag valtozo letrehozas
        //Szukseges, hogy szepen lehessen kirajzolni a dolgokat
        GridBagConstraints c = new GridBagConstraints();

        //Bonyolultnak tunhet, de annyira nem az, lehet tolem kerdezni
        //Finomhagonlni kell oket, ha valami nem tetszik
        /*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*
         * KIEGESZITES: A WEIGHT ACTUALLY AZT MONDJA MEG, HOGY HA VAN FOLOSLEGES HELY, AKKOR AZT HOGYAN OSSZA SZET, EBBOL A MAPNEK NEM KELL SEMENNYI -Dani  *
         *//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
        c.weightx = 0.0;    //Mekkora sulya legyen 0.0-1.0 kozti szam. Azt mondja meg, hogy a tobbihet kepest mekkora helyet foglaljon X tengelyen
        c.weighty = 0.0;    //Mekkora sulya legyen 0.0-1.0 kozti szam. Azt mondja meg, hogy a tobbihet kepest mekkora helyet foglaljon Y tengelyen
        c.gridx = 0;    //Melyik oszlopban helyezkedjen el az adott komponens
        c.gridy = 0;    //Melyik sorban helyezkedjen el az adott komponens
        c.fill = GridBagConstraints.NONE; //Ez felel azert, hogy kitoltse a neki szant helyet -Dani
        this.add(mapPanel, c);

        c.fill = GridBagConstraints.NONE; //Itt pedig mar nicsn kitoltes -Dani
        c.weightx = 0.2;
        c.weighty = 0.8;
        c.gridx = 1;
        c.gridy = 0;
        this.add(actionsPanelMain, c);

        c.weightx = 0.2;
        c.weighty = 0.2;
        c.gridx = 1;
        c.gridy = 1;
        this.add(basicPanel, c);

        c.weightx = 0.2;
        c.weighty = 0.7;
        c.gridx = 0;
        c.gridy = 1;
        this.add(otherPanel, c);

        //Alap ablak beallitasok
        this.setMinimumSize(new Dimension(1300, 600));
        this.setTitle("The World of Blind Virologists");
        this.setResizable(false);

        control = new Control(board, this, view);

        control.invalidateBasicPanel();
        //Ablak valtas
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        menu.setVisible(false);
    }

    public void setBoard(Board br){
        board = br;
    }
}
