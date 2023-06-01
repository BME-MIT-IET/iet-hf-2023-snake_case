package commands;

import src.*;

public class Equip {
    public void equip(String[] args, Board board){
        if (args.length > 3 || args.length < 2){
            System.out.println("Number of parameters doesn't match!");
        }
        /*Melyik virologushoz adunk*/
        /*A parancsban virologist volt-e megadva*/
        if(args[1].length() < 10 || !args[1].substring(0,10).equals(StringConstants.VIROLOGIST)) {
            System.out.println("virologist was expected, but got something else!");
            return;
        }
        String vID = args[1].substring(10);
        int vIDInt;
        try {
            vIDInt = Integer.parseInt(vID);
        }catch(NumberFormatException ex){
            System.out.println("Virologist ID is invalid!");
            return;
        }
        /*Nincs szam, vagy a szam nem ad meg egy letezo virologust*/
        if(vID.equals("")) {
            System.out.println("Virologist ID is missing!");
            return;
        }
        else if(vIDInt >= board.getVirologusok().size() ){
            System.out.println("I can't find that virologist.");
            return;
        }

        /*felszereles ID-je*/                  //regex a nem szamok atirasara
        String eqID = args[2].replaceAll("[^\\d]", "");

        int eqIDInt;
        try {
            eqIDInt = Integer.parseInt(eqID);
        }catch(NumberFormatException ex){
            System.out.println("Equipment ID is invalid!");
            return;
        }
        /*Ha már valaki felvette*/
        if(board.getFelszerelesek().get(eqIDInt).getEquipped()){
            System.out.println("Someone already equipped this item!");
        }
        else{
            /*ha nincs valakinel mar akkor felveszi*/
            board.getVirologusok().get(vIDInt).equip(board.getFelszerelesek().get(eqIDInt));
            System.out.println("Virologist" + vID +  " has equipped the following item: "
                    + board.getFelszerelesek().get(eqIDInt).getName() + eqID);
        }
    }
}
