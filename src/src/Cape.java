package src;

import effects.CapeEffect;

import java.io.Serializable;

public class Cape extends Equipment implements Serializable {                    				//Teszthez kell
	
	public Cape(double dc) {
		effect = new CapeEffect(dc);
		name = StringConstants.CAPE;
		System.out.println("Cape: I'm Created.");
	}
}
