package src;

import java.io.Serializable;

public class Axe extends Equipment implements Serializable {
    protected boolean broken;
        public Axe() {
            name = "axe";
            broken = false;
            System.out.println("Axe: I'm Created.");
        }

        public void AxeBroke(){
            if(!broken) {
                broken = true;
                return;
            }
            else{
                return;
            }
        }
    }

