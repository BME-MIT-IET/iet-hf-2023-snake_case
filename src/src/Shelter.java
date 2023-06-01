package src;

import java.io.Serializable;

public class Shelter extends Field implements Serializable {
    //A mezon megtalalhato felszereles.
    private final Equipment equipment;
    private int counter;

    public Shelter(Equipment equipment){
        this.equipment = equipment;
        this.equipment.setHovaTartozas(this);
        counter = 3;
        System.out.println("Shelter has been created.");
    }

    //Az ovohelyen talalhato vedofelszereles felvetelet valositja meg.
    @Override
    public void collect(Virologist v){
    	System.out.println("The virologist tries to take the Equipment");
        if(counter > 0){
            v.equip(equipment);
            counter--;
        }
        else{
            System.out.println("This shelter is empty");
        }
    }
    
    public Equipment getEquipment() {
    	return equipment;
    }

    public void increaseCounter(){
        counter++;
    }

    public int getCounter(){return counter;}
}
