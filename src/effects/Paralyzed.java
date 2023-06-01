package effects;

import src.Effect;
import src.StringConstants;
import src.Virologist;

import java.io.Serializable;

public class Paralyzed extends Effect implements Serializable {

    public Paralyzed(){
        duration = 3;
        myEffect = StringConstants.PARALYZE;
        timeLeft = duration;
    }

    @Override
    public void effect(Virologist v){
        throw new UnsupportedOperationException();
    }
    public void removeEffect(Virologist v){
        throw new UnsupportedOperationException();
    }
}
