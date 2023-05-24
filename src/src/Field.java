package src;

import java.io.Serializable;
import java.util.ArrayList;

public class Field implements Serializable {
    //A mezon tartozkodo virologusokat tartalmazo lista.
    private final ArrayList<Virologist> virologists = new ArrayList<>();

    //A szomszédos mezoket tartalmazo lista.
    private final ArrayList<Field> neighbours = new ArrayList<>();

    public Field(){
        //System.out.println("Field: Field has been created.");
    }

    //A szomszedos mezoket adja vissza
    public ArrayList<Field> getNeighbours(){
        return neighbours;
    }

    //Visszaadja a mezőn allo virológusokat.
    public ArrayList<Virologist> getVirologists(){
        return virologists;
    }

    //Virologus elhelyezése a mezore.
    public void accept(Virologist v){
        virologists.add(v);
        System.out.println("Field: The player has been accepted to the field.");
    }

    //Virológus eltavolitasa a mezorol.
    public void remove(Virologist v){
        virologists.remove(v);
        System.out.println("Field: The player has been removed from the field.");
    }

    public void addNeighbour(Field f1){
        if(f1 != null){
            neighbours.add(f1);
            f1.neighbours.add(this);
            System.out.println("Field: Added a neighbour.");
            return;
        }
        System.out.println("The field is null.");
    }

    //Virtualis fuggveny, a tobbi field tipuson talalhato objektumok begyujteseert felel.
    public void collect(Virologist v){
        System.out.println("Simple field collect.");
    }
}
