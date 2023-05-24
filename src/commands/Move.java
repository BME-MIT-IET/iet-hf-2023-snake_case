package commands;

import src.Board;

public class Move {
    /*Mozgas
     *@param args = A teljes parancs sor amit kap a felhasznalotol
     * Syntax: move [kit] [hova]*/
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

        
        if(args[2].contains("field")){
            String szam2 = args[2].substring(5);
            if(szam2.length() == 0){
                System.out.println("I can't find that field.");
                return;
            }
            int hva = Integer.parseInt(szam2);
            /*Megnezi, hogy ahova lepni szeretnenk, az szomszedja-e a jelenlegi mezonek*/
            if(con.getMezok().get(hva).getNeighbours().contains(con.getVirologusok().get(sz).getField())){
                con.getVirologusok().get(sz).Move(con.getMezok().get(hva));
                return;
            }
        }
        else if(args[2].contains("laboratory")){
            String szam2 = args[2].substring(10);
            if(szam2.length() == 0){
                System.out.println("I can't find that field.");
                return;
            }
            int hva = Integer.parseInt(szam2);
            if(con.getMezok().get(hva).getNeighbours().contains(con.getVirologusok().get(sz).getField())){
                con.getVirologusok().get(sz).Move(con.getMezok().get(hva));
                return;
            }
        }
        else if(args[2].contains("warehouse")){
            String szam2 = args[2].substring(9);
            if(szam2.length() == 0){
                System.out.println("I can't find that field.");
                return;
            }
            int hva = Integer.parseInt(szam2);
            if(con.getMezok().get(hva).getNeighbours().contains(con.getVirologusok().get(sz).getField())){
                con.getVirologusok().get(sz).Move(con.getMezok().get(hva));
                return;
            }
        }
        else if(args[2].contains("shelter")) {
            String szam2 = args[2].substring(7);
            if(szam2.length() == 0){
                System.out.println("I can't find that field.");
                return;
            }
            int hva = Integer.parseInt(szam2);
            if(con.getMezok().get(hva).getNeighbours().contains(con.getVirologusok().get(sz).getField())){
                con.getVirologusok().get(sz).Move(con.getMezok().get(hva));
                return;
            }
        }
        System.out.println("The chosen field is not neighbour of the field you are standing on!");
    }
}
