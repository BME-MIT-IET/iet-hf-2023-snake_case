package Effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class Paralyzed extends Effect implements Serializable {

    public Paralyzed(){
        duration = 3;
        effect = "paralyze";
        timeLeft = duration;
    }

    @Override
    public void Effect(Virologist v){}
    public void RemoveEffect(Virologist v){}
}
