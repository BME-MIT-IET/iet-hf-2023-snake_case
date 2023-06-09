package effects;

import src.Effect;
import src.Virologist;

import java.io.Serializable;

public class CapeEffect extends Effect implements Serializable {
	private final double dodgeChance;

	public CapeEffect(double dc) {			//Ez nem kell, hogy igy legyen, szimplan csak egy alapotlet -> Ha duration 0, akkor felszereles effectje pl.
		duration = 0;
		timeLeft = 0;
		dodgeChance = dc;
		myEffect = "CapeEffect";
		System.out.println("CapeEffect: I'm Created");
	}
	
	public void effect(Virologist v) {
		/*CapeEffect-et majd itt valositsuk meg, ahogyan akarjuk, nekem csak a lete kellett a teszteleshez.*/
		System.out.println("CapeEffect: My Effect is activated");
		v.setDodgeChance(dodgeChance);
	}
	public void removeEffect(Virologist v){
		v.setDodgeChance(v.getOriginalDodgeChance());
		System.out.println("CapeEffect: My Effect is removed");
	}

}
