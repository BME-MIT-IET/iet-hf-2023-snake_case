package src;

import Effects.CapeEffect;

import java.io.Serializable;

public class Cape extends Equipment implements Serializable {                    				//Teszthez kell
	
	public Cape(double dc) {
		effect = new CapeEffect(dc);
		name = "cape";
		System.out.println("Cape: I'm Created.");
	}
}
