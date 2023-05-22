package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Effects.*;

public class Virologist implements Update, Serializable {
    /*Valtozok*/
    private double dodgeChance;
    private double originalDodgeChance;
    private Inventory inv;
    private Effects effects;
    private Field field;

    private static String PARALYZE = "paralyze";
    private static String GLOVES = "gloves";
    private static String CAPE = "cape";
    private static String AXE = "axe";
    private static String BAG = "bag";

    public Virologist(int amino, int nukelo, double dodgeChance, Field field){
        if(dodgeChance > 1.0 || dodgeChance < 0.0){
            System.out.println("The dodgeChance must be between 0 and 1");
            return;
        }
        this.dodgeChance = dodgeChance;
        this.originalDodgeChance = dodgeChance;
        inv = new Inventory(amino, nukelo);
        effects = new Effects();
        this.field = field;
        field.Accept(this);
    }

    /*Visszaadja azt a field-et amin all a virologus
     * @param -
     * @return A field valtozoban levo fieldet adja vissza*/
    public Field getField(){
        return field;
    }

    /*Visszaadja a virologus effects listajat
     * @param -
     * @return A virologus effects listaja*/
    public Effects getEffects(){return effects;}

    public Inventory getInv() {return inv;}

    public double getOriginalDodgeChance(){return originalDodgeChance;}

    public void SetDodgeChance(double value){this.dodgeChance = value;}

    /*Fuggvenyek*/
    /*Elkeszít egy ugy agenst a megadott genetiaki kod alapjan*/
    public void Craft(GCode gcode){
        /*Paralyze check*/
        for(int i = 0; i < effects.GetEffects().size();i++){
            if(effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: I can't do anything i'm paralyzed!");
                return;
            }
        }

        System.out.println("Virologist: Checking if I have enough materials");
        Materials myMat = inv.getMaterials();           //A virologus alapanyagai
        ArrayList<Agent> myAgents = inv.getAgents();

        /*Check, hogy barmelyik nagyobb-e mint amennyi van*/
        if(gcode.getNukleo() > myMat.getNukleo() || gcode.getAmino() > myMat.GetAmino()){
            System.out.println("Virologist: I don't have enough materials for this agent!");
            return;
        }

        /*Check, hogy van-e hely az uj agensnek*/
        System.out.println("Virologist: Checking if I have enough space");
        if(myAgents.size()==5){
            System.out.println("Virologist: I don't have enough space to craft another agent!");
            return;
        }

        /*Check, hogy ismeri-e a Genetical Code-ot*/
        System.out.println("Virologist: Checking if I know the Code");
        if(!inv.GetGcodes().contains(gcode)) {
            System.out.println("Virologist: I don't know that genetical code yet.");
            return;
        }

        System.out.println("Virologist: Creating new agent");
        boolean sikeres = inv.CreateAgent(gcode);
        if(sikeres){
            inv.ChangeMaterial('a', gcode.getAmino()*(-1));
            inv.ChangeMaterial('n', gcode.getNukleo()*(-1));
            System.out.println("Virologist: I created a new agent.");
        }
        else{
            System.out.println("Virologist: I coudln't craft the agent!");
        }
    }

    /*Elmozgatja a virologust egy masik mezore*/
    public void Move(Field f){
        System.out.println("Virologist: I have called the 'Move' function.");
        /*CheckIfParalyzed*/
        for(int i = 0; i < effects.GetEffects().size(); i++){
            //Megnezi, hogy az adott elem 'string effect' valtozoja az effects-nek az effects listajaban 'paralyzed'-e
            //Tudom, bonyolult, de nem tudom, hogyan mashogyan lehetne, mert a 'contains' paranccsal nem tudtam dulore jutni
            if(effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: The player is paralyzed.");
                return;
            }
        }
        /*Tenyleges rakosgatas*/
        if(!field.getNeighbours().contains(f)){
            System.out.println("The chosen field is not a neighbour field with the field you are standing on.");
            return;
        }
        field.Remove(this);
        f.Accept(this);
        this.setField(f);
        System.out.println("Virologist: The player moved to another field.");
    }

