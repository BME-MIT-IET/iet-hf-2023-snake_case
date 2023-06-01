package effects;

import src.Effect;
import src.StringConstants;
import src.Virologist;

import java.io.Serializable;
import java.util.Random;

public class VirusDance extends Effect implements Serializable {
    private final Random rand  = new Random();

    public VirusDance(){
        duration = 3;
        myEffect = StringConstants.DANCEVIRUS;
        timeLeft = duration;
    }

    @Override
    public void effect(Virologist v){
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
        throw new UnsupportedOperationException();
    }
}
