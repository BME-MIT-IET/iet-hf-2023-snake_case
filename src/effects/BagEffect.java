package effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class BagEffect extends Effect implements Serializable {
    public BagEffect() {
        duration = 0;
        timeLeft = 0;
        myEffect = "BagEffect";
        System.out.println("BagEffect: I'm Created");
    }

    public void effect(Virologist v) {
        /*BagEffect-et majd itt valositsuk meg, ahogyan akarjuk, nekem csak a lete kellett a teszteleshez.*/
        v.getInv().getMaterials().setCapacity(8);
        System.out.println("BagEffect: My Effect is activated");
    }
    public void removeEffect(Virologist v){
        v.getInv().getMaterials().setCapacity(5);
    }
}