    /*A virologus megtamad egy másik virologust(aki meg van adva az argumentumban)*/
    public void Attack(Virologist kezdemenyezo, Virologist v, String mivel){
        System.out.println("Virologist: I have called the 'Attack' function.");
        boolean owningTheAgent = false;
        /*CheckIfParalyzed*/
        for(int i = 0; i < effects.GetEffects().size();i++) {
            if (effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0) {
                System.out.println("The player is paralyzed.");
                return;
            }
        }
        Effect effect = null;
        /*Ide kene szerintem egy checkIfDodge, mert akkor tovabb se menjen, csak vonja le*/
        if(mivel.equals(PARALYZE)) {
            effect = new Paralyzed();
        }
        else if(mivel.equals("virusdance")){
            effect = new VirusDance();
        }
        else if(mivel.equals("protectvirus")){
            effect = new ProtectEffect();
        }
        else if(mivel.equals("forgetvirus")){
            effect = new ForgetEffect();
        }
        else if(mivel.equals("bearvirus")){
            effect = new BearEffect();
        }
        else {
            System.out.println("I couldn't find the virus");
            return;
        }
        //Tesztelem, hogy megvan-e neki az az agens
        for(int i = 0; i < inv.getAgents().size(); i++){
            Agent adottAgent = inv.getAgents().get(i);
            if(adottAgent.getEffect().effect.equals(mivel)){
                owningTheAgent = true;
            }
        }
        /*Megnezi, hogy mit valasztott a felhasznalo, kiter vagy nem
         * Azzal, hogy ezt tettem elorebb elertem, hogy hiaba van kesztyuje a masiknak
         * nem fogja zavarni, hiszen kikeruli*/
        if(owningTheAgent){
            v.Affected(kezdemenyezo, effect, true);
            System.out.println("Virologist: The agent has been used.");
            boolean removed = false;
            for(int i = 0; i < inv.getAgents().size(); i++){
                Agent agent = inv.getAgents().get(i);
                if(agent.getEffect().effect.equals(mivel) && !removed){
                    inv.RemoveAgent(agent);
                    removed = true;
                }
            }
            System.out.println("Virologist: The agent has been removed from inventory");
        }
        else{
            System.out.println("I don't have that agent!");
        }
    }

    /*A virologus aktivalja a kiterest, ezzel elhasznalva a koret*/
    public void Dodge(){}


    /*Meghivja az adott mezo collect() fuggvenyet*/
    public void Collect(){
        System.out.println("Virologist: I have called the 'Collect' function.");
        /*CheckIfParalyzed*/
        for(int i = 0; i < effects.GetEffects().size(); i++){
            //Megnezi, hogy az adott elem 'string effect' valtozoja az effects-nek az effects listajaban 'paralyzed'-e
            //Tudom, bonyolult, de nem tudom, hogyan mashogyan lehetne, mert a 'contains' paranccsal nem tudtam dulore jutni
            if(effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: The player is paralyzed.");
                return;
            }
        }
        field.Collect(this);
    }

    /*Aktivál a virologusra egy, az argumentumkent atadott hatast*/
    public void Affected(Virologist kezdemenyezo, Effect ef, boolean elso) {
        System.out.println("Affectig eljutott");
        /*Alap valtozok*/
        Random rand = new Random();
        double dodged = rand.nextDouble();
        boolean gloves = false;

        if (elso) {
            for (int i = 0; i < this.effects.GetEffects().size(); i++) {
                if (this.effects.GetEffects().get(i).effect.equals("GloveEffect")) {
                    gloves = true;
                }
            }
        }

        //Dodgeolas mechanika
        if (dodged < dodgeChance) {
            System.out.println("Virologist: The attacked virologist dodged the attack!");
            return;
        }

        /*Megnezi, hogy mit valasztott a felhasznalo, van kesztyuje vagy nincs*/
        if (gloves) {
            boolean wareable = true;
            //Hasznaljuk tenylegesen a glove-ot
            for (int i = 0; i < inv.GetEquipments().size(); i++) {
                if (inv.GetEquipments().get(i).name.equals(GLOVES)) {
                    Gloves glove = (Gloves)inv.GetEquipments().get(i);
                    wareable = glove.Decrease();
                }
            }
            //Ha elhasznalodott, akkor toroljuk
            if (!wareable) {
                for (int i = 0; i < inv.GetEquipments().size(); i++){
                    if (inv.GetEquipments().get(i).name.equals(GLOVES)){
                        Equipment glove = inv.GetEquipments().get(i);
                        Effect gloveEf = glove.getEffect();
                        //Item torlese
                        inv.RemoveItem(glove);
                        //Item effektjenek torlese
                        this.effects.RemoveEffect(this, gloveEf);
                        System.out.println("The glove has been removed");
                    }
                }
            }
            if (effects.GetEffects().contains(ef)) {
                System.out.println("Virologist: I have been affected by this effect already.");
                //Reseteli a timert rajta
                this.effects.RemoveEffect(this, ef);
                this.effects.ApplyEffect(this, ef);
                return;
            }

            effects.ApplyEffect(kezdemenyezo, ef);
            System.out.println("Virologist: I have been affected by my own paralyze agent!");
        }
        else{
            effects.ApplyEffect(this, ef);
            System.out.println("Virologist: I have attacked the other virologist");
        }
    }

