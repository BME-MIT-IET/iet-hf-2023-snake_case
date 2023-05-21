package commands;

import src.Board;
import src.Field;
import src.Virologist;

public class Attack {
    public void Attack(String[] args, Board board){
        if(args.length < 4){
            System.out.println("Not enough parameters!");
            return;
        }
        if(!args[1].contains("virologist") && !args[2].contains("virologist")){
            System.out.println("These are not virolgists!");
            return;
        }
        //Virologusok inicializalasa
        int AttackerV = Integer.parseInt(args[1].substring(10));
        int AttackedV = Integer.parseInt(args[2].substring(10));

        Virologist attacker = board.getVirologusok().get(AttackerV);
        Virologist attacked = board.getVirologusok().get(AttackedV);

        
        //Teszteli, hogy a virologusnak van e meg akcioja
        if(AttackerV == 0 && board.getAction()) {
        	board.setAction(false);
        }
        //Ha nincs nem csinal semmit
        else if(AttackerV == 0 && !board.getAction()) {
        	System.out.println("The player doesn't have any more actions!");
        	return;
        }
        
        
        //Ha nem ugyan azon a mezon allnak
        if(attacker.getField() != attacked.getField()){
            System.out.println("You are not standing on the same field!");
            return;
        }

        if(args[3].equals("axe")){
            attacker.UseAxe(attacked);
        }
        else {
            attacker.Attack(attacker, attacked, args[3]);
        }
    }
}
