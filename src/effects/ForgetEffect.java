package effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class ForgetEffect extends Effect implements Serializable {

    public ForgetEffect() {
        duration = 1;
        timeLeft = duration;
        effect = "forgetvirus";
        System.out.println("ForgetEffect: I'm Created");
    }

    public void Effect(Virologist v) {
        v.getInv().GetGcodes().clear();
        System.out.println("ForgetEffect: My Effect is activated");
    }

    public void RemoveEffect(Virologist v){

    }

}