    /*Felszereli az argumentumban megadott felszerelest*/
    public void Equip(Equipment eq){
        eq.setEquipped();
        inv.AddEquipment(eq);
        if(eq.getEffect() != null) {										//Teszteles miatt a NullPointerException problema lehetne, ezert ezt elkerulom
            if (inv.GetEquipments().size() != 3){
                effects.ApplyEffect(this, eq.getEffect());                        //meg kell tudnia, hogy beteheti-e az effectet
            }
        }
    }

    /*Megtanul egy argumentumként átadott genetiaki kodot*/
    public void LearnGCode(GCode gcode){
        inv.AddGCode(gcode);
    }

    /*Visszaadja azoknak az effekteknek a listajat, amelyek hatása alatt van eppen a virologus*/
    public Effects GetEffects(){
        return effects;
    }

    /*Visszaadja virologus inventorijat*/
    public Inventory GetInventory(){
        return inv;
    }

    /*A virologus eldob egy felszerelest*/
    public void Drop(String equipment){
        Equipment felsz;

        //Paralyze check
        for(int i = 0; i < effects.GetEffects().size();i++){
            if(effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: I can't do anything i'm paralyzed!");
                return;
            }
        }

        //Van-e equipment
        if(inv.GetEquipments().size() == 0){
            System.out.println("Virologist: I don't have an equipment to drop.");
            return;
        }

        if(equipment.length() == 0){
            System.out.println("I can't find that equipment");
            return;
        }

        //Milyen itemek vannak a jatekban
        String[] recogniseable = {CAPE, GLOVES, AXE, BAG};
        boolean recognised = false;

        //Letezik-e az equipment a jatekban amit beírt a felhasznalo
        for(int i = 0; i < recogniseable.length; i++){
            if(equipment.equals(recogniseable[i])){
                recognised = true;
            }
        }
        if(recognised){
            int item = -1;
            boolean found = false;
            for(int i = 0; i < inv.GetEquipments().size(); i++){
                Equipment adottEq = inv.GetEquipments().get(i);
                //Megkeressuk az itemet
                if(adottEq.getName().equals(equipment)){
                    //Megkeressuk az effektjet es kitoroljuk
                    Effect adottEff = adottEq.getEffect();
                    if(adottEff != null){
                        this.GetEffects().RemoveEffect(this, adottEff);
                        System.out.println("Virologist: The effect has been deleted!");
                    }
                    found = true;
                    item = i;
                }
            }
            if(found){
                //Ha megtalaltuk, akkor ki is toroljuk
                inv.GetEquipments().get(item).setEquipped();
                if(inv.GetEquipments().get(item).getName().equals(CAPE)){
                    this.dodgeChance = 0.2;
                }
                //Honnan vette ki a virologus
                Shelter hovaShelter = (Shelter)inv.GetEquipments().get(item).getHovaTartozas();
                inv.GetEquipments().remove(item);
                //Visszarakas a shelterbe
                if(hovaShelter != null) {
                    hovaShelter.IncreaeCounter();
                }
                System.out.println("Virologist: The equipment has been deleted!");
                System.out.println("Virologist: The equipment has been dropped");
            }
            else{
                System.out.println("Virologist: I couldn't find that item in my inventory");
                return;
            }
        }
        else{
            System.out.println("I can't recognise that equipment");
            return;
        }
    }

    /*Ellop egy felszerelest az argumentumban megadott virologustol*/
    public void StealEq(Virologist v, String eq){
        Equipment felsz;
        /*Paralyze check a lopast kezdo virologusnak*/
        for(int i = 0; i < effects.GetEffects().size();i++){
            if(effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0){
                System.out.println("Virologist0: I can't do anything i'm paralyzed!");
                return;
            }
        }
        /*Paralyze chack a masik virologusnak*/
        boolean paraB = false;
        for(int i = 0; i < v.effects.GetEffects().size();i++){
            if(v.effects.GetEffects().get(i).effect.equals(PARALYZE) && v.effects.GetEffects().get(i).timeLeft > 0){
                paraB = true;
            }
        }
        if(!paraB){
            /*Nincs paralyze a masik virologuson*/
            System.out.println("Virologist1: You can't steal from me because I'm not paralyzed!");
            return;
        }

        if(inv.GetEquipments().size() == 3){
            System.out.println("Virologist0: My inventory is full! I can't steal right now.");
            return;
        }

        if(v.GetInventory().GetEquipments().size() == 0){
            System.out.println("Virologist1: You can't steal from me right now, because my inventory is empty.");
            return;
        }
        boolean van = false;
        for(int i = 0; i < v.GetInventory().GetEquipments().size(); i++){
            if(eq.equals(v.GetInventory().GetEquipments().get(i).name)){
                van = true;
            }
        }
        if(!van){
            System.out.println("Virologist1: I don't have the equipment, you want to steal.");
            return;
        }

        for(int e = 0; e < this.GetInventory().GetEquipments().size(); e++ ){
            if(this.GetInventory().GetEquipments().get(e).name.equals(eq)){
                System.out.println("Virologist0: I already have this equipment, so I can't steal it.");
                return;
            }
        }

        for(int i = 0; i < v.GetInventory().GetEquipments().size(); i++){
            if(eq.equals(v.GetInventory().GetEquipments().get(i).name)){
                felsz = v.GetInventory().GetEquipments().get(i);
                v.RemoveItem(v.GetInventory().GetEquipments().get(i));
                this.Equip(felsz);
            }
        }
        System.out.println("Virologist0: I stole  " + eq + " from Virologist1");

    }

