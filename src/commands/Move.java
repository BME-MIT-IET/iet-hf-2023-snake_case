package commands;

import src.Board;
import src.StringConstants;

public class Move {
    /*Mozgas
     *@param args = A teljes parancs sor amit kap a felhasznalotol
     * Syntax: move [kit] [hova]*/

    private static final  String NO_FIELD = "I can't find that field.";
    public void move(String[] args, Board con){
        if(con.getMezok().size()<2){
            System.out.println("Not enough fields! You need at least 2 to move from one to another.");
            return;
        }

        if(args[1].length() < 11){
            System.out.println("I can't find that virologist!");
            return;
        }
        String szam = args[1].substring(10);
        /*Ha elirna az illeto es csak virologistot irna*/
        if(szam.length() == 0){
            System.out.println("I can't find that virologist.");
            return;
        }
        int sz = Integer.parseInt(szam);
        
        int FID = Integer.parseInt(args[2].replaceAll("[^0-9]", ""));
        if(con.getMezok().get(FID).getNeighbours().contains(con.getVirologusok().get(sz).getField())) {
        	//Teszteli, hogy a virologusnak van e meg akcioja
            //Elobb ellenorzi, hogy mozgott e mar, utana kerul csak akcioba
            if(sz == 0 && con.getMove()) {
            	con.setMove(false);
            }
            else if(sz == 0 && con.getAction()) {
            	con.setAction(false);
            }
            //Ha nincs nem csinal semmit
            else if(sz == 0 && !con.getAction() && !con.getMove()) {
            	System.out.println("The player doesn't have any more actions or moves left!");
            	return;
            }
        }
        else {
        	System.out.println("The chosen field is not neighbour of the field you are standing on!");
        	return;
        }
        movePhysically(args, con, sz);
    }

    private void movePhysically(String[] args, Board con, int sz){
        if(args[2].contains(StringConstants.FIELD)){
            String szam2 = args[2].substring(5);
            if(executeMove(szam2, con, sz)){
                return;
            }
        }
        else if(args[2].contains(StringConstants.LABORATORY)){
            String szam2 = args[2].substring(10);
            if(executeMove(szam2, con, sz)){
                return;
            }
        }
        else if(args[2].contains(StringConstants.WAREHOUSE)){
            String szam2 = args[2].substring(9);
            if(executeMove(szam2, con, sz)){
                return;
            }
        }
        else if(args[2].contains(StringConstants.SHELTER)) {
            String szam2 = args[2].substring(7);
            if(executeMove(szam2, con, sz)){
                return;
            }
        }
        System.out.println("The chosen field is not neighbour of the field you are standing on!");
    }

    private boolean executeMove(String szam2, Board con, int sz){
        if(szam2.length() == 0){
            System.out.println(NO_FIELD);
            return true;
        }
        int hva = Integer.parseInt(szam2);
        if(con.getMezok().get(hva).getNeighbours().contains(con.getVirologusok().get(sz).getField())){
            con.getVirologusok().get(sz).move(con.getMezok().get(hva));
            return true;
        }
        return false;
    }
}
