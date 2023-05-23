package commands;

import src.Board;
import src.Field;
import src.Virologist;

public class Steal {
    public void Steal(String[] args, Board board){
        if(args.length < 4){
            System.out.println("Not enough parameters!");
            return;
        }
        if(board.getVirologusok().size() < 2){
            System.out.println("There have to be at least 2 virologist on the board");
            return;
        }
        if(!args[1].contains("virologist") && !args[2].contains("virologist")){
            System.out.println("These are not virolgists!");
            return;
        }
        //Virologusok inicializalasa
        int StealerV = Integer.parseInt(args[1].substring(10));
        int VictimV = Integer.parseInt(args[2].substring(10));

        Virologist stealer = board.getVirologusok().get(StealerV);
        Virologist victim = board.getVirologusok().get(VictimV);

        //Ha nem ugyan azon a mezon allnak
        if(stealer.getField() != victim.getField()){
            System.out.println("You are not standing on the same field!");
            return;
        }

        
        //Teszteli, hogy a virologusnak van e meg akcioja
        if(StealerV == 0 && board.getAction()) {
        	board.setAction(false);
        }
        //Ha nincs nem csinal semmit
        else if(StealerV == 0 && !board.getAction()) {
        	System.out.println("The player doesn't have any more actions!");
        	return;
        }
        
        
        
        if(args[3].equals("material")){
            stealer.StealMat(victim);
        }
        else{
            stealer.StealEq(victim, args[3]);
        }
    }
}
