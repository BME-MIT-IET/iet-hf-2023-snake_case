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
			for(Effect ef : board.getVirologusok().get(0).getEffects().getEffects()){
				if(ef.getMyEffect().equals("bearvirus") || ef.getMyEffect().equals("virusdance")){
					board.setAction(false);
					board.setMove(false);
				}
			}
	        for(int i = 0; i < board.getMezok().size(); i++) {
	        	if(board.getMezok().get(i).getClass().getSimpleName().equals("Warehouse")) {
	        		( (Warehouse)board.getMezok().get(i) ).update();
	        	}
	        }
			
			board.getVirologusok().get(0).update();
            System.out.println("The Player ends their turn in testing mode, so the AI doesn't act.");
            return;
            
        }
        //Reseteli a jatekos akcioit
        board.setAction(true);
        board.setMove(true);
		/*Ha jatekos medve akkor ne tudjon lepni*/
		for(Effect ef : board.getVirologusok().get(0).getEffects().getEffects()){
			if(ef.getMyEffect().equals("bearvirus") || ef.getMyEffect().equals("virusdance")){
				board.setAction(false);
				board.setMove(false);
			}
		}
        System.out.println("The Player ends their turn.");
        
        //Meghivja minden Update metodusat
        for(int i = 0; i < board.getVirologusok().size(); i++) {
        	if (i == 0) {
        		board.getVirologusok().get(i).update();
        	}
        	else {
        		//Egy egyszeru AI, ami lepked mezokre, ha tud Collect-elni megprobal, ha massal talalkozik akkor menekul vagy tamad
        		double movedecision = Math.random();
        		Virologist v = board.getVirologusok().get(i);
				/*Ha medve akkor a BearEffect mozgast hajtsa vegre*/
				if(v.getEffects().searchForEffect("bearvirus") || v.getEffects().searchForEffect("virusdance")){
					v.update();
				}
				else{

					//eloszor megnezi, mozogjon-e
					if(movedecision >= 0.3) {
						//Ebben az esetben mozog egy szomszedos mezore, ha van
						v.move(v.getField().getNeighbours().get( (int)(Math.random() * v.getField().getNeighbours().size()) ));
					}

					//utana probal dolgokat csinalni
					double actiondecision = Math.random();

					if(actiondecision >= 0.6 && v.getField().getVirologists().size() != 0 && v.getInv().getAgents().size() != 0) {
						//Tamadni probal, de csak akkor, ha van egy masik virologus vele egy mezon es ha van mivel
						String mivel = v.getInv().getAgents().get( (int)(Math.random() * v.getInv().getAgents().size()) ).getEffect().getMyEffect();
						Virologist celpont = v.getField().getVirologists().get( (int)Math.random() * v.getField().getVirologists().size());
						v.attack(v, celpont, mivel);
					}
					else if(actiondecision >= 0.4 && !v.getField().getClass().getSimpleName().equals("Field")) {
						//Megprobal Collectelni, de csak akkor, ha nem egy Field-en all
						v.collect();
					}
					else if(actiondecision >= 0 && v.getInv().getGcodes().size() != 0 && v.getInv().getMaterials().getAmino() > 0 && v.getInv().getMaterials().getNukleo() > 0) {
						//Craftolni probal, ha ismer Gcode-t es van valamennyi alapanyaga
						v.craft(v.getInv().getGcodes().get( (int)(Math.random() * v.getInv().getGcodes().size()) ));
					}
					else {
						//Lep megegyet, ha semmi mast nem csinal
						v.move(v.getField().getNeighbours().get( (int)(Math.random() * v.getField().getNeighbours().size()) ));
					}
					v.update();
				}
			}
        }
        for(int i = 0; i < board.getMezok().size(); i++) {
        	if(board.getMezok().get(i).getClass().getSimpleName().equals("Warehouse")) {
        		( (Warehouse)board.getMezok().get(i) ).update();
        	}
        }
        
        
    }
}
