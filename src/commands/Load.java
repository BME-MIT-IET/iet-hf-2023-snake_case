package commands;

import src.Board;

import java.io.*;
import java.util.Scanner;

public class Load {
    public void load(String[]args, Board b) throws FileNotFoundException {
        String n = args[1];
        String nev = "Tests/" + n + ".txt";

        //Board resetelese
        b.setNULLFelszerelesek();
        b.SetNULLVirologusok();
        b.setNULLMezok();
        b.setNULLGenetikaiKodok();
        boolean action = b.getAction();
        boolean move = b.getMove();
        if(!action){
            b.setAction(true);
        }
        if(!move){
            b.setMove(true);
        }

        try{
            File file = new File(nev);
            PrintStream psf = new PrintStream("testout.txt");
            PrintStream psc = System.out;
            System.setOut(psf);
            Scanner sc = new Scanner(file);
            String[] commands;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                commands = s.split(" ");
                String givenCommand = commands[0];
                switch(givenCommand){
                    case "create":
                        Create createLoad = new Create();
                        createLoad.create(commands, b);
                        break;
                    case "attack":
                        action = b.getAction();
                        b.setAction(actionCheck(action));

                        Attack atkLoad = new Attack();
                        atkLoad.attack(commands, b);
                        break;
                    case "collect":
                        action = b.getAction();
                        b.setAction(actionCheck(action));
                        Collect collLoad = new Collect();
                        collLoad.collect(commands, b);
                        break;
                    case "move":
                        action = b.getAction();
                        b.setAction(actionCheck(action));
                        move = b.getMove();
                        b.setMove(moveCheck(move));
                        Move moveLoad = new Move();
                        moveLoad.move(commands, b);
                        break;
                    case "craft":
                        action = b.getAction();
                        b.setAction(actionCheck(action));
                        Craft craftLoad = new Craft();
                        craftLoad.craft(commands, b);
                        break;
                    case "drop":
                        action = b.getAction();
                        b.setAction(actionCheck(action));
                        Drop dropLoad = new Drop();
                        dropLoad.drop(commands, b);
                        break;
                    case "steal":
                        action = b.getAction();
                        b.setAction(actionCheck(action));
                        Steal stealLoad = new Steal();
                        stealLoad.steal(commands, b);
                        break;
                    case "list":
                        List listLoad = new List();
                        listLoad.list(commands, b);
                        break;
                    case "applyeffect":
                        action = b.getAction();
                        b.setAction(actionCheck(action));
                        ApplyEffect afLoad = new ApplyEffect();
                        afLoad.applyEffect(commands, b);
                        break;
                    case "equip":
                        action = b.getAction();
                        b.setAction(actionCheck(action));
                        Equip equipLoad = new Equip();
                        equipLoad.equip(commands, b);
                        break;
                    default:
                        throw new UnsupportedOperationException();
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
                    String correct = helyes.nextLine();
                    String tested = tester.nextLine();
                    if(!correct.equals(tested)){
                        System.out.println("\t\tTHE TEST FAILED AT LINE: " + sorSzamlalo);
                        System.out.println("The correct line was: "+correct);
                        System.out.println("The line that I got was: "+tested);
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

    private boolean actionCheck(boolean action){
        if(!action){
            return true;
        }
        return false;
    }

    private boolean moveCheck(boolean action){
        if(!action){
            return true;
        }
        return false;
    }
}