    /*Ellop egy adag alapanyagot az argumentumban megadott virologustol*/
    public void StealMat(Virologist v){
        //System.out.println("StealMat Begins");
        /*Paralyze check a lopast kezdo virologusnak*/
        for(int i = 0; i < effects.GetEffects().size();i++){
            if(effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0){
                System.out.println("Virologist0: I can't do anything i'm paralyzed!");
                return;
            }
        }
        /*Paralyze chack a masik virologusnak*/
        boolean paraB = false;
        for(int i = 0; i < v.effects.GetEffects().size();i++){
            if(v.effects.GetEffects().get(i).effect.equals(PARALYZE) && v.effects.GetEffects().get(i).timeLeft > 0){
                paraB = true;
            }
        }
        if(!paraB){
            /*Nincs paralyze a masik virologuson*/
            System.out.println("Virologist1: You can't steal from me because I'm not paralyzed!");
            return;
        }

        this.GetInventory().ChangeMaterial('a', v.GetInventory().getMaterials().GetAmino());
        this.GetInventory().ChangeMaterial('n', v.GetInventory().getMaterials().getNukleo());
        v.GetInventory().ChangeMaterial('n', (v.GetInventory().getMaterials().getNukleo()*(-1)));
        v.GetInventory().ChangeMaterial('a', (v.GetInventory().getMaterials().GetAmino()*(-1)));

        System.out.println("Virologist0 stole materials from Virologist1");
    }

    public void RemoveItem(Equipment eq){
        effects.RemoveEffect(this, eq.effect);
        //System.out.println("VirolgoistB: The effect has been deleted!");
        inv.GetEquipments().remove(eq);
        //System.out.println("VirolgoistB: The equipment has been deleted!");
        eq.setEquipped();
    }

    /*Beallitja a kapott ertekre a field valtozot*/
    public void setField(Field value){
        field = value;
    }

    /*AZ update interfacet megvalosito fuggveny*/
    public void Update(){
        for(int i = 0; i < effects.GetEffects().size(); i++){
            if(effects.GetEffects().get(i).getEffect().equals(PARALYZE) || effects.GetEffects().get(i).getEffect().contains("virus")){
                effects.GetEffects().get(i).Effect(this);
                effects.GetEffects().get(i).decrementTimeLeft();
                if(effects.GetEffects().get(i).getTimeLeft() == 0){
                    effects.GetEffects().get(i).RemoveEffect(this);
                    effects.GetEffects().remove(i);
                }
            }
        }
    }

    public void UseAxe(Virologist v) {
        for (int i = 0; i < effects.GetEffects().size(); i++) {
            if (effects.GetEffects().get(i).effect.equals(PARALYZE) && effects.GetEffects().get(i).timeLeft > 0) {
                System.out.println("The player is paralyzed!");
                return;
            }
        }
        Axe axe = null;
        for(int i = 0; i < inv.GetEquipments().size(); i++){
            if(inv.GetEquipments().get(i).name.equals(AXE)){
                axe = (Axe)inv.GetEquipments().get(i);
            }
        }
        if(axe == null){
            System.out.println("I don't have an axe!");
            return;
        }
        if(axe.broken){
            System.out.println("My axe is broken, I can't use it!");
            return;
        }
        boolean bear = false;
        for(int i = 0; i < v.effects.GetEffects().size();i++){
            if(v.effects.GetEffects().get(i).effect.equals("bearvirus")){
                bear = true;
                axe.AxeBroke();
                Effect ef = new Paralyzed();
                ef.duration = Integer.MAX_VALUE;
                v.effects.ApplyEffect(this, ef);
                System.out.println("I successfully killed the bear!");
                return;
            }
        }
        if(!bear){
            System.out.println("I am not effected with the bear virus, so you can't hit me with the axe!");
        }
    }
}
