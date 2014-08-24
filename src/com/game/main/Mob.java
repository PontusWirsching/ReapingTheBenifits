package com.game.main;

import java.awt.Graphics;

import com.game.main.level.LevelHandler;
import com.lss.flasher.LEngine;

public abstract class Mob {

	public String tag;
	public int x;
	public int y;
	
	public boolean flip = false;

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
	
	
	public Mob(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int move) {
		if (move == 0 && !moveUp && !moveDown) {
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
		if (move == 1 && !moveRight && !moveLeft) {
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
		if (move == 2 && !moveUp && !moveDown) {
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
		if (move == 3 && !moveRight && !moveLeft) {
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
	}
	
	public int speed = 4;

	
	public void update() {
	
		
		tileX = (x + Game.tileSize / 2) / Game.tileSize;
		tileY = (y + Game.tileSize / 2) / Game.tileSize;



	
		
		
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
	}
	public abstract void render(Graphics g);
	
}
