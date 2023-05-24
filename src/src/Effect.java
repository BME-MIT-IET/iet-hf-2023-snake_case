package src;

import java.io.Serializable;

public abstract class Effect implements Serializable {
    /*Valtozok*/
    protected int duration;
    protected String myEffect;
    protected int timeLeft;

    /*Fuggvenyek*/
    public abstract void effect(Virologist v);
    public abstract void removeEffect(Virologist v);

    public String getMyEffect(){return myEffect;}
    public int getTimeLeft(){return timeLeft;}
    public void decrementTimeLeft(){timeLeft--;}
}
