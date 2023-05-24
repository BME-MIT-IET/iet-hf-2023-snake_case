package src;

import java.io.Serializable;

public class Agent implements Serializable {
    /*Valtozok*/
    private final Effect effect;

    Agent(Effect value){
        effect = value;
    }
    /*Fuggvenyek*/
    public Effect getEffect(){
        return effect;
    }
}
