package effects;

import src.Effect;
import src.StringConstants;
import src.Virologist;

import java.io.Serializable;

public class Paralyzed extends Effect implements Serializable {

    public Paralyzed(){
        duration = 3;
        myEffect = StringConstants.PARALYZE;
        timeLeft = duration;
    }

    @Override
    public void effect(Virologist v){
        //ha bármit beleírok nem jó a kód
    }
    public void removeEffect(Virologist v){
        //Azért üres, mert az Effect absztrakt osztályból származik, ezért meg kell valósítania ezt a függvényt, azonban ezem meg vannak hívva máshol
        //Szándékosan nem csinálnak semmit!
    }
}
