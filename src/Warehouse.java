package src;

import java.io.Serializable;

public class Warehouse extends Field implements Update, Serializable {
    //Az anyag ujratermelesenek az ideje.
    private int cooldown;

    //Az anyag tipusa, lehet nukleotid vagy aminosav.
    private char type;

    //Tarolja, hogy mennyi anyag van a raktarban
    private int amount;

    /*Mennyi anyag van a raktarban maximum*/
    private int capacity;

    /*Ujratoltesig meg hatra van ennyi*/
    private int timeUntilRefill;

    public Warehouse(int cooldown, char type, int amount){
        this.cooldown = cooldown;
        this.timeUntilRefill = 0;
        this.amount = amount;
        this.type = type;
        this.capacity = amount;
        System.out.println("Warehouse created.");
    }

    //A virologus ennek segitsegevel gyujtheti ossze a mezon levo anyagokat.
    public void Collect(Virologist v){
        if(amount != 0) {
        	v.GetInventory().ChangeMaterial(type, amount);
        	
        	if(type == 'a')
                System.out.println("The virologist took " + amount + " amino acid.");
            else System.out.println("The virologist took " + amount + " nukleotid.");
        	timeUntilRefill = cooldown;
        	amount = 0;
        }
        else {
        	System.out.println("The Warehouse was empty, the virologist couldn't collect anything");
        }
    	

    }
    
    //Visszaadja a raktar anyagtipusat
    public char getType() {
    	return type;
    }
    
    //Visszaadja a jelenleg tarolt anyagmennyiseget
    public int getAmount() {
    	return amount;
    }

    //Ennek a fuggvenynek a hatasara tolti ujra az anyagkeszletet
    public void Update(){
        timeUntilRefill--;
        if(timeUntilRefill == 0){
            amount = capacity;
            timeUntilRefill = cooldown;
        }
    }
}
