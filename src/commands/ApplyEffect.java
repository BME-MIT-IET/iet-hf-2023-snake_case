package commands;

import src.*;
import effects.*;

public class    ApplyEffect {
    public void applyEffect(String[] args, Board board){
        if(inputSyntaxCheck(args)){
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
                    case(StringConstants.PARALYZE):
                        effect = new Paralyzed();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().applyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case(StringConstants.DANCEVIRUS):
                        effect = new VirusDance();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().applyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case(StringConstants.PROTECTVIRUS):
                        effect = new ProtectEffect();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().applyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case(StringConstants.FORGETVIRUS):
                        effect = new ForgetEffect();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().applyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        break;
                    case(StringConstants.BEARVIRUS):
                        effect = new BearEffect();
                        board.getVirologusok().get(Integer.parseInt(vID)).getEffects().applyEffect(board.getVirologusok().get(Integer.parseInt(vID)), effect);
                        for(int i = 0; i < board.getVirologusok().get(Integer.parseInt(vID)).getInv().getAgents().size(); i++){
                            board.getVirologusok().get(Integer.parseInt(vID)).getInv().removeAgent(board.getVirologusok().get(Integer.parseInt(vID)).getInv().getAgents().get(i));
                        }
                        board.getVirologusok().get(Integer.parseInt(vID)).learnGCode(new GCode(0,0,StringConstants.BEARVIRUS));
                        break;
                    default:
                        throw new UnsupportedOperationException("This effect has not been implemented to ApplyEffect yet");
                }
                System.out.println(StringConstants.VIROLOGIST + vID + " has been affected by " + args[2]);
                break;
            }
        }
    }
    private boolean inputSyntaxCheck(String[] args){
        if (args.length > 3 || args.length < 2){
            System.out.println("Number of parameters doesn't match!");
            return true;
        }
        /*Melyik virologushoz adunk*/
        /*A parancsban virologist volt-e megadva*/
        if(args[1].length() < 10 || !args[1].startsWith("virologist")) {
            System.out.println("virologist was expected, but got something else!");
            return true;
        }
        return false;
    }
}
