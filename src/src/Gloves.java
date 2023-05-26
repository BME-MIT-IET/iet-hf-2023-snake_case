package src;

import effects.GloveEffect;

import java.io.Serializable;

public class Gloves extends Equipment implements Serializable {
    int usesLeft;
    public Gloves() {
        effect = new GloveEffect();
        name = StringConstants.GLOVES;
        usesLeft = 3;
        System.out.println("Gloves: I'm Created.");
    }

    public boolean decrease(){
        usesLeft--;
        if(usesLeft == 0){
            return false;
        }
        return true;
    }
}
