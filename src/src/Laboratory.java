package src;

import effects.BearEffect;

import java.io.Serializable;

public class Laboratory extends Field implements Serializable {
    //ez a genetikai kod talalhato a mezon.
    private final GCode gcode;
    private final double bearChance;

    public Laboratory(GCode gc, double bearChance){
        gcode = gc;
        this.bearChance = bearChance;
        System.out.println("A Laboratory has been created.");
    }

    //A virologus felveszi a laborban talalhato genetikai kodot.
    @Override
    public void collect(Virologist v){
    	System.out.println("The virologist tries to learn the GCode");
    	v.learnGCode(gcode);
        /*Ha nincs köpenye*/
        if(!v.getEffects().searchForEffect("CapeEffect")){
            /*Ha nem sikerül kikerülnie*/
            if(Math.random() < bearChance){
                /*Megkapja a medveeffectet*/
                v.getEffects().applyEffect(v, new BearEffect());
                /*Elvesszuk tole az osszes agenset, hogy legyen hely a bearvirus agenseknek*/
                for(int i = 0; i < v.getInv().getAgents().size(); i++){
                    v.getInv().removeAgent(v.getInv().getAgents().get(i));
                }
                v.learnGCode(new GCode(0,0,"bearvirus"));
            }
        }
    }
    
    //visszaadja a laborban talalhato genetikai kodot
    public GCode getGCode() {
    	return gcode;
    }
}
