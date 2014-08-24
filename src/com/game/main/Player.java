package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.main.level.LevelHandler;
import com.lss.flasher.LEngine;
import com.lss.flasher.Graphics.Animation;
import com.lss.flasher.Graphics.Shape;
import com.lss.flasher.Graphics.ShapeRender;
import com.lss.flasher.tools.Loader;

public class Player extends Mob {

	public Animation walk = new Animation(0.15, 0, 0, Game.tileSize, Game.tileSize);
	public Animation attackAnimation = new Animation(0.15, 0, 0, Game.tileSize * 2, Game.tileSize);

	public Shape shape;

	public Player(int x, int y) {
		super(x, y);

		walk.addFrame(Loader.loadImage("/grimidle.png"));
		walk.addFrame(Loader.loadImage("/grimwalk.png"));
		attackAnimation.addFrame(Loader.loadImage("/grimhit.png"));
		attackAnimation.addFrame(Loader.loadImage("/grimhit1.png"));

		shape = new Shape(0, 0, 0, 0);
		shape.setShapeRender(new ShapeRender() {
			public void render(Graphics2D g) {

				if (attackAnimation.play) {
					if (attackLeft) {
						attackAnimation.x = -Game.tileSize;
					} else {
						attackAnimation.x = 0;
					}

					attackAnimation.setFlip(attackLeft);
					attackAnimation.render(g);
				} else {
					walk.render(g);
				}

			}
		});

		walk.startAnimation();
		attackAnimation.stopAnimation();
	}

	boolean attack = false;
	boolean attackLeft = false;

	boolean flip = false;

	boolean moveUp = false;
	boolean moveLeft = false;
	boolean moveDown = false;
	boolean moveRight = false;

	public int oldX, oldY;
	public int oldTX, oldTY;

	public int tileX, tileY;
	public int rTileX, rTileY;

	public boolean walkingSide = false;
	public boolean walkingUp = false;

	int dir = 0;

	int i = 0;
	int j = 0;

	@Override
	public void update() {

		tileX = (x + Game.tileSize / 2) / Game.tileSize;
		tileY = (y + Game.tileSize / 2) / Game.tileSize;

		int speed = 4;

		if (LEngine.key.getKey(0) && !moveUp && !moveDown) {
			dir = 0;
			rTileX = tileX;
			rTileY = tileY - 1;
			if (LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY) != null) {
				if (!LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY).solid) {
					walkingUp = true;
					moveUp = true;
					oldY = y;
					oldTY = tileY;
				}
			}
		}
		if (LEngine.key.getKey(1) && !moveRight && !moveLeft) {
			flip = true;
			dir = 1;
			rTileX = tileX - 1;
			rTileY = tileY;
			if (LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY) != null) {
				if (!LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY).solid) {
					walkingSide = true;
					moveLeft = true;
					oldX = x;
					oldTX = tileX;

				}
			}
		}
		if (LEngine.key.getKey(2) && !moveUp && !moveDown) {

			dir = 2;

			rTileX = tileX;
			rTileY = tileY + 1;
			if (LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY) != null) {
				if (!LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY).solid) {
					moveDown = true;
					oldY = y;
					walkingUp = true;
					oldTY = tileY;

				}
			}
		}
		if (LEngine.key.getKey(3) && !moveRight && !moveLeft) {
			flip = false;

			dir = 3;

			rTileX = tileX + 1;
			rTileY = tileY;
			if (LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY) != null) {
				if (!LevelHandler.getSelected().getLayer("collision").getTile(rTileX, rTileY).solid) {
					moveRight = true;
					oldX = x;
					walkingSide = true;
					oldTX = tileX;
				}
			}
		}

		if (dir == 1) {
			attackLeft = true;
		}

		if (dir == 3) {
			attackLeft = false;
		}

		if (LEngine.key.getKey(4) && !attackAnimation.play) {
			attackAnimation.play();
			
			
		}

		System.out.println(attackAnimation.selectedImage + ", " + attackAnimation.frames.size());

		

		attackAnimation.update();

		if (LevelHandler.getSelected().getLayer("collision").getTile(tileX, tileY) != null) {
			if (LevelHandler.getSelected().getLayer("collision").getTile(tileX, tileY).solid) {
				x = oldTX * Game.tileSize;
				y = oldTY * Game.tileSize;

			}
		}

		if (dir == 0) {
			rTileX = tileX;
			rTileY = tileY - 1;
		}
		if (dir == 1) {
			rTileX = tileX - 1;
			rTileY = tileY;
		}
		if (dir == 2) {
			rTileX = tileX;
			rTileY = tileY + 1;
		}
		if (dir == 3) {
			rTileX = tileX + 1;
			rTileY = tileY;
		}
		if (moveRight) {
			i += speed;
			if (i > Game.tileSize) {
				i = 0;
				moveRight = false;
				x = oldX + Game.tileSize;
			} else {
				x += speed;
			}
		}
		if (moveLeft) {
			i += speed;
			if (i > Game.tileSize) {
				i = 0;
				moveLeft = false;
				x = oldX - Game.tileSize;
			} else {
				x -= speed;
			}
		}

		if (moveDown) {
			j += speed;
			if (j > Game.tileSize) {
				j = 0;
				moveDown = false;
				y = oldY + Game.tileSize;
			} else {
				y += speed;
			}
		}
		if (moveUp) {
			j += speed;
			if (j > Game.tileSize) {
				j = 0;
				moveUp = false;
				y = oldY - Game.tileSize;
			} else {
				y -= speed;
			}
		}

		walk.setFlip(flip);

		shape.pos.set(x - Game.xOff, y - Game.yOff);

		walk.update();
		walk.setAnimationSpeed(0.25);
	}

	@Override
	public void render(Graphics g) {

		/*
		 * g.setColor(Color.green);
		 * g.fillRect(tileX * 64 - Game.xOff, tileY * 64 - Game.yOff, 64, 64);
		 * g.setColor(Color.yellow);
		 * g.drawRect(rTileX * 64 - Game.xOff, rTileY * 64 - Game.yOff, 64, 64);
		 * 
		 * g.setColor(Color.blue);
		 * g.drawRect(x - Game.xOff, y - Game.yOff, 64, 64);
		 * g.setColor(Color.blue);
		 * g.drawRect(x - Game.xOff + 32, y - Game.yOff + 32, 32, 32);
		 */

		shape.setGraphics(g.create());

	}

}
