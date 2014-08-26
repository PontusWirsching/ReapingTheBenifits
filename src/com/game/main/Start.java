package com.game.main;

import java.awt.event.KeyEvent;

import com.lss.flasher.LEngine;
import com.lss.flasher.StateHandler.StateHandler;

public class Start extends LEngine {
	
	
	/*
	 * Here is everything that i think is missing from the game. The things with arrows is easier
	 * for you i think. Ask me or gavin if you have any questions!
	 */
	
	
	//TODO - Optimize rendering for both game and editor.
	//TODO - DONE - Change Loading and Saving dir to %appdata% lss folder
	//TODO - Add more NPC's - Talk to GAVIN                                       <-----------------
	//TODO - Add more tiles and design the map                                    <-----------------
	//TODO - Add barricades around the map like road blocks and trees etc.        <-----------------
	//TODO - Make the kill meter count kills.
	//TODO - Add a menu                                 					      <-----------------
	//TODO - Add a game over menu                             			          <-----------------
	//TODO - Add looting money from different places like Bins and Under benches.
	//TODO - Make so you get a random amount of money from NPC's.
	//TODO - Add a shop with upgrades.              	                          <-----------------
	//TODO - Fix the layered rendering.
	
	
	
	
	
	
	
	
	public Start(int width, int height, String frameName) {
		super(width, height, frameName);

		key.addKey(KeyEvent.VK_W);
		key.addKey(KeyEvent.VK_A);
		key.addKey(KeyEvent.VK_S);
		key.addKey(KeyEvent.VK_D);
		key.addKey(KeyEvent.VK_SPACE);

		StateHandler.addState(new Game("GAME"));
		
		StateHandler.setState("GAME");
		
		skipIntro();
		start();
	}

	@Override
	public void update() {
		StateHandler.update();
	}

	@Override
	public void render() {
		StateHandler.render(g);
	}

	public static void main(String[] args) {
		new Start(1024, 720, "Reaping the Benefits! - Alpha");

	}

}
