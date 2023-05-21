package src;

import Effects.BagEffect;

import java.io.Serializable;

public class Bag extends Equipment implements Serializable {
    public Bag() {
        effect = new BagEffect();
        name = "bag";
        System.out.println("Bag: I'm Created.");
    }
}
