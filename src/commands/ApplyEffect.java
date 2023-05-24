package commands;

import src.*;
import effects.*;

public class ApplyEffect {
    public void applyEffect(String[] args, Board board){
        if (args.length > 3 || args.length < 2){
            System.out.println("Number of parameters doesn't match!");
        }
        /*Melyik virologushoz adunk*/
        /*A parancsban virologist volt-e megadva*/
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
        for (GCode gencode : board.getGenetikaiKodok()) {
            if(gencode.getEffect().equals(args[2])){
                Effect effect;
                switch(args[2]){
                    case("paralyze"):
                        effect = new Paralyzed();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().ApplyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case("virusdance"):
                        effect = new VirusDance();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().ApplyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case("protectvirus"):
                        effect = new ProtectEffect();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().ApplyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case("forgetvirus"):
                        effect = new ForgetEffect();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().ApplyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case("bearvirus"):
                        effect = new BearEffect();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().ApplyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        for(int i = 0; i < board.getVirologusok().get(Integer.parseInt(vID)).GetInventory().getAgents().size(); i++){
                            board.getVirologusok().get(Integer.parseInt(vID)).GetInventory().RemoveAgent(board.getVirologusok().get(Integer.parseInt(vID)).GetInventory().getAgents().get(i));
                        }
                        board.getVirologusok().get(Integer.parseInt(vID)).LearnGCode(new GCode(0,0,"bearvirus"));
                        break;
                    default:
                        throw new UnsupportedOperationException("This effect has not been implemented to ApplyEffect yet");
                }
                System.out.println("virologist" + vID + " has been affected by " + args[2]);
                break;
            }
        }

    }
}
