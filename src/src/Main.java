package src;

import View.Menu;
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
        commands.put("create", Main::Create);
        commands.put("attack", Main::Attack);
        commands.put("collect", Main::Collect);
        commands.put("move", Main::Move);
        commands.put("craft", Main::Craft);
        commands.put("drop", Main::Drop);
        commands.put("steal", Main::Steal);
        commands.put("list", Main::List);
        commands.put("applyeffect", Main::ApplyEffect);
        commands.put("equip", Main::Equip);
        commands.put("load", Main::Load);
        commands.put("endturn", Main::EndTurn);
        commands.put("start", Main::Start);

        //VISUAL START
        menu = new Menu();
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

    public static void Create(String[] args){
        crea.create(args, board);
    }

    public static void Move(String[] args){
        move.move(args, board);
    }

    public static void Collect(String[] args){
        collect.collect(args, board);
    }

    public static void Steal(String[] args) { steal.steal(args, board); }
    
    public static void Craft(String[] args) {
    	craft.craft(args, board);
    }

    /*Syntax: attack 'who is attacking' 'who do you want to attack' 'with what' */
    public static void Attack(String[] args){
        attack.attack(args, board);
    }

    public static void Drop(String[] args){
        drop.drop(args, board);
    }

    public static void List(String[] args){
    	list.list(args, board);
    }
    
    public static void EndTurn(String[] args){
    	endturn.endTurn(args, board);
    }

    public static void ApplyEffect(String[] args){applyeffect.applyEffect(args, board);}

    public static void Equip(String[] args){equip.equip(args, board);}

    public static void Load(String[] args) throws FileNotFoundException {load.load(args, board);}

    public static void Start(String[] args) throws FileNotFoundException {start.start(args, board);}

}

