package effects;
import src.Effect;
import src.StringConstants;
import src.Virologist;

import java.io.Serializable;
import java.util.Random;

public class BearEffect extends Effect implements Serializable {
    public BearEffect(){
        myEffect = StringConstants.BEARVIRUS;
        duration = 0;
        timeLeft = 0;
    }
    Random rand  = new Random();
    @Override
    public void effect(Virologist v) {
        /*Merre mozog a medve, először mindig lép*/
        int randommove = rand.nextInt(v.getField().getNeighbours().size());
        /*Elmozdul a random szomszedos mezore*/
        v.move(v.getField().getNeighbours().get(randommove));


        /*Ha vannak azon a mezon akkor megtamad mindenkit ott*/
        if(v.getField().getVirologists().size() > 1){
            bearAttack(v);
        }
        else{
            /*Ha nincs senki a mezon akkor lep megegyet*/
            randommove = rand.nextInt(v.getField().getNeighbours().size());
            /*Elmozdul a random szomszedos mezore*/

            if(v.getField().getClass().getSimpleName().equals("Warehouse")){
                    v.collect();
                    System.out.println("I have destroyed the materials in the warehouse!");}
            else{v.move(v.getField().getNeighbours().get(randommove));}

        }

    }

    public void removeEffect(Virologist v){
        throw new UnsupportedOperationException();
    }

    public void bearAttack(Virologist v){
        for(int i = 0; i < v.getField().getVirologists().size(); i++){
            /*Akin nincs beareffect azt megtamadja egy bearvirus-al*/
            if(!v.getField().getVirologists().get(i).getEffects().searchForEffect(StringConstants.BEARVIRUS)){
                /*Ezzel fog támadni, ingyen craftolja, mert végtelen van neki*/
                for(int e = 0; e < v.getInv().getGcodes().size(); e++){
                    if(v.getInv().getGcodes().get(i).getEffect().equals(StringConstants.BEARVIRUS)){
                        v.craft(v.getInv().getGcodes().get(i));
                    }
                }
                v.attack(v, v.getField().getVirologists().get(i), StringConstants.BEARVIRUS);
            }
        }
    }
}
