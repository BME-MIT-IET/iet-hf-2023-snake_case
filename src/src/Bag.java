package src;

import effects.BagEffect;

import java.io.Serializable;

public class Bag extends Equipment implements Serializable {
    public Bag() {
        effect = new BagEffect();
        name = StringConstants.BAG;
        System.out.println("Bag: I'm Created.");
    }
}
