package effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class GloveEffect extends Effect implements Serializable {
    public GloveEffect() {
        duration = 0;
        timeLeft = 0;
        effect = "GloveEffect";
        System.out.println("GloveEffect: I'm Created");
    }


    public void Effect(Virologist v) {
        /*CapeEffect-et majd itt valositsuk meg, ahogyan akarjuk, nekem csak a lete kellett a teszteleshez.*/
        System.out.println("GloveEffect: My Effect is activated");
    }
    public void RemoveEffect(Virologist v){

    }
}
