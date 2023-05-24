package effects;
import src.Effect;
import src.Virologist;

import java.io.Serializable;
import java.util.Random;

public class BearEffect extends Effect implements Serializable {
    public BearEffect(){
        effect = "bearvirus";
        duration = 0;
        timeLeft = 0;
    }

    @Override
    public void Effect(Virologist v) {
        Random rand  = new Random();
        /*Merre mozog a medve, először mindig lép*/
        int randommove = rand.nextInt(v.getField().getNeighbours().size());
        /*Elmozdul a random szomszedos mezore*/
        v.Move(v.getField().getNeighbours().get(randommove));


        /*Ha vannak azon a mezon akkor megtamad mindenkit ott*/
        if(v.getField().getVirologists().size() > 1){
            for(int i = 0; i < v.getField().getVirologists().size(); i++){
                /*Akin nincs beareffect azt megtamadja egy bearvirus-al*/
                if(!v.getField().getVirologists().get(i).GetEffects().SearchForEffect("bearvirus")){
                    /*Ezzel fog támadni, ingyen craftolja, mert végtelen van neki*/
                    for(int e = 0; e < v.GetInventory().GetGcodes().size(); e++){
                        if(v.GetInventory().GetGcodes().get(i).getEffect().equals("bearvirus")){
                            v.Craft(v.GetInventory().GetGcodes().get(i));
                        }
                    }
                    v.Attack(v, v.getField().getVirologists().get(i), "bearvirus");
                }
            }
        }
        else{
            /*Ha nincs senki a mezon akkor lep megegyet*/
            randommove = rand.nextInt(v.getField().getNeighbours().size());
            /*Elmozdul a random szomszedos mezore*/

            if(v.getField().getClass().getSimpleName().equals("Warehouse")){
                    v.Collect();
                    System.out.println("I have destroyed the materials in the warehouse!");}
            else{v.Move(v.getField().getNeighbours().get(randommove));}

        }

    }

    public void RemoveEffect(Virologist v){}
}
