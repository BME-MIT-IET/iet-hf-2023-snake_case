package src;

import effects.BearEffect;

import java.io.Serializable;

public class Laboratory extends Field implements Serializable {
    //ez a genetikai kod talalhato a mezon.
    private GCode gcode;
    private double bearChance;

    public Laboratory(GCode gc, double bearChance){
        gcode = gc;
        this.bearChance = bearChance;
        System.out.println("A Laboratory has been created.");
    }

    //A virologus felveszi a laborban talalhato genetikai kodot.
    public void Collect(Virologist v){
    	System.out.println("The virologist tries to learn the GCode");
    	v.LearnGCode(gcode);
        /*Ha nincs köpenye*/
        if(!v.getEffects().SearchForEffect("CapeEffect")){
            /*Ha nem sikerül kikerülnie*/
            if(Math.random() < bearChance){
                /*Megkapja a medveeffectet*/
                v.getEffects().ApplyEffect(v, new BearEffect());
                /*Elvesszuk tole az osszes agenset, hogy legyen hely a bearvirus agenseknek*/
                for(int i = 0; i < v.GetInventory().getAgents().size(); i++){
                    v.GetInventory().RemoveAgent(v.GetInventory().getAgents().get(i));
                }
                v.LearnGCode(new GCode(0,0,"bearvirus"));
            }
        }
    }
    
    //visszaadja a laborban talalhato genetikai kodot
    public GCode getGCode() {
    	return gcode;
    }
}
