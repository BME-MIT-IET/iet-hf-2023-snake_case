package src;

import java.io.Serializable;

public abstract class Effect implements Serializable {
    /*Valtozok*/
    protected int duration;
    protected String effect;
    protected int timeLeft;

    /*Fuggvenyek*/
    public abstract void Effect(Virologist v);
    public abstract void RemoveEffect(Virologist v);

    public String getEffect(){return effect;}
    public int getTimeLeft(){return timeLeft;}
    public void decrementTimeLeft(){timeLeft--;}
}
