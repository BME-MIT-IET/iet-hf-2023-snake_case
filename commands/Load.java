package commands;

import commands.Create;
import src.Board;

import java.io.*;
import java.util.Scanner;

public class Load {
    public void Load(String[]args, Board b) throws FileNotFoundException {
        String n = args[1];
        String nev = "Tests/" + n + ".txt";

        //Board resetelese
        b.SetNULLFelszerelesek();
        b.SetNULLVirologusok();
        b.SetNULLMezok();
        b.SetNULLGenetikaiKodok();
        boolean Action = b.getAction();
        boolean Move = b.getMove();
        if(!Action){
            b.setAction(true);
        }
        if(!Move){
            b.setMove(true);
        }

        try{
            File file = new File(nev);
            PrintStream psf = new PrintStream("testout.txt");
            PrintStream psc = System.out;
            System.setOut(psf);
            Scanner sc = new Scanner(file);
            //Board b = new Board();
            String[] commands;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                commands = s.split(" ");
                if (commands[0].equals("create")) {
                    Create createLoad = new Create();
                    createLoad.Create(commands, b);
                } else if (commands[0].equals("attack")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    Attack atkLoad = new Attack();
                    atkLoad.Attack(commands, b);
                } else if (commands[0].equals("collect")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    Collect collLoad = new Collect();
                    collLoad.Collect(commands, b);
                } else if (commands[0].equals("move")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    Move = b.getMove();
                    if(!Move){
                        b.setMove(true);
                    }
                    Move moveLoad = new Move();
                    moveLoad.Move(commands, b);
                } else if (commands[0].equals("craft")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    Craft craftLoad = new Craft();
                    craftLoad.Craft(commands, b);
                } else if (commands[0].equals("drop")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    Drop dropLoad = new Drop();
                    dropLoad.Drop(commands, b);
                } else if (commands[0].equals("steal")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    Steal stealLoad = new Steal();
                    stealLoad.Steal(commands, b);
                } else if (commands[0].equals("list")) {
                    List listLoad = new List();
                    listLoad.List(commands, b);
                } else if (commands[0].equals("applyeffect")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    ApplyEffect afLoad = new ApplyEffect();
                    afLoad.ApplyEffect(commands, b);
                } else if (commands[0].equals("equip")) {
                    Action = b.getAction();
                    if(!Action){
                        b.setAction(true);
                    }
                    Equip equipLoad = new Equip();
                    equipLoad.Equip(commands, b);
                }
            }
            System.setOut(psc);

            //Teszteles, hogy rendesen lefutottak-e a parancsok
            File testerF = new File("testout.txt");
            Scanner tester = new Scanner(testerF);

            String szam = n.substring(4);
            String kimenetTeszt = "CorrectOut/Correct" + szam + ".txt";
            File helyesF = new File(kimenetTeszt);
            Scanner helyes = new Scanner(helyesF);
            boolean failed = false;
            int sorSzamlalo = 1;
            while(tester.hasNextLine()){
                if(helyes.hasNextLine()){
                    String Correct = helyes.nextLine();
                    String Tested = tester.nextLine();
                    if(!Correct.equals(Tested)){
                        System.out.println("\t\tTHE TEST FAILED AT LINE: " + sorSzamlalo);
                        System.out.println("The correct line was: "+Correct);
                        System.out.println("The line that I got was: "+Tested);
                        failed = true;
                    }
                    sorSzamlalo++;
                }
                else{
                    System.out.println("The two txt has different number of lines!");
                    helyes.close();
                    tester.close();
                    return;
                }
            }
            if(!failed){
                System.out.println("The test Nr:" + szam + " succeeded!");
                helyes.close();
                tester.close();
            }

        }
        catch(FileNotFoundException fe){
            System.out.println("Invalid test case!");
        }
    }
}
