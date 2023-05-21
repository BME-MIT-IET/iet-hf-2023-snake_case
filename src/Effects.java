package src;

import java.io.Serializable;
import java.util.ArrayList;

public class Effects implements Update, Serializable {
    /*Valtozok*/
    private ArrayList<Effect> effects = new ArrayList<>();

    /*Fuggvenyek*/
    /*Hozzaad egy hatast a listahoz*/
    public void ApplyEffect(Virologist v, Effect ef){
        if(!effects.contains(ef)){
            effects.add(ef);
            ef.Effect(v);
            System.out.println("The effect has been added.");
            return;
        }
        /*Nincs setter/getter, ugyhogy lehet kéne a frissitesek miatt
        (hogy ne kelljen mindig hozzaadni, ha mar egyszer ott van, csak az idot resetelnei)*/
    }

    /*Elvesz egy hatast a listabol*/
    public void RemoveEffect(Virologist v, Effect ef){
        if(effects.size() != 0){
            if(effects.contains(ef)){
                ef.RemoveEffect(v);
                effects.remove(ef);
                return;
            }
            System.out.println("I am not affected by that effect.");
            return;
        }
    }

    /*Meg nezi hogy egy effect rajta van-e mar a virologuson*/
    public boolean SearchForEffect(String effect){
        for(Effect ef : effects){
            if(ef.getEffect().equals(effect)){
                return true;
            }
        }
        return false;
    }

    /*Visszaadja az effektek listáját*/
    public ArrayList<Effect> GetEffects(){
        return effects;
    }

    /*Fontos hogy ezt mindig a jatekos korenek a legvegen hivjuk meg mert ha nem leszedheti az effectet a kor kozben*/
    public void Update(){

    }
}
