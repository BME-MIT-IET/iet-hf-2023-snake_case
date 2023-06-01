package effects;

import src.Effect;
import src.StringConstants;
import src.Virologist;

import java.io.Serializable;

public class ForgetEffect extends Effect implements Serializable {

    public ForgetEffect() {
        duration = 1;
        timeLeft = duration;
        myEffect = StringConstants.FORGETVIRUS;
        System.out.println("ForgetEffect: I'm Created");
    }

    public void effect(Virologist v) {
        v.getInv().getGcodes().clear();
        System.out.println("ForgetEffect: My Effect is activated");
    }

    public void removeEffect(Virologist v){
        throw new UnsupportedOperationException();
    }

}
