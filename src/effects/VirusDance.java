package effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;
import java.util.Random;

public class VirusDance extends Effect implements Serializable {

    public VirusDance(){
        duration = 3;
        myEffect = "virusdance";
        timeLeft = duration;
    }

    @Override
    public void effect(Virologist v){
        Random rand  = new Random();
        /*Merre mozog*/
        int randommove = rand.nextInt(v.getField().getNeighbours().size());
        /*Elmozdul a random szomszedos mezore*/
        v.move(v.getField().getNeighbours().get(randommove));
        /*Ujra*/
        randommove = rand.nextInt(v.getField().getNeighbours().size());
        /*Elmozdul a random szomszedos mezore*/
        v.move(v.getField().getNeighbours().get(randommove));
    }
    public void removeEffect(Virologist v){

    }
}