package src;

import java.io.Serializable;
import java.util.ArrayList;

public class Effects implements Update, Serializable {
    /*Valtozok*/
    private final ArrayList<Effect> myEffects = new ArrayList<>();

    /*Fuggvenyek*/
    /*Hozzaad egy hatast a listahoz*/
    public void applyEffect(Virologist v, Effect ef){
        if(!myEffects.contains(ef)){
            myEffects.add(ef);
            ef.effect(v);
            System.out.println("The effect has been added.");
        }
        /*Nincs setter/getter, ugyhogy lehet kéne a frissitesek miatt
        (hogy ne kelljen mindig hozzaadni, ha mar egyszer ott van, csak az idot resetelnei)*/
    }

    /*Elvesz egy hatast a listabol*/
    public void removeEffect(Virologist v, Effect ef){
        if(myEffects.size() != 0){
            if(myEffects.contains(ef)){
                ef.removeEffect(v);
                myEffects.remove(ef);
                return;
            }
            System.out.println("I am not affected by that effect.");
        }
    }

    /*Megnezi hogy egy effect rajta van-e mar a virologuson*/
    public boolean searchForEffect(String effect){
        for(Effect ef : myEffects){
            if(ef.getMyEffect().equals(effect)){
                return true;
            }
        }
        return false;
    }

    /*Visszaadja az effektek listáját*/
    public ArrayList<Effect> getEffects(){
        return myEffects;
    }

    /*Fontos hogy ezt mindig a jatekos korenek a legvegen hivjuk meg mert ha nem leszedheti az effectet a kor kozben*/
    public void update(){

    }
}
