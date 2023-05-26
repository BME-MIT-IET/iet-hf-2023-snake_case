package commands;

import src.Board;
import src.StringConstants;
import src.Virologist;

public class Steal {
    public void steal(String[] args, Board board){
        if(args.length < 4){
            System.out.println("Not enough parameters!");
            return;
        }
        if(board.getVirologusok().size() < 2){
            System.out.println("There have to be at least 2 virologist on the board");
            return;
        }
        if(!args[1].contains(StringConstants.VIROLOGIST) && !args[2].contains(StringConstants.VIROLOGIST)){
            System.out.println("These are not virolgists!");
            return;
        }
        //Virologusok inicializalasa
        int stealerV = Integer.parseInt(args[1].substring(10));
        int victimV = Integer.parseInt(args[2].substring(10));

        Virologist stealer = board.getVirologusok().get(stealerV);
        Virologist victim = board.getVirologusok().get(victimV);

        //Ha nem ugyan azon a mezon allnak
        if(stealer.getField() != victim.getField()){
            System.out.println("You are not standing on the same field!");
            return;
        }

        
        //Teszteli, hogy a virologusnak van e meg akcioja
        if(stealerV == 0 && board.getAction()) {
        	board.setAction(false);
        }
        //Ha nincs nem csinal semmit
        else if(stealerV == 0 && !board.getAction()) {
        	System.out.println("The player doesn't have any more actions!");
        	return;
        }

        if(args[3].equals("material")){
            stealer.stealMaterial(victim);
        }
        else{
            stealer.stealEq(victim, args[3]);
        }
    }
}
