package commands;

import src.Board;
import src.Virologist;

public class Collect {

    /*Virologus felvehet felszereleseket
     * @param args = A felhasznalotol kapott input
     * Syntax: collect [kivel]*/
    public void Collect(String[] args, Board board){
        if(args.length < 2){
            System.out.println("There are not enough arguments!");
            return;
        }
        /*Melyik virologushoz adunk*/
        /*A parancsban virologist vot-e megadva*/
        if(args[1].length() < 10 || !args[1].substring(0,10).equals("virologist")) {
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
        
        
        //Teszteli, hogy a virologusnak van e meg akcioja
        if(virologusID == 0 && board.getAction()) {
        	board.setAction(false);
        }
        //Ha nincs nem csinal semmit
        else if(virologusID == 0 && !board.getAction()) {
        	System.out.println("The player doesn't have any more actions!");
        	return;
        }
        
        
        
        
        Virologist v1 = board.getVirologusok().get(virologusID);
        /*Felvetel shelterbol*/
        ///Ezt a reszt nem ertettem, mivel args[2] nem is letezhet itt, igy inkabb csak kivettem az if-bol, itt hagyom ha valamit szandekoznatok vele megiscsak -Dani
        /*
	        if(args[2].equals("shelter") || args[2].equals("warehouse") || args[2].equals("laboratory")){
	            v1.getField().Collect(v1);
	        }
	    */
        v1.Collect();
    }
}
