package View;

import src.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import commands.Start;

public class Menu extends JFrame {
    private JButton start;
    private JButton load;
    private JButton exit;

    private SystemCall system;

    private void exitAction(ActionEvent e){
        system.exit(0);
    }

    private void startAction(ActionEvent e){
        Start start = new Start();
        Board board = new Board();
        String[] args = {"mapTestNoBear.txt"};
        try{
            start.start(args, board);
        }
        catch(Exception ex){
            system.out().println(ex);
        }
        GameWindow game = new GameWindow(system, new View(), board);
        game.startGame();
        this.setVisible(false);
    }

    //Szerializált dolgok betöltése
    //Lefuttat egy rendes startGame funkciót, és utána set-eli a játék szempontjából fontos mentett állásokat
    //Gyakorlatilag nem a betöltött adatokkal generálja a mapot, hanem legenerálja az eredetit és utána set-el.
    private void loadGame(ActionEvent e){
        try {
            FileInputStream FileIn = new FileInputStream("save.txt");
            ObjectInputStream ObjectIn = new ObjectInputStream(FileIn);
            ArrayList<Object> minden;
            minden = (ArrayList<Object>)ObjectIn.readObject();
            ObjectIn.close();
            Start start = new Start();
            Board board = new Board();
            String[] args = {};
            try{
                start.start(args, board);
            }
            catch(Exception ex){
                system.out().println(ex);
            }
            GameWindow game = new GameWindow(system, new View(), board);
            system.out().println("Sikeres betoltes!");
            game.getBoard().setVirologusok((ArrayList<Virologist>) minden.get(0));
            game.getBoard().setFields((ArrayList<Field>) minden.get(1));
            game.getBoard().setFelszerelesek((ArrayList<Equipment>) minden.get(2));
            game.getBoard().setGenetikaiKodok((ArrayList<GCode>) minden.get(3));
            game.startGame();
            this.setVisible(false);

        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            system.out().println(ex);
        }
    }

    //Jelenleg nincs nalam a az elozo felevi munkam, majd megoldom szebbre is
    public Menu(SystemCall systemCall) throws IOException {
        this.setLayout(new BorderLayout());
        this.system = systemCall;
        //Gombok letrehozasa
        start = new JButton("START");
        start.setName("StartButton");
        load = new JButton("LOAD");
        load.setName("LoadButton");
        exit = new JButton("EXIT");
        exit.setName("ExitButton");

        //ActionListenerek
        start.addActionListener(this::startAction);
        load.addActionListener(this::loadGame);
        exit.addActionListener(this::exitAction);
        start.setFont(new Font("Tempus Sans ITC",Font.BOLD, 18));
        start.setBackground(new Color(139,204,210));
        load.setFont(new Font("Tempus Sans ITC",Font.BOLD, 18));
        load.setBackground(new Color(139,204,210));
        exit.setFont(new Font("Tempus Sans ITC",Font.BOLD, 18));
        exit.setBackground(new Color(139,204,210));

        JPanel left = new JPanel(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        JPanel logo = new JPanel(new BorderLayout());
        logo.setSize(600,200);
        JPanel middle = new JPanel(new BorderLayout());
        JPanel middleSumP = new JPanel(new GridLayout(3,1));

        BufferedImage img = ImageIO.read(new File("Logo.png"));
        BufferedImage imgL = ImageIO.read(new File("virologistLeft.png"));
        BufferedImage imgR = ImageIO.read(new File("virologistRight.png"));
        JLabel pic = new JLabel(new ImageIcon(img));
        JLabel picL = new JLabel(new ImageIcon(imgL));
        JLabel picR = new JLabel(new ImageIcon(imgR));
        logo.add(pic);
        left.add(picL);
        right.add(picR);
        JPanel startP = new JPanel();
        JPanel loadP = new JPanel();
        JPanel exitP = new JPanel();

        middleSumP.add(start);
        middleSumP.add(load);
        middleSumP.add(exit);

        middle.add(middleSumP, BorderLayout.CENTER);

        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(logo, BorderLayout.NORTH);
        this.add(middle, BorderLayout.CENTER);

        //Alap ablak beallitasok
        this.setTitle("The World of Blind Virologists");
        this.setMinimumSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}
