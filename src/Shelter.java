package src;

import java.io.Serializable;

public class Shelter extends Field implements Serializable {
    //A mezon megtalalhato felszereles.
    private Equipment equipment;
    private int Counter;

    public Shelter(Equipment equipment){
        this.equipment = equipment;
        this.equipment.setHovaTartozas(this);
        Counter = 3;
        System.out.println("Shelter has been created.");
    }

    //Az ovohelyen talalhato vedofelszereles felvetelet valositja meg.
    public void Collect(Virologist v){
    	System.out.println("The virologist tries to take the Equipment");
        if(Counter > 0){
            v.Equip(equipment);
            Counter--;
        }
        else{
            System.out.println("This shelter is empty");
        }
    }
    
    public Equipment getEquipment() {
    	return equipment;
    }

    public void IncreaeCounter(){
        Counter++;
    }
}
