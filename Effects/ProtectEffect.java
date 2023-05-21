package Effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class ProtectEffect extends Effect implements Serializable {
    public ProtectEffect(){
        effect = "protectvirus";
        duration = 5;
        timeLeft = duration;
    }

    @Override
    public void Effect(Virologist v) {
        v.SetDodgeChance(1);
    }

    public void RemoveEffect(Virologist v){
        v.SetDodgeChance(v.getOriginalDodgeChance());
    }
}
