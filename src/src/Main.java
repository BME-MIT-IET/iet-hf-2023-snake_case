package src;

import View.Menu;
import View.SystemCall;
import commands.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static HashMap<String, src.CommandsInterface> commands = new HashMap<>();
    private static src.Board board = new src.Board();
    private static Create crea = new Create();
    private static Move move = new Move();
    private static Collect collect = new Collect();
    private static Craft craft = new Craft();
    private static Attack attack = new Attack();
    private static Drop drop = new Drop();
    private static ApplyEffect applyeffect = new ApplyEffect();
    private static Equip equip = new Equip();
    private static List list = new List();
    private static Steal steal = new Steal();
    private static Load load = new Load();
    private static EndTurn endturn = new EndTurn();
    private static Start start = new Start();

    //VISUAL INIT
    private static Menu menu;

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        String parancs;
        String s[];

        /*Parancsok hozzaddasa a hashmaphez*/
        commands.put("create", Main::create);
        commands.put("attack", Main::attack);
        commands.put("collect", Main::collect);
        commands.put("move", Main::move);
        commands.put("craft", Main::craft);
        commands.put("drop", Main::drop);
        commands.put("steal", Main::steal);
        commands.put("list", Main::list);
        commands.put("applyeffect", Main::applyEffect);
        commands.put("equip", Main::equip);
        commands.put("load", Main::load);
        commands.put("endturn", Main::endTurn);
        commands.put("start", Main::start);

        //VISUAL START
        SystemCall system = new SystemCall();
        menu = new Menu(system);
        menu.setVisible(true);

        while(!exit) {
            System.out.println("What would you like to do?");
            parancs = input.nextLine();
            s = parancs.split(" ");
            if(commands.containsKey(s[0])) {
                commands.get(s[0]).execute(s);
            }
            else if(s[0].equals("exit")){
                exit = true;
                System.exit(0);//Utolag lett beteve, hogy az ablakot is bezarja
            }
            else{
                System.out.println("The command doesn't exists!");
            }
        }
    }

    public static void create(String[] args){
        crea.create(args, board);
    }

    public static void move(String[] args){
        move.move(args, board);
    }

    public static void collect(String[] args){
        collect.collect(args, board);
    }

    public static void steal(String[] args) { steal.steal(args, board); }
    
    public static void craft(String[] args) {
    	craft.craft(args, board);
    }

    /*Syntax: attack 'who is attacking' 'who do you want to attack' 'with what' */
    public static void attack(String[] args){
        attack.attack(args, board);
    }

    public static void drop(String[] args){
        drop.drop(args, board);
    }

    public static void list(String[] args){
    	list.list(args, board);
    }
    
    public static void endTurn(String[] args){
    	endturn.endTurn(args, board);
    }

    public static void applyEffect(String[] args){applyeffect.applyEffect(args, board);}

    public static void equip(String[] args){equip.equip(args, board);}

    public static void load(String[] args) throws FileNotFoundException {load.load(args, board);}

    public static void start(String[] args) throws FileNotFoundException {start.start(args, board);}

}

