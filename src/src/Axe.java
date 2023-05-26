package src;

import java.io.Serializable;

public class Axe extends Equipment implements Serializable {
    protected boolean broken;
    public Axe() {
        name = StringConstants.AXE;
        broken = false;
        System.out.println("Axe: I'm Created.");
    }

    public void axeBroke(){
        if(!broken) {
            broken = true;
        }
    }
}

