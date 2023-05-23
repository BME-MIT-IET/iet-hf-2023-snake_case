package src;

import Effects.GloveEffect;

import java.io.Serializable;

public class Gloves extends Equipment implements Serializable {
    int usesLeft;
    public Gloves() {
        effect = new GloveEffect();
        name = "gloves";
        usesLeft = 3;
        System.out.println("Gloves: I'm Created.");
    }

    public boolean Decrease(){
        usesLeft--;
        if(usesLeft == 0){
            return false;
        }
        return true;
    }
}
