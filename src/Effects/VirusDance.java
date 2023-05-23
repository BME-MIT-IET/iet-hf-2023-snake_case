package Effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;
import java.util.Random;

public class VirusDance extends Effect implements Serializable {

    public VirusDance(){
        duration = 3;
        effect = "virusdance";
        timeLeft = duration;
    }

    @Override
    public void Effect(Virologist v){
        Random rand  = new Random();
        /*Merre mozog*/
        int randommove = rand.nextInt(v.getField().getNeighbours().size());
        /*Elmozdul a random szomszedos mezore*/
        v.Move(v.getField().getNeighbours().get(randommove));
        /*Ujra*/
        randommove = rand.nextInt(v.getField().getNeighbours().size());
        /*Elmozdul a random szomszedos mezore*/
        v.Move(v.getField().getNeighbours().get(randommove));
    }
    public void RemoveEffect(Virologist v){

    }
}
