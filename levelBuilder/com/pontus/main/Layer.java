package com.pontus.main;

import java.awt.Graphics;

import com.game.main.Game;
import com.game.main.level.Tile;
import com.lss.flasher.LEngine;

public class Layer {

	public Tile[] tiles;

	public String name;

	public int width;
	public int height;

	public Layer(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;

		tiles = new Tile[width * height];

	}

	public void render(Graphics g) {
		for (int x = (Main.xOff / Main.tileSize); x < (Main.xOff / Main.tileSize) + 33; x++) {
			for (int y = (Main.yOff / Main.tileSize); y < (Main.yOff / Main.tileSize) + 24; y++) {
				try {
					Tile t = getTile(x, y);
					t.renderEditor(g, x, y);
				} catch (Exception e) {}
			}
		}
	}
	
	public void renderGame(Graphics g) {
		for (int x = (Game.xOff / Game.tileSize); x < (Game.xOff / Game.tileSize) + LEngine.WIDTH / Game.tileSize + 2; x++) {
			for (int y = (Game.yOff / Game.tileSize); y < (Game.yOff / Game.tileSize) + LEngine.HEIGHT / Game.tileSize + 2; y++) {
				try {
					getTile(x, y).render(g);
				} catch (Exception e) {}
			}
		}
	}

	public void setTile(int x, int y, Tile tile) {
		tiles[x + y * width] = tile;
	}

	public Tile getTile(int x, int y) {
		if (x >= 0 && y >= 0 && x < width && y < height)
			return tiles[x + y * width];
		else
			return null;
	}

}
