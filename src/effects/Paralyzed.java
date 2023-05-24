package effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class Paralyzed extends Effect implements Serializable {

    public Paralyzed(){
        duration = 3;
        myEffect = "paralyze";
        timeLeft = duration;
    }

    @Override
    public void effect(Virologist v){}
    public void removeEffect(Virologist v){}
}
