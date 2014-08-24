package com.pontus.main;

import java.awt.Graphics;

import com.game.main.level.Tile;

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
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				try {
					Tile t = getTile(x, y);
					t.renderEditor(g, x, y);
				} catch (Exception e) {}
			}
		}
	}
	
	public void renderGame(Graphics g) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				try {
					Tile t = getTile(x, y);
					t.render(g);
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
