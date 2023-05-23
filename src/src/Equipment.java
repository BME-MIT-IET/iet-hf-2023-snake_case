package src;

import java.io.Serializable;

public class Equipment implements Serializable {
    /*Valtozok*/
    protected Effect effect = null;
    protected String name;              //Atlathatosag miatt szukseges
    private boolean equipped = false;
    private Field hovaTartozas = null;


    /*Fuggvenyek*/
    public Effect getEffect(){
        return effect;
    }

    /*Ha fel van veve akkor true, ha eldobjuk akkor false*/
    public void setEquipped(){
        if(equipped){equipped = false;}
        else{ equipped = true;}
    }

    public boolean getEquipped(){return equipped;}

    public String getName() {return name;}

    public Field getHovaTartozas(){
        return hovaTartozas;
    }

    public void setHovaTartozas(Field hova){
        this.hovaTartozas = hova;
    }
}
