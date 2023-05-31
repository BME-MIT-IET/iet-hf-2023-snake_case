package commands;

import src.*;

import static src.StringConstants.*;


public class List {
	/*Lekerdezhetunk informaciot a jatekallasrol
     * @param args = A felhasznalotol kapott input
     * Syntax: 	list virologist
     * 			list virologist[szam] [field/virologists/gcodes/equipment/agents/effects/materials]
     * 			list cost [Genetikai kod]
     * 			list field
     * 			list field[szam] [virologists/neighbours]
     * 			list laboratory[szam] [virologists/neighbours/gcode]
     * 			list shelter[szam] [virologists/neighbours/equipment]
     * 			list warehouse[szam] [virologists/neighbours/material]
     * 																		*/

	private static final String NOT_ENOUGH_PARAM = "Not enough parameters!";
	public void list(String[] args, Board board) {
		if(args.length < 2) {
			System.out.println(NOT_ENOUGH_PARAM);
			return;
		}

		/* Virologussal kapcsolatos kilistazasok*/
		if ( args[1].contains(StringConstants.VIROLOGIST)) {
			virologistListActions(args, board);
		}
		/*Genetikai kodok arat adja vissza*/
		else if(args[1].contains("cost") && args[1].length() < 5) {
			showCostOfGenCode(args, board);
		}

		/*Mindegyik field kiirasa*/
		else if ( args[1].contains(FIELD) && args[1].length() == 5) {
			listAllFields(args, board);
		}
		/*Egyes fieldek tulajdonsagainak kiirasa*/
		else if (args[1].contains(FIELD) || args[1].contains(LABORATORY) || args[1].contains(SHELTER) || args[1].contains(WAREHOUSE)) {
			fieldListActions(args, board);
		}
		else {
			System.out.println("Unknown list command!");
		}
	}

	private void virologistListActions(String[] args, Board board){
		/* kilistazza az osszes virologust, akit eddig letrehoztunk*/
		if(listCreatedVirologists(args, board)){
			return;
		}

		/* Egy adott virologusnak kerdezzuk le valamilyen dolgait*/
		/* Eloszor teszteljuk, jo e az input*/
		String vID = args[1].replaceAll("[^0-9]", "");
		if(!virologistExistsCheck(args, board, vID)){
			return;
		}

		Virologist v1 = board.getVirologusok().get(Integer.parseInt(vID));

		/*Lekerdezi, hol all a virologus*/
		if(args[2].equals(FIELD)){
			getFieldVirologistStandingOn(v1, board);
		}
		/*Lekerdezi, hogy kik allnak a virologus mezojen*/
		else if(args[2].equals("virologists")) {
			listAllVirologistOnFieldStandingOn(v1, board);
		}
		/*Lekerdezi, hogy milyen Genetikai Kodokat ismer a virologus*/
		else if(args[2].equals("gcodes")) {
			listAllKnownCodesByVirologist(v1);
		}
		/*Lekerdezi milyen felszerelesei vannak a virologusnak*/
		else if(args[2].equals("equipment")) {
			listAllEquipmentVirologist(v1);
		}
		/*Lekerdezi milyen agensei vannak a virologusnak*/
		/*FONTOS: ez meg csak az osztalyok nevevel foglalkozik, mert mast nem erek el, igy a parancs neve es az agens neve nem egyezik meg!!!!!
		 * 			Ezt az egysegesiteskor at kell majd irni*/
		else if(args[2].equals("agents")) {
			listAllKnownAgentsByVirologist(v1);
		}
		/*Lekerdezi milyen effectek vann a virologuson
		 * FONTOS: -||- */
		else if(args[2].equals("effects")) {
			listAllEffectsOnVirologist(v1);
		}
		/*Lekerdezi, mennyi materialja van a virologusnak*/
		else if(args[2].equals("materials")) {
			listAllMaterialsVirologist(v1);
		}
	}

	private void fieldListActions(String[] args, Board board){
		if(args.length < 3) {
			System.out.println(NOT_ENOUGH_PARAM);
			return;
		}

		if(!doesFieldExist(args, board)){
			return;
		}


		/*field tulajdonsagai*/
		String fID = args[1].replaceAll("[^0-9]", "");
		if(fID.equals("")){
			System.out.println("Field ID is missing!");
			return;
		}
		int fieldID = Integer.parseInt(fID);

		/*Kiirja melyik virologusok vannak a mezon*/
		if (args[2].equals("virologists")) {
			listAllVirologistOnField(fieldID, board);
		}
		/*Kiirja a megadott mezo szomszedait*/
		else if (args[2].equals("neighbours")) {
			listAllNeightbours(args, board, fieldID);
		}
		/*Labor eseten kiirja a laborban talalhato genetikai kodot*/
		else if (args[1].contains(LABORATORY) && args[2].equals("gcode")) {
			System.out.println("The Laboratory's Genetical Code is: " + ((Laboratory)board.getMezok().get(fieldID)).getGCode().getEffect());
		}
		else if(args[1].contains(SHELTER) && args[2].equals("equipment")) {
			System.out.println("The Shelter's Equipment is: " + ((Shelter)board.getMezok().get(fieldID)).getEquipment().getName());
		}
		else if(args[1].contains(WAREHOUSE) && args[2].equals("material")) {
			System.out.println("The Warehouse currently stores: " + ((Warehouse)board.getMezok().get(fieldID)).getAmount()
					+ ( ((Warehouse)board.getMezok().get(fieldID)).getType() == 'a' ? " Aminoacid":" Nukleotid" ) );
		}
	}

