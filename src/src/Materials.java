package src;

import java.io.Serializable;

public class Materials implements Serializable {
    /*Valtozok*/
    private int aminoacid;
    private int nukleotid;
    private int capacity = 5;

    /*Konstruktor, itt*/
    Materials(int amino, int nukelo){
        aminoacid = amino;
        nukleotid = nukelo;
    }

    /*Fuggvenyek*/
    /*Megvaltoztatja az amino erteket, az argumentumkent megadott szammal*/
    public void ChangeAmino(int amount){
        if(amount < 0){
            aminoacid = aminoacid + amount;
            return;
        }
        if((amount+aminoacid) > capacity){
            System.out.println("Materials: That's too much Amino, I'm going to take as much as I can and burn the rest");
            aminoacid = capacity;
            return;
        }
        aminoacid = aminoacid + amount;
    }

    /*Megvaltoztatja a nukleo erteket, az argumentumkent megadott szammal*/
    public void ChangeNukleo(int amount){
        if(amount < 0){
            nukleotid = nukleotid + amount;
            return;
        }
        if((amount+nukleotid) > capacity){
            System.out.println("Materials: That's too much Nukleo, I'm going to take as much as I can and burn the rest");
            nukleotid = capacity;
            return;
        }
        nukleotid = nukleotid + amount;
    }

    /*Visszaadja az amino erteket*/
    public int GetAmino(){
        return aminoacid;
    }

    /*Visszaadja az nukelo erteket*/
    public int getNukleo(){
        return nukleotid;
    }

    /*Visszaadja a "capacity" erteket*/
    public int GetCapacity(){
        return capacity;
    }

    public void SetCapacity(int value){capacity = value;}

    @Override
    public String toString() {
        return "Amino: " + aminoacid + " Nukleo: " + nukleotid;
    }
}
