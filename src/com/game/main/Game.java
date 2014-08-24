package com.game.main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.main.level.Level;
import com.game.main.level.LevelHandler;
import com.lss.flasher.LEngine;
import com.lss.flasher.StateHandler.State;

@SuppressWarnings("serial")
public class Game extends State {

	public static int tileSize = 128;

	public static int xOff, yOff;

	public static Player player;

	public static int health = 1;
	
	public static int money;


	public Game(String name) {
		super(name);

		player = new Player(0,0);

		
		
		LevelHandler.add(new Level("res/town.xml"));

	}

	@Override
	public void update() {
		
		
		xOff = player.x - LEngine.WIDTH / 2 + 32;
		yOff = player.y - LEngine.HEIGHT / 2 + 32;
		
		LevelHandler.update();
		player.update();
		healthbar.run();

		
		}

	@Override
	public void render(Graphics g1) {
		
		Graphics2D g = (Graphics2D) g1.create();
		
		
		
		LevelHandler.render(g);
		player.render(g);

		Gui.drawHealthBar(g, 10, 30);
		Gui.drawMoney(g, -10, 620);
	}

	@Override
	public void selected() {

	}

	@Override
	public void unSelected() {

	}

}
