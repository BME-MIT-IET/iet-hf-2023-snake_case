package src;

import java.io.Serializable;
import java.util.ArrayList;
import effects.*;

public class Inventory implements Serializable {
    /*Valtozok*/
    private final ArrayList<GCode> gCodes= new ArrayList<>();
    private final ArrayList<Equipment> equipment = new ArrayList<>();
    private final ArrayList<Agent> agents = new ArrayList<>();
    private final Materials materials;
    private static final String INV_CREATED = "Inventory: I have created a new ";

    Inventory(int amino, int nukelo){
        materials = new Materials(amino, nukelo);
    }

    /*Fuggvenyek*/
    /*Letrehoz egy agenst, amely megfelel a genetikai kodnak*/
    public boolean createAgent(GCode code){
        /*Megnezi, hogy a listaban bent van-e a genetikai kod*/
        if(gCodes.contains(code)) {
            /*A genetikai kod benulas*/
            if (code.getEffect().equals(StringConstants.PARALYZE)) {
                /*Nem tudom, hogy ide kell-e a material check, levonas, de egyenlore nem teszzem be*/
                Agent newAgent = new Agent(new Paralyzed());
                agents.add(newAgent);
                System.out.println(INV_CREATED+StringConstants.PARALYZE+" agent");
                return true;
            }
            else if(code.getEffect().equals(StringConstants.DANCEVIRUS)){
                Agent newAgent = new Agent(new VirusDance());
                agents.add(newAgent);
                System.out.println(INV_CREATED+StringConstants.DANCEVIRUS);
                return true;
            }
            else if(code.getEffect().equals(StringConstants.PROTECTVIRUS)){
                Agent newAgent = new Agent(new ProtectEffect());
                agents.add(newAgent);
                System.out.println(INV_CREATED+StringConstants.PROTECTVIRUS);
                return true;
            }
            else if(code.getEffect().equals(StringConstants.FORGETVIRUS)){
                Agent newAgent = new Agent(new ForgetEffect());
                agents.add(newAgent);
                System.out.println(INV_CREATED+StringConstants.FORGETVIRUS);
                return true;
            }
            else if(code.getEffect().equals(StringConstants.BEARVIRUS)){
                Agent newAgent = new Agent(new BearEffect());
                agents.add(newAgent);
                System.out.println(INV_CREATED+StringConstants.BEARVIRUS);
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
    public void removeAgent(Agent a){
        if(!agents.isEmpty()){
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
    public ArrayList<GCode> getGcodes(){
        return gCodes;
    }

    /*Hozzaad egy genetikai kodot az inventory listajahoz*/
    public void addGCode(GCode gcode){
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
    public void addEquipment(Equipment eq){
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
    public void removeItem(Equipment eq){
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
    public void changeMaterial(char mat, int value){
        if(mat == 'a'){
            materials.changeAmino(value);
            System.out.println("Inventory: I have changed number of aminoacids I have to: " + materials.getAmino());
        }
        else if(mat == 'n'){
            materials.changeNukleo(value);
            System.out.println("Inventory: I have changed number of nukleotids I have to: " + materials.getNukleo());
        }
        else{
            System.out.println("Inventory: That material is unknown.");
        }
    }

    /*Ez hivatalosan nincs bent a class diagramban, de szerintem kell/jó ha van*/
    public Materials getMaterials() {
        return materials;
    }


}
