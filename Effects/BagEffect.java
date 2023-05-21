package Effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class BagEffect extends Effect implements Serializable {
    public BagEffect() {
        duration = 0;
        timeLeft = 0;
        effect = "BagEffect";
        System.out.println("BagEffect: I'm Created");
    }


    public void Effect(Virologist v) {
        /*BagEffect-et majd itt valositsuk meg, ahogyan akarjuk, nekem csak a lete kellett a teszteleshez.*/
        v.getInv().getMaterials().SetCapacity(8);
        System.out.println("BagEffect: My Effect is activated");
    }
    public void RemoveEffect(Virologist v){
        v.getInv().getMaterials().SetCapacity(5);
    }
}
