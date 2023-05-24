package commands;

import src.Board;
import src.Effect;
import src.Virologist;
import src.Warehouse;

public class EndTurn {

	/*Jatekos koret lezarja
     * @param args = A felhasznalotol kapott input
     * Syntax: endturn <testing>*/
    public void endTurn(String[] args, Board board){
        if(args.length > 2){
            System.out.println("Too many arguments!");
            return;
        }
        //tesztelesi okokbol megadhatjuk, hogy az AI magatol ne cselekedjen
        if(args.length > 1 && args[1].equals("testing")) {
        	//Reseteli a jatekos akcioit
            board.setAction(true);
            board.setMove(true);
			for(Effect ef : board.getVirologusok().get(0).GetEffects().GetEffects()){
				if(ef.getEffect().equals("bearvirus") || ef.getEffect().equals("virusdance")){
					board.setAction(false);
					board.setMove(false);
				}
			}
	        for(int i = 0; i < board.getMezok().size(); i++) {
	        	if(board.getMezok().get(i).getClass().getSimpleName().equals("Warehouse")) {
	        		( (Warehouse)board.getMezok().get(i) ).Update();
	        	}
	        }
			
			board.getVirologusok().get(0).Update();
            System.out.println("The Player ends their turn in testing mode, so the AI doesn't act.");
            return;
            
        }
        //Reseteli a jatekos akcioit
        board.setAction(true);
        board.setMove(true);
		/*Ha jatekos medve akkor ne tudjon lepni*/
		for(Effect ef : board.getVirologusok().get(0).GetEffects().GetEffects()){
			if(ef.getEffect().equals("bearvirus") || ef.getEffect().equals("virusdance")){
				board.setAction(false);
				board.setMove(false);
			}
		}
        System.out.println("The Player ends their turn.");
        
        //Meghivja minden Update metodusat
        for(int i = 0; i < board.getVirologusok().size(); i++) {
        	if (i == 0) {
        		board.getVirologusok().get(i).Update();
        	}
        	else {
        		//Egy egyszeru AI, ami lepked mezokre, ha tud Collect-elni megprobal, ha massal talalkozik akkor menekul vagy tamad
        		double movedecision = Math.random();
        		Virologist v = board.getVirologusok().get(i);
				/*Ha medve akkor a BearEffect mozgast hajtsa vegre*/
				if(v.getEffects().SearchForEffect("bearvirus") || v.getEffects().SearchForEffect("virusdance")){
					v.Update();
				}
				else{

					//eloszor megnezi, mozogjon-e
					if(movedecision >= 0.3) {
						//Ebben az esetben mozog egy szomszedos mezore, ha van
						v.Move(v.getField().getNeighbours().get( (int)(Math.random() * v.getField().getNeighbours().size()) ));
					}

					//utana probal dolgokat csinalni
					double actiondecision = Math.random();

					if(actiondecision >= 0.6 && v.getField().getVirologists().size() != 0 && v.GetInventory().getAgents().size() != 0) {
						//Tamadni probal, de csak akkor, ha van egy masik virologus vele egy mezon es ha van mivel
						String mivel = v.GetInventory().getAgents().get( (int)(Math.random() * v.GetInventory().getAgents().size()) ).getEffect().getEffect();
						Virologist celpont = v.getField().getVirologists().get( (int)Math.random() * v.getField().getVirologists().size());
						v.Attack(v, celpont, mivel);
					}
					else if(actiondecision >= 0.4 && !v.getField().getClass().getSimpleName().equals("Field")) {
						//Megprobal Collectelni, de csak akkor, ha nem egy Field-en all
						v.Collect();
					}
					else if(actiondecision >= 0 && v.GetInventory().GetGcodes().size() != 0 && v.GetInventory().getMaterials().GetAmino() > 0 && v.GetInventory().getMaterials().getNukleo() > 0) {
						//Craftolni probal, ha ismer Gcode-t es van valamennyi alapanyaga
						v.Craft(v.GetInventory().GetGcodes().get( (int)(Math.random() * v.GetInventory().GetGcodes().size()) ));
					}
					else {
						//Lep megegyet, ha semmi mast nem csinal
						v.Move(v.getField().getNeighbours().get( (int)(Math.random() * v.getField().getNeighbours().size()) ));
					}
					v.Update();
				}
			}
        }
        for(int i = 0; i < board.getMezok().size(); i++) {
        	if(board.getMezok().get(i).getClass().getSimpleName().equals("Warehouse")) {
        		( (Warehouse)board.getMezok().get(i) ).Update();
        	}
        }
        
        
    }
}
