package com.game.main.mobs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import com.game.main.Game;
import com.game.main.Mob;
import com.lss.flasher.LEngine;
import com.lss.flasher.Graphics.Animation;
import com.lss.flasher.Graphics.Shape;
import com.lss.flasher.Graphics.ShapeRender;
import com.lss.flasher.tools.Loader;

public class OldMan extends Mob {

	public Animation walk = new Animation(0.15, 0, 0, Game.tileSize, Game.tileSize);

	public Shape shape;

	public OldMan(int x, int y) {
		super(x, y);

		speed = 1;

		walk.addFrame(Loader.loadImage("/oldman.png"));
		walk.addFrame(Loader.loadImage("/oldman1.png"));
		walk.addFrame(Loader.loadImage("/oldman2.png"));

		shape = new Shape(0, 0, 0, 0);
		shape.setShapeRender(new ShapeRender() {
			public void render(Graphics2D g) {
				walk.render(g);

			}
		});

		walk.startAnimation();

	}

	public Random random = new Random();

	int timer = 0;

	@Override
	public void update() {
		super.update();

		timer++;

		if (timer > 30) {
			timer = 0;
			int rand = random.nextInt(8);

			move(rand);
		}

		walk.setFlip(flip);

		shape.pos.set(x - Game.xOff, y - Game.yOff);

		walk.update();
		walk.setAnimationSpeed(0.25);

	}

	@Override
	public void render(Graphics g) {
		shape.setGraphics(g.create());

	}

}
