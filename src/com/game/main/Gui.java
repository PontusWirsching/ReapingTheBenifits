package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.lss.flasher.Graphics.Font;
import com.lss.flasher.Graphics.Sheet;
import com.lss.flasher.tools.Loader;

public class Gui {

	public static Sheet reaper = new Sheet(128, 32, Loader.loadBufferedImageAbsolute(Game.RESOURCES + "/gui/reaper.png"));

	public static Image reaperBG = reaper.getByPixels(4, 16, 104, 13);
	public static Image reaperBar = reaper.getByPixels(5, 8, 90, 4);

	public static Image money = Loader.loadImageWithAbsolutePath(Game.RESOURCES + "/gui/money.png");
	
	public static void drawHealthBar(Graphics g, int x, int y) {
		g.drawImage(reaperBG, x, y, 104 * 4, 13 * 4, null);
		g.drawImage(reaperBar, x + 13 * 4, y + 4 * 4, 90 * 4, 4 * 4, null);
		
		if (Game.health < 1000) 
			{
			int h = (int) (((360) / 100.0) * Game.health / 10);
			g.setColor(new Color(91,91,91));
			g.fillRect((x + 13 * 4 + 90 * 4) - h, y + 4 * 4, h, 16);
			}
		else
		{
			g.setColor(new Color(91,91,91));
			g.fillRect((x + 13 * 4 + 90 * 4) - 360, y + 4 * 4, 90*4, 16);
		}
		
		
	}
	
	public static void drawMoney(Graphics g, int x, int y) {
		g.drawImage(money, x, y, 32 * 4, 32 * 4, null);
		Font.setSize(24);
		Font.draw(Game.money+"", x+114, y+55);
	}
	

}