	private boolean listCreatedVirologists(String[] args, Board board){
		if ( args.length < 3 && args[1].length() == 10) {
			System.out.println("Currently created virologists:");
			for (int i = 0; i < board.getVirologusok().size();i++) {
				System.out.println(StringConstants.VIROLOGIST + i);
			}
			return true;
		}
		return false;
	}



	private boolean virologistExistsCheck(String[] args, Board board, String vID){

		/*Nincs szam, vagy a szam nem ad meg egy letezo virologust*/
		if(vID.equals("")) {
			System.out.println("Virologist ID is missing!");
			return false;
		}
		else if(Integer.parseInt(vID) >= board.getVirologusok().size() ){
			System.out.println("I can't find that virologist.");
			return false;
		}
		else if (vID.length() + 10 < args[1].length()) {
			System.out.println("Something isn't right with the virologist name given.");
			return false;
		}
		else if(args.length < 3) {
			System.out.println(NOT_ENOUGH_PARAM);
			return false;
		}
		return true;
	}

	private void getFieldVirologistStandingOn(Virologist v1, Board board){
		for (int i = 0; i < board.getMezok().size(); i++) {
			if (v1.getField().equals(board.getMezok().get(i))) {
				System.out.println(v1.getField().getClass().getSimpleName().toLowerCase() + i);
			}
		}
	}

	private void listAllVirologistOnFieldStandingOn(Virologist v1, Board board){
		System.out.println("Virologists on the same field:");
		for (int i = 0; i < board.getVirologusok().size(); i++) {
			if(v1.getField().equals(board.getVirologusok().get(i).getField())) {
				System.out.println(StringConstants.VIROLOGIST + i);
			}
		}
	}

	private void listAllKnownCodesByVirologist(Virologist v1){
		System.out.println("Genetical Codes the virologist knows:");
		for (int i = 0; i < v1.getInv().getGcodes().size(); i++) {
			System.out.println(v1.getInv().getGcodes().get(i).getEffect());
		}
	}

	private void listAllEquipmentVirologist(Virologist v1){
		System.out.println("Equipment the virologist has:");
		for (int i = 0; i < v1.getInv().GetEquipments().size(); i++) {
			System.out.println(v1.getInv().GetEquipments().get(i).getName());
		}
	}

	private void listAllKnownAgentsByVirologist(Virologist v1){
		System.out.println("Agents the virologist has:");
		for (int i = 0; i < v1.getInv().getAgents().size(); i++) {
			System.out.println(v1.getInv().getAgents().get(i).getEffect().getClass().getSimpleName().toLowerCase()); //Emiatt nem j�, hogy nincsenek j� nevek. -Dani
		}
	}

	private void listAllEffectsOnVirologist(Virologist v1){
		System.out.println("Effects affecting the virologist:");
		for (int i = 0; i < v1.getEffects().getEffects().size(); i++) {    //GETEFFECTS.GETEFFECTS ?????
			System.out.println(v1.getEffects().getEffects().get(i).getClass().getSimpleName().toLowerCase());
		}
	}

	private void listAllMaterialsVirologist(Virologist v1){
		System.out.println("Materials the virologist has:");
		System.out.println("Aminoacid: " + v1.getInv().getMaterials().getAmino() + "\nNukleotid: " + v1.getInv().getMaterials().getNukleo());
	}

	private void showCostOfGenCode(String[] args, Board board){
		for (int i = 0; i < board.getGenetikaiKodok().size(); i++) {
			if(board.getGenetikaiKodok().get(i).getEffect().equals(args[2])) {
				System.out.println(args[2] + " costs: \n" + board.getGenetikaiKodok().get(i).getAmino() + " Amenoacid, and "
						+ board.getGenetikaiKodok().get(i).getNukleo() + " Nukleotid.");
				return;
			}
		}
		System.out.println("I can't find that Genetical Code.");
	}

	private void listAllFields(String[] args, Board board){
		/* kilistazza az osszes virologust, akit eddig letrehoztunk*/
		if ( args.length < 3) {
			System.out.println("Currently created fields:");
			for (int i = 0; i < board.getMezok().size();i++) {
				System.out.println(board.getMezok().get(i).getClass().getSimpleName().toLowerCase() + i);
			}
		}
	}

	private boolean doesFieldExist(String[] args, Board board){
		for (int i = 0; i < board.getMezok().size(); i++) {
			/*Osszehasonlitja az inputot a letezo mezokkel (ehhez a mezok osztalyanak nevet es az indexet figyeli)*/
			if (args[1].equals(board.getMezok().get(i).getClass().getSimpleName().toLowerCase() + i)) {
				return true;
			}
		}

		System.out.println(args[1] + " doesn't exist!");
		return false;

	}

	private void listAllVirologistOnField(int fieldID, Board board){
		System.out.println("Virologists currently on the field:");
		for (int i = 0; i < board.getVirologusok().size(); i++) {
			if(board.getMezok().get(fieldID).equals(board.getVirologusok().get(i).getField())) {
				System.out.println(StringConstants.VIROLOGIST + i);
			}
		}
	}

	private void listAllNeightbours(String[] args, Board board, int fieldID){
		System.out.println("Fields neighbouring " + args[1] + ":");
		/*Osszehasonlitja a szomszedokat es az osszes mezot, hogy megtalalja az indexet*/
		for (int i = 0; i < board.getMezok().get(fieldID).getNeighbours().size(); i++) {
			for(int j = 0; j < board.getMezok().size(); j++) {
				if(board.getMezok().get(fieldID).getNeighbours().get(i).equals(board.getMezok().get(j))) {
					System.out.println(board.getMezok().get(j).getClass().getSimpleName().toLowerCase() + j);
				}
			}
		}
	}



}
