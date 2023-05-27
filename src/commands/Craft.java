package commands;

import src.Board;
import src.StringConstants;
import src.Virologist;

import javax.naming.OperationNotSupportedException;

public class Craft {
	
    /*Virologus letrehozhat agenseket
     * @param args = A felhasznalotol kapott input
     * Syntax: craft [ki] [mit]*/
	public void craft(String[] args, Board board) {

		/*A craft parancsban megadott parameterek ellenorzese*/
		if(inputCheck(args, board)){
			return;
		}

		String vID = args[1].substring(10);
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

		/*Megnezi, hogy megadott nevu GCode letezik-e*/
        String gcodeName = args[2];
		boolean exists = false;

		switch (gcodeName){
			case StringConstants.PARALYZE:
				if(craftAgent(board, StringConstants.PARALYZE, v1)){
					exists = true;
				}
				break;
			case StringConstants.DANCEVIRUS:
				if(craftAgent(board, StringConstants.DANCEVIRUS, v1)){
					exists = true;
				}
				break;
			case StringConstants.PROTECTVIRUS:
				if(craftAgent(board, StringConstants.PROTECTVIRUS, v1)){
					exists = true;
				}
				break;
			case StringConstants.FORGETVIRUS:
				if(craftAgent(board, StringConstants.FORGETVIRUS, v1)){
					exists = true;
				}
				break;
			default:
				throw new UnsupportedOperationException();
		}
        
        if(!exists)
        	System.out.println("The given Genetical Code doesn't exist!");
	}

	private boolean inputCheck(String[] args, Board board){
		/* Teszteli, hogy van-e egyaltalan eleg parameter */
		if(args.length < 3){
			System.out.println("There are not enough parameters!");
			return true;
		}

		/*Melyik virologushoz adunk*/
		/*A parancsban virologist vot-e megadva*/
		if(args[1].length() < 10 || !args[1].startsWith(StringConstants.VIROLOGIST)) {
			System.out.println("virologist was expected, but got something else!");
			return true;
		}
		String vID = args[1].substring(10);
		/*Nincs szam, vagy a szam nem ad meg egy letezo virologust*/
		if(vID.equals("")) {
			System.out.println("Virologist ID is missing!");
			return true;
		}
		else if(Integer.parseInt(vID) >= board.getVirologusok().size() ){
			System.out.println("I can't find that virologist.");
			return true;
		}
		return false;
	}

	/*Vegigmegy az osszes letrehozott GCode-on, es ha megtalalja azt a nevut, ami az inputon van, akkor lefuttatja a craft metodust*/
	private boolean craftAgent(Board board, String name, Virologist v1){
		for(int i = 0; i < board.getGenetikaiKodok().size(); i++) {
			if(board.getGenetikaiKodok().get(i).getEffect().equals(name)) {
				v1.craft(board.getGenetikaiKodok().get(i));
				return true;
			}
		}
		return false;
	}
}
