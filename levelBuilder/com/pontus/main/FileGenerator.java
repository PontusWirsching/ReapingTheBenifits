package com.pontus.main;

import java.io.FileWriter;
import java.util.ArrayList;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class FileGenerator {

	public static void generateFile(int tilesWidth, int tilesHeight, int tileSize, String path, ArrayList<Layer> layers) {
		try {

			Element level = new Element("tilemap");
			Document doc = new Document(level);
			level.setAttribute(new Attribute("tileswide", Integer.toString(tilesWidth)));
			level.setAttribute(new Attribute("tileshigh", Integer.toString(tilesHeight)));
			level.setAttribute(new Attribute("tilewidth", Integer.toString(tileSize)));
			level.setAttribute(new Attribute("tileheight", Integer.toString(tileSize)));

			Element prop = new Element("properties");
			prop.setAttribute(new Attribute("name", "properties"));

			Element bg = new Element("background");
			bg.setAttribute(new Attribute("red", "255"));
			bg.setAttribute(new Attribute("green", "255"));
			bg.setAttribute(new Attribute("blue", "255"));
			prop.addContent(bg);

			level.addContent(prop);

			for (Layer l : layers) {

				Element layer = new Element("layer");
				layer.setAttribute(new Attribute("number", String.valueOf(layers.indexOf(l))));
				layer.setAttribute(new Attribute("name", l.name));

				for (int y = 0; y < tilesHeight; y++) {
					for (int x = 0; x < tilesWidth; x++) {
						if (l.tiles[x + y * tilesWidth] != null) {
							Element t = new Element("tile");
							t.setAttribute(new Attribute("x", Integer.toString(x)));
							t.setAttribute(new Attribute("y", Integer.toString(y)));
							t.setAttribute(new Attribute("tile", l.tiles[x + y * tilesWidth].name));
							t.setAttribute(new Attribute("collision", String.valueOf(l.tiles[x + y * tilesWidth].solid)));

							t.setAttribute(new Attribute("rot", "0"));
							t.setAttribute(new Attribute("flipX", "false"));
							layer.addContent(t);

						} else {
							Element t = new Element("tile");
							t.setAttribute(new Attribute("x", Integer.toString(x)));
							t.setAttribute(new Attribute("y", Integer.toString(y)));
							t.setAttribute(new Attribute("tile", "null"));
							t.setAttribute(new Attribute("collision", "false"));

							t.setAttribute(new Attribute("rot", "0"));
							t.setAttribute(new Attribute("flipX", "false"));
							layer.addContent(t);

						}

					}
				}

				level.addContent(layer);
			}
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(path));

			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateFile(int tilesWidth, int tilesHeight, int tileSize, String path) {
		try {

			Element level = new Element("tilemap");
			Document doc = new Document(level);
			level.setAttribute(new Attribute("tileswide", Integer.toString(tilesWidth)));
			level.setAttribute(new Attribute("tileshigh", Integer.toString(tilesHeight)));
			level.setAttribute(new Attribute("tilewidth", Integer.toString(tileSize)));
			level.setAttribute(new Attribute("tileheight", Integer.toString(tileSize)));

			Element prop = new Element("properties");
			prop.setAttribute(new Attribute("name", "properties"));

			Element bg = new Element("background");
			bg.setAttribute(new Attribute("red", "255"));
			bg.setAttribute(new Attribute("green", "255"));
			bg.setAttribute(new Attribute("blue", "255"));
			prop.addContent(bg);

			level.addContent(prop);

			Element layer = new Element("layer");
			layer.setAttribute(new Attribute("number", "0"));
			layer.setAttribute(new Attribute("name", "tiles"));

			for (int y = 0; y < tilesHeight; y++) {
				for (int x = 0; x < tilesWidth; x++) {
					Element t = new Element("tile");
					t.setAttribute(new Attribute("x", Integer.toString(x)));
					t.setAttribute(new Attribute("y", Integer.toString(y)));
					t.setAttribute(new Attribute("tile", "null"));
					t.setAttribute(new Attribute("collision", "false"));

					t.setAttribute(new Attribute("rot", "0"));
					t.setAttribute(new Attribute("flipX", "false"));

					layer.addContent(t);
				}
			}

			level.addContent(layer);

			Element layer2 = new Element("layer");
			layer2.setAttribute(new Attribute("number", "1"));
			layer2.setAttribute(new Attribute("name", "collision"));

			for (int y = 0; y < tilesHeight; y++) {
				for (int x = 0; x < tilesWidth; x++) {
					Element t = new Element("tile");
					t.setAttribute(new Attribute("x", Integer.toString(x)));
					t.setAttribute(new Attribute("y", Integer.toString(y)));
					t.setAttribute(new Attribute("tile", "null"));
					t.setAttribute(new Attribute("collision", "false"));

					t.setAttribute(new Attribute("rot", "0"));
					t.setAttribute(new Attribute("flipX", "false"));

					layer2.addContent(t);
				}
			}

			level.addContent(layer2);

			
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(path));

			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
