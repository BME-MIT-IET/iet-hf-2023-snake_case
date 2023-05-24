package src;

import java.io.Serializable;
import java.util.ArrayList;
import effects.*;

public class Inventory implements Serializable {
    /*Valtozok*/
    private ArrayList<GCode> gCodes= new ArrayList<>();
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private ArrayList<Agent> agents = new ArrayList<>();
    private Materials materials;

    Inventory(int amino, int nukelo){
        materials = new Materials(amino, nukelo);
    }

    /*Fuggvenyek*/
    /*Letrehoz egy agenst, amely megfelel a genetikai kodnak*/
    public boolean CreateAgent(GCode code){
        /*Megnezi, hogy a listaban bent van-e a genetikai kod*/
        if(gCodes.contains(code)) {
            /*A genetikai kod benulas*/
            if (code.getEffect().equals("paralyze")) {
                /*Nem tudom, hogy ide kell-e a material check, levonas, de egyenlore nem teszzem be*/
                Agent newAgent = new Agent(new Paralyzed());
                agents.add(newAgent);
                System.out.println("Inventory: I have created a new paralyze agent");
                return true;
            }
            else if(code.getEffect().equals("virusdance")){
                Agent newAgent = new Agent(new VirusDance());
                agents.add(newAgent);
                System.out.println("Inventory: I have created a new virusdance agent");
                return true;
            }
            else if(code.getEffect().equals("protectvirus")){
                Agent newAgent = new Agent(new ProtectEffect());
                agents.add(newAgent);
                System.out.println("Inventory: I have created a new protect agent");
                return true;
            }
            else if(code.getEffect().equals("forgetvirus")){
                Agent newAgent = new Agent(new ForgetEffect());
                agents.add(newAgent);
                System.out.println("Invenotry: I have created a new forgetvirus agent");
                return true;
            }
            else if(code.getEffect().equals("bearvirus")){
                Agent newAgent = new Agent(new BearEffect());
                agents.add(newAgent);
                System.out.println("Invenotry: I have created a new bearvirus agent");
                return true;
            }
            /*---------------------------------------------------------------------------------------------
             * IDE KELL MEG A TOBBI AGENS TIPUS MEGOLDASA IS
             *---------------------------------------------------------------------------------------------*/
            //Szerintem implementaltam az osszeset.
            
            System.out.println("Inventory: I can't find that effect, are you sure you inicialized the genetical code the right way?");
            return false;
        }
        System.out.println("Inventory: I don't know that genetical code yet.");
        return false;
    }

    /*Elvesz egy agenst*/
    public void RemoveAgent(Agent a){
        if(agents.size() != 0){
            if(agents.contains(a)){//Ellenorzi, hogy a birtokaban van-e az agens
                agents.remove(a);
                System.out.println("Inventory: I have removed the agent.");
                return;
            }
            System.out.println("Inventory: I don't have that agent.");
            return;
        }
        System.out.println("Inventory: I don't have any agents.");
    }

    /*Visszaadja az inventoryban lévo agenseket*/
    public ArrayList<Agent> getAgents(){
        return agents;
    }

    /*Visszaadja az inventoryban lévo genetikai kodokat*/
    public ArrayList<GCode> GetGcodes(){
        return gCodes;
    }

    /*Hozzaad egy genetikai kodot az inventory listajahoz*/
    public void AddGCode(GCode gcode){
        if(gCodes.size() < 4){//Ellenorzi, hogy csak maximum 5 lehet                                         //Itt ha 5 LESZ mar ki kell lepnie! (nem <= 5)
            if(gCodes.contains(gcode)){//Ellenorzi, hogy ismeri-e mar ezt a genetikai kodot
                System.out.println("Inventory: I already know that genetical code.");
                return;
            }
            gCodes.add(gcode);
            System.out.println("Inventory: I learned a new genetical code.");
            return;
        }
        System.out.println("Ran out of learnable genetical codes.");
    }

    /*Hozzaad egy felszerelest az inventory listajahoz*/
    public void AddEquipment(Equipment eq){
        if(equipment.size() < 3){//Ellenorzi, hogy nem lepne-e at a maxmimalis tarolo kapacitast
            if(equipment.contains(eq)){//Ellenorzi, hogy a birtokaban van-e mar az a targy
                System.out.println("Inventory:I already have that equipment.");
                return;
            }
            equipment.add(eq);
            System.out.println("Inventory: I have added the equipment");
        }
        else {
        	System.out.println("Inventory: The Inventory is full, i can't add the new equipment!");
        }
    }

    /*Kitorli a parameterkent kapott felszerelest a felszerelesek listabol
    * @param eq = A kitorlendo felszereles*/
    public void RemoveItem(Equipment eq){
        int talalt = -1;
        for(int i = 0; i < equipment.size(); i++){
            if(equipment.get(i) == eq){
                talalt = i;
            }
        }
        if(talalt != -1){
            equipment.remove(talalt);
        }
    }

    /*Visszaadja a felszereles listat*/
    public ArrayList<Equipment> GetEquipments(){
        return equipment;
    }

    /*Hozzáad egy darab nyersanyagot a választott nyersanyag tipusbol, a taroloba*/
    /*Valtoztatva lett public void AddMaterial(boolean mat)-ról*/
    public void ChangeMaterial(char mat, int value){
        if(mat == 'a'){
            materials.ChangeAmino(value);
            System.out.println("Inventory: I have changed number of aminoacids I have to: " + materials.GetAmino());
        }
        else if(mat == 'n'){
            materials.ChangeNukleo(value);
            System.out.println("Inventory: I have changed number of nukleotids I have to: " + materials.getNukleo());
        }
        else{
            System.out.println("Inventory: That material is unknown.");
        }
    }

    /*Torol egy genetikai kodot a listabol*/
/*    public void RemoveGCode(GCode g) {
        if (gCodes.size() != 0) {//Ellenorzi, hogy ures-e a lista
            if (!gCodes.contains(g)) {
                System.out.println("This genetical code is not on the list.");
                return;
            }
            gCodes.remove(g);
            System.out.println("Inventory: I forgot a genetical code.");
            return;
        }
        System.out.println("I don't know any genetical code yet.");
    }*/

    /*Ez hivatalosan nincs bent a class diagramban, de szerintem kell/jó ha van*/
    public Materials getMaterials() {
        return materials;
    }


}
