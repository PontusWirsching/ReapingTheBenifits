package com.game.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import com.game.main.level.Level;
import com.game.main.level.LevelHandler;
import com.lss.flasher.LEngine;
import com.lss.flasher.StateHandler.State;

@SuppressWarnings("serial")
public class Game extends State
{

	public static String PATH = System.getenv("APPDATA") + File.separator + "LostSourceStudios" + File.separator + "ReapingTheBenefits";
	public static File FILE_PATH = new File(PATH);

	public static String RESOURCES = PATH + File.separator + "resources" + File.separator + "textures";
	
	public static int tileSize = 64;

	public static int xOff, yOff;

	public static Player player;

	public static int health = 1;

	public static int money;

	public Game(String name)
	{
		super(name);

		/*
		 * Generates a new folder in %appdata% if it does'nt exists.
		 */
		if (!FILE_PATH.exists())
		{
			System.err.println("[WARN] Could not find data folder! Generating new one now!");
			FILE_PATH.mkdirs();
			new File(PATH + File.separator + "levels").mkdirs();
			new File(PATH + File.separator + "resources" + File.separator + "textures").mkdirs();

		}
		
		
		
		
		
		player = new Player(0, 0);

//		LevelHandler.add(new Level("res/town.xml"));
		LevelHandler.add(new Level(PATH + File.separator + "levels" + File.separator + "main.xml"));

	}

	@Override
	public void update()
	{

		xOff = player.x - LEngine.WIDTH / 2;
		yOff = player.y - LEngine.HEIGHT / 2;

		LevelHandler.update();
		player.update();
		healthbar.run();

	}

	@Override
	public void render(Graphics g1)
	{

		Graphics2D g = (Graphics2D) g1.create();

		LevelHandler.render(g);
		player.render(g);

		Gui.drawHealthBar(g, 10, 30);
		Gui.drawMoney(g, -10, 620);
	}

	@Override
	public void selected()
	{

	}

	@Override
	public void unSelected()
	{

	}

}
