package com.game.main.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.game.main.Game;
import com.game.main.Mob;
import com.game.main.mobs.OldMan;
import com.lss.flasher.LEngine;
import com.lss.flasher.tools.Loader;
import com.pontus.main.Layer;

public class Level {

	public String file = "";
	public int width, height, tileSize;

	// --------------
	public ArrayList<Layer> layers = new ArrayList<>();

	public void addLayer(Layer layer) {
		layers.add(layer);
	}

	public Layer getLayer(String name) {
		for (Layer l : layers) {
			if (l.name.equals(name)) {
				return l;
			}
		}
		return null;
	}

	public Layer getLayer(int index) {
		return layers.get(index);
	}

	public int selectedLayer = 0;

	public Layer getWorkingLayer() {
		return getLayer(selectedLayer);
	}

	// -------------------

	public ArrayList<Mob> mobs = new ArrayList<>();

	public void addMob(Mob mob) {
		mobs.add(mob);
	}

	public Mob getMob(int index) {
		return mobs.get(index);
	}

	public Level(String file) {
		this.file = file;
		loadLevel();

		addMob(new OldMan(0, 0));

	}

	public void update() {
		for (Mob m : mobs) {
			m.update();
		}
		System.out.println("FPS: " + LEngine.getFPS());
	}

	public void render(Graphics g) {
		for (Layer l : layers) {
			l.renderGame(g);
		}
		
		for (Mob m : mobs) {
			m.render(g);
		}

		g.setColor(Color.blue);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				g.drawRect(i * Game.tileSize - Game.xOff, j * Game.tileSize - Game.yOff,  Game.tileSize, Game.tileSize);
			}
		}
	}

	public SAXBuilder builder;
	public Document d;

	private int parse(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			System.err.println(s);
		}
		return -1;
	}

	public Element get(List<Element> l, String name) {
		for (Element e : l) {
			if (e.getAttributeValue("name").equals(name)) {
				return e;
			}
		}
		return null;
	}

	public Tile getTile(int x, int y, Layer layer) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return null;
		else
			return layer.tiles[x + y * width];

	}

	public List<Element> getMultiple(List<Element> l, String tag) {
		List<Element> list = new ArrayList<>();

		for (Element e : l) {
			if (e.getName().equals(tag)) {
				list.add(e);
			}
		}
		return list;

	}

	public void loadLevel() {
		builder = new SAXBuilder();
		try {
			d = builder.build(new File(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		width = parse(d.getRootElement().getAttributeValue("tileswide"));
		height = parse(d.getRootElement().getAttributeValue("tileshigh"));
		tileSize = parse(d.getRootElement().getAttributeValue("tilewidth"));

		/**
		 * All loaded layers, and other general level properties.
		 */
		List<Element> first = d.getRootElement().getChildren();

		List<Element> layers = getMultiple(first, "layer");

		for (Element e : layers) {
			Layer l = new Layer(e.getAttributeValue("name"), width, height);
			List<Element> layerContents = e.getChildren();

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					try {
						Element tile = layerContents.get(x + y * width);

						if (!tile.getAttributeValue("tile").equals("null")) {
							Image image = Loader.loadImageWithAbsolutePath(tile.getAttributeValue("tile"));
							Tile t = new Tile(tile.getAttributeValue("tile"), image, x, y);
							l.tiles[x + y * width] = t;

						} else {
							Tile t = new Tile("null", null, x, y);
							l.tiles[x + y * width] = t;
						}
					} catch (Exception ex) {

					}
				}
			}
			addLayer(l);
		}

	}

}
