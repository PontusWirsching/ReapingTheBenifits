package com.game.main;


public class healthbar {
	
	public static boolean freezeHPbar;
	
	public static void run() {
		
		if (Game.health >= 1000)freezeHPbar = true;
		
		if (freezeHPbar == false)
		{
			Game.health += 1;
//			System.out.println("Health:" + Game.health);
		}
		
		
		
	}
}
