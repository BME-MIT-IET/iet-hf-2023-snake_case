package src;

import java.io.Serializable;

public class GCode implements Serializable {
    /*Valtozok*/
    private final int amino;
    private final int nukleo;
    private final String effect;

    public GCode(int amino, int nukelo, String effect){
        this.amino = amino;
        this.nukleo = nukelo;
        this.effect = effect;
    }

    /*Fuggvenyek*/
    /*Visszaadja az amino erteket*/
    public int getAmino(){
        return amino;
    }

    /*Visszaadja a nukelo erteket*/
    public int getNukleo(){
        return nukleo;
    }

    public String getEffect(){
        return effect;
    }
}
