package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import effects.*;

public class Virologist implements Update, Serializable {
    /*Valtozok*/
    private double dodgeChance;
    private double originalDodgeChance;
    private Inventory inv;
    private Effects effects;
    private Field field;

    private final Random rand = new Random();
    private static final String PARALYZE = "paralyze";
    private static final String GLOVES = "gloves";
    private static final String CAPE = "cape";
    private static final String AXE = "axe";
    private static final String BAG = "bag";

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
        field.accept(this);
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

    public void setDodgeChance(double value){this.dodgeChance = value;}

    /*Fuggvenyek*/
    /*Elkeszít egy ugy agenst a megadott genetiaki kod alapjan*/
    public void craft(GCode gcode){
        /*Paralyze check*/
        for(int i = 0; i < effects.getEffects().size(); i++){
            if(effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: I can't do anything i'm paralyzed!");
                return;
            }
        }

        System.out.println("Virologist: Checking if I have enough materials");
        Materials myMat = inv.getMaterials();           //A virologus alapanyagai
        ArrayList<Agent> myAgents = inv.getAgents();

        /*Check, hogy barmelyik nagyobb-e mint amennyi van*/
        if(gcode.getNukleo() > myMat.getNukleo() || gcode.getAmino() > myMat.getAmino()){
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
        if(!inv.getGcodes().contains(gcode)) {
            System.out.println("Virologist: I don't know that genetical code yet.");
            return;
        }

        System.out.println("Virologist: Creating new agent");
        boolean sikeres = inv.createAgent(gcode);
        if(sikeres){
            inv.changeMaterial('a', gcode.getAmino()*(-1));
            inv.changeMaterial('n', gcode.getNukleo()*(-1));
            System.out.println("Virologist: I created a new agent.");
        }
        else{
            System.out.println("Virologist: I coudln't craft the agent!");
        }
    }

    /*Elmozgatja a virologust egy masik mezore*/
    public void move(Field f){
        System.out.println("Virologist: I have called the 'Move' function.");
        /*CheckIfParalyzed*/
        for(int i = 0; i < effects.getEffects().size(); i++){
            //Megnezi, hogy az adott elem 'string effect' valtozoja az effects-nek az effects listajaban 'paralyzed'-e
            //Tudom, bonyolult, de nem tudom, hogyan mashogyan lehetne, mert a 'contains' paranccsal nem tudtam dulore jutni
            if(effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: The player is paralyzed.");
                return;
            }
        }
        /*Tenyleges rakosgatas*/
        if(!field.getNeighbours().contains(f)){
            System.out.println("The chosen field is not a neighbour field with the field you are standing on.");
            return;
        }
        field.remove(this);
        f.accept(this);
        this.setField(f);
        System.out.println("Virologist: The player moved to another field.");
    }

    /*A virologus megtamad egy másik virologust(aki meg van adva az argumentumban)*/
    public void attack(Virologist kezdemenyezo, Virologist v, String mivel){
        System.out.println("Virologist: I have called the 'Attack' function.");
        boolean owningTheAgent = false;
        /*CheckIfParalyzed*/
        for(int i = 0; i < effects.getEffects().size(); i++) {
            if (effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0) {
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
            if(adottAgent.getEffect().myEffect.equals(mivel)){
                owningTheAgent = true;
            }
        }
        /*Megnezi, hogy mit valasztott a felhasznalo, kiter vagy nem
         * Azzal, hogy ezt tettem elorebb elertem, hogy hiaba van kesztyuje a masiknak
         * nem fogja zavarni, hiszen kikeruli*/
        if(owningTheAgent){
            v.affected(kezdemenyezo, effect, true);
            System.out.println("Virologist: The agent has been used.");
            boolean removed = false;
            for(int i = 0; i < inv.getAgents().size(); i++){
                Agent agent = inv.getAgents().get(i);
                if(agent.getEffect().myEffect.equals(mivel) && !removed){
                    inv.removeAgent(agent);
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
    public void dodge(){}


    /*Meghivja az adott mezo collect() fuggvenyet*/
    public void collect(){
        System.out.println("Virologist: I have called the 'Collect' function.");
        /*CheckIfParalyzed*/
        for(int i = 0; i < effects.getEffects().size(); i++){
            //Megnezi, hogy az adott elem 'string effect' valtozoja az effects-nek az effects listajaban 'paralyzed'-e
            //Tudom, bonyolult, de nem tudom, hogyan mashogyan lehetne, mert a 'contains' paranccsal nem tudtam dulore jutni
            if(effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: The player is paralyzed.");
                return;
            }
        }
        field.collect(this);
    }

    /*Aktivál a virologusra egy, az argumentumkent atadott hatast*/
    public void affected(Virologist kezdemenyezo, Effect ef, boolean elso) {
        System.out.println("Affectig eljutott");
        /*Alap valtozok*/
        double dodged = rand.nextDouble();
        boolean gloves = false;

        if (elso) {
            for (int i = 0; i < this.effects.getEffects().size(); i++) {
                if (this.effects.getEffects().get(i).myEffect.equals("GloveEffect")) {
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
                    wareable = glove.decrease();
                }
            }
            //Ha elhasznalodott, akkor toroljuk
            if (!wareable) {
                for (int i = 0; i < inv.GetEquipments().size(); i++){
                    if (inv.GetEquipments().get(i).name.equals(GLOVES)){
                        Equipment glove = inv.GetEquipments().get(i);
                        Effect gloveEf = glove.getEffect();
                        //Item torlese
                        inv.removeItem(glove);
                        //Item effektjenek torlese
                        this.effects.removeEffect(this, gloveEf);
                        System.out.println("The glove has been removed");
                    }
                }
            }
            if (effects.getEffects().contains(ef)) {
                System.out.println("Virologist: I have been affected by this effect already.");
                //Reseteli a timert rajta
                this.effects.removeEffect(this, ef);
                this.effects.applyEffect(this, ef);
                return;
            }

            effects.applyEffect(kezdemenyezo, ef);
            System.out.println("Virologist: I have been affected by my own paralyze agent!");
        }
        else{
            effects.applyEffect(this, ef);
            System.out.println("Virologist: I have attacked the other virologist");
        }
    }

    /*Felszereli az argumentumban megadott felszerelest*/
    public void equip(Equipment eq){
        eq.setEquipped();
        inv.addEquipment(eq);
        if(eq.getEffect() != null) {                                        //Teszteles miatt a NullPointerException problema lehetne, ezert ezt elkerulom
            if (inv.GetEquipments().size() != 3){
                effects.applyEffect(this, eq.getEffect());                        //meg kell tudnia, hogy beteheti-e az effectet
            }
        }
    }

    /*Megtanul egy argumentumként átadott genetiaki kodot*/
    public void learnGCode(GCode gcode){
        inv.addGCode(gcode);
    }

    /*A virologus eldob egy felszerelest*/
    public void drop(String equipment){
        //Paralyze check
        for(int i = 0; i < effects.getEffects().size(); i++){
            if(effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0){
                System.out.println("Virologist: I can't do anything i'm paralyzed!");
                return;
            }
        }

        //Van-e equipment
        if(inv.GetEquipments().isEmpty()){
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
                        this.getEffects().removeEffect(this, adottEff);
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
                    hovaShelter.increaseCounter();
                }
                System.out.println("Virologist: The equipment has been deleted!");
                System.out.println("Virologist: The equipment has been dropped");
            }
            else{
                System.out.println("Virologist: I couldn't find that item in my inventory");
            }
        }
        else{
            System.out.println("I can't recognise that equipment");
        }
    }

    /*Ellop egy felszerelest az argumentumban megadott virologustol*/
    public void stealEq(Virologist v, String eq){
        Equipment felsz;
        /*Paralyze check a lopast kezdo virologusnak*/
        for(int i = 0; i < effects.getEffects().size(); i++){
            if(effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0){
                System.out.println("Virologist0: I can't do anything i'm paralyzed!");
                return;
            }
        }
        /*Paralyze check a masik virologusnak*/
        if(isTheOtherVirologistParalyzed(v)){
            return;
        }

        //Check if the Inventory is full or empty
        if(invIsFullOrInvIsEmpty(v)){
            return;
        }

        //Check if the item is in the inventory
        if(isItInMyInv(v, eq)){
            return;
        }

        for(int e = 0; e < this.getInv().GetEquipments().size(); e++ ){
            if(this.getInv().GetEquipments().get(e).name.equals(eq)){
                System.out.println("Virologist0: I already have this equipment, so I can't steal it.");
                return;
            }
        }

        for(int i = 0; i < v.getInv().GetEquipments().size(); i++){
            if(eq.equals(v.getInv().GetEquipments().get(i).name)){
                felsz = v.getInv().GetEquipments().get(i);
                v.removeItem(v.getInv().GetEquipments().get(i));
                this.equip(felsz);
            }
        }
        System.out.println("Virologist0: I stole  " + eq + " from Virologist1");
    }

    private boolean invIsFullOrInvIsEmpty(Virologist v){
        if(inv.GetEquipments().size() == 3){
            System.out.println("Virologist0: My inventory is full! I can't steal right now.");
            return true;
        }

        if(v.getInv().GetEquipments().isEmpty()){
            System.out.println("Virologist1: You can't steal from me right now, because my inventory is empty.");
            return true;
        }
        return false;
    }

    private boolean isItInMyInv(Virologist v, String eq){
        for(int i = 0; i < v.getInv().GetEquipments().size(); i++){
            if(eq.equals(v.getInv().GetEquipments().get(i).name)){
                System.out.println("Virologist1: I don't have the equipment, you want to steal.");
                return true;
            }
        }
        return false;
    }

    private boolean isTheOtherVirologistParalyzed(Virologist v){
        for(int i = 0; i < v.effects.getEffects().size(); i++){
            if(v.effects.getEffects().get(i).myEffect.equals(PARALYZE) && v.effects.getEffects().get(i).timeLeft > 0){
                System.out.println("Virologist1: You can't steal from me because I'm not paralyzed!");
                return true;
            }
        }
        return false;
    }

    /*Ellop egy adag alapanyagot az argumentumban megadott virologustol*/
    public void stealMaterial(Virologist v){
        /*Paralyze check a lopast kezdo virologusnak*/
        for(int i = 0; i < effects.getEffects().size(); i++){
            if(effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0){
                System.out.println("Virologist0: I can't do anything i'm paralyzed!");
                return;
            }
        }
        /*Paralyze chack a masik virologusnak*/
        boolean paraB = false;
        for(int i = 0; i < v.effects.getEffects().size(); i++){
            if(v.effects.getEffects().get(i).myEffect.equals(PARALYZE) && v.effects.getEffects().get(i).timeLeft > 0){
                paraB = true;
            }
        }
        if(!paraB){
            /*Nincs paralyze a masik virologuson*/
            System.out.println("Virologist1: You can't steal from me because I'm not paralyzed!");
            return;
        }

        this.getInv().changeMaterial('a', v.getInv().getMaterials().getAmino());
        this.getInv().changeMaterial('n', v.getInv().getMaterials().getNukleo());
        v.getInv().changeMaterial('n', (v.getInv().getMaterials().getNukleo()*(-1)));
        v.getInv().changeMaterial('a', (v.getInv().getMaterials().getAmino()*(-1)));

        System.out.println("Virologist0 stole materials from Virologist1");
    }

    public void removeItem(Equipment eq){
        effects.removeEffect(this, eq.effect);
        inv.GetEquipments().remove(eq);
        eq.setEquipped();
    }

    /*Beallitja a kapott ertekre a field valtozot*/
    public void setField(Field value){
        field = value;
    }

    /*AZ update interfacet megvalosito fuggveny*/
    public void update(){
        for(int i = 0; i < effects.getEffects().size(); i++){
            if(effects.getEffects().get(i).getMyEffect().equals(PARALYZE) || effects.getEffects().get(i).getMyEffect().contains("virus")){
                effects.getEffects().get(i).effect(this);
                effects.getEffects().get(i).decrementTimeLeft();
                if(effects.getEffects().get(i).getTimeLeft() == 0){
                    effects.getEffects().get(i).removeEffect(this);
                    effects.getEffects().remove(i);
                }
            }
        }
    }

    public void useAxe(Virologist v) {
        for (int i = 0; i < effects.getEffects().size(); i++) {
            if (effects.getEffects().get(i).myEffect.equals(PARALYZE) && effects.getEffects().get(i).timeLeft > 0) {
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
        for(int i = 0; i < v.effects.getEffects().size(); i++){
            if(v.effects.getEffects().get(i).myEffect.equals("bearvirus")){
                bear = true;
                axe.axeBroke();
                Effect ef = new Paralyzed();
                ef.duration = Integer.MAX_VALUE;
                v.effects.applyEffect(this, ef);
                System.out.println("I successfully killed the bear!");
                return;
            }
        }
        if(!bear){
            System.out.println("I am not effected with the bear virus, so you can't hit me with the axe!");
        }
    }
}
