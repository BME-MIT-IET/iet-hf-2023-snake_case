package effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class ProtectEffect extends Effect implements Serializable {
    public ProtectEffect(){
        myEffect = "protectvirus";
        duration = 5;
        timeLeft = duration;
    }

    @Override
    public void effect(Virologist v) {
        v.setDodgeChance(1);
    }

    public void removeEffect(Virologist v){
        v.setDodgeChance(v.getOriginalDodgeChance());
    }
}
