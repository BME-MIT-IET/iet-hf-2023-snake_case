package commands;

import src.Board;
import src.StringConstants;
import src.Virologist;

public class Craft {
	
    /*Virologus letrehozhat agenseket
     * @param args = A felhasznalotol kapott input
     * Syntax: craft [ki] [mit]*/
	public void craft(String[] args, Board board) {
		/* Teszteli, hogy van-e egyaltalan eleg parameter */
		if(args.length < 3){
            System.out.println("There are not enough parameters!");
            return;
        }
		
		/*Melyik virologushoz adunk*/
        /*A parancsban virologist vot-e megadva*/
        if(args[1].length() < 10 || !args[1].substring(0,10).equals(StringConstants.VIROLOGIST)) {
        	System.out.println("virologist was expected, but got something else!");
        	return;
        }
        String vID = args[1].substring(10);
        /*Nincs szam, vagy a szam nem ad meg egy letezo virologust*/
        if(vID.equals("")) {
        	System.out.println("Virologist ID is missing!");
        	return;
        }
        else if(Integer.parseInt(vID) >= board.getVirologusok().size() ){
            System.out.println("I can't find that virologist.");
            return;
        }
        int virologusID = Integer.parseInt(vID);
        Virologist v1 = board.getVirologusok().get(virologusID);
		
        
        
        //Teszteli, hogy a virologusnak van e meg akcioja
        if(virologusID == 0 && board.getAction()) {
        	board.setAction(false);
        }
        //Ha nincs nem csinal semmit
        else if(virologusID == 0 && !board.getAction()) {
        	System.out.println("The player doesn't have any more actions!");
        	return;
        }
        
        
        boolean exists = false;
        /*Megnezi, hogy megadott nevu GCode letezik-e*/
        if(args[2].equals(StringConstants.PARALYZE)) {
        	/*Vegigmegy az osszes letrehozott GCode-on, es ha megtalalja azt a nevut, ami az inputon van, akkor lefuttatja a craft metodust*/
        	for(int i = 0; i < board.getGenetikaiKodok().size(); i++) {
        		if(board.getGenetikaiKodok().get(i).getEffect().equals(StringConstants.PARALYZE)) {
        			v1.craft(board.getGenetikaiKodok().get(i));
        			exists = true;
        		}
        	}
        }
        else if(args[2].equals(StringConstants.DANCEVIRUS)) {
        	for(int i = 0; i < board.getGenetikaiKodok().size(); i++) {
        		if(board.getGenetikaiKodok().get(i).getEffect().equals(StringConstants.DANCEVIRUS)) {
        			v1.craft(board.getGenetikaiKodok().get(i));
        			exists = true;
        		}
        	}
        }
        else if(args[2].equals(StringConstants.PROTECTVIRUS)) {
        	for(int i = 0; i < board.getGenetikaiKodok().size(); i++) {
        		if(board.getGenetikaiKodok().get(i).getEffect().equals(StringConstants.PROTECTVIRUS)) {
        			v1.craft(board.getGenetikaiKodok().get(i));
        			exists = true;
        		}
        	}
        }
        else if(args[2].equals(StringConstants.FORGETVIRUS)) {
        	for(int i = 0; i < board.getGenetikaiKodok().size(); i++) {
        		if(board.getGenetikaiKodok().get(i).getEffect().equals(StringConstants.FORGETVIRUS)) {
        			v1.craft(board.getGenetikaiKodok().get(i));
        			exists = true;
        		}
        	}
        }
        
        if(!exists)
        	System.out.println("The given Genetical Code doesn't exist!");
	}
}
