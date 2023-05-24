package commands;

import src.Board;
import src.Virologist;

public class Drop {
    public void drop(String[] args, Board board){
        if(args.length < 3){
            System.out.println("There are not enough arguments");
            return;
        }
        if(board.getVirologusok().size() == 0){
            System.out.println("There are no virologists on the board");
            return;
        }
        if(args[1].length() < 10){
            System.out.println("I can't find that virologist");
        }
        int virologusID = Integer.parseInt(args[1].substring(10));
        if(virologusID < 0){
            System.out.println("Invalid virologist");
            return;
        }
        
        
        //Teszteli, hogy a virologusnak van e meg akcioja
        if(virologusID == 0 && board.getAction()) {
        	board.setAction(false);
        }
        //Ha nincs nem csinal semmit
        else if(virologusID == 0 && !board.getAction()) {
        	System.out.println("The player doesn't have any more actions!");
        	return;
        }

        Virologist virologist = board.getVirologusok().get(virologusID);
        virologist.drop(args[2]);
    }
}
