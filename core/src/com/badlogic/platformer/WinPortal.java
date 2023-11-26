package com.badlogic.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WinPortal {
	private static Texture portalTexture = new Texture(Gdx.files.internal("Finish.png"));;
	private final int PORTAL_HEIGHT = 75;
	private final int PORTAL_WIDTH = 75;
	
	private Rectangle portal;
	
	public WinPortal() {
		portal = new Rectangle(1375, 780, PORTAL_WIDTH, PORTAL_HEIGHT);
	}
	
	public void portal_batch(SpriteBatch batch) {
		batch.draw(portalTexture, portal.x, portal.y, PORTAL_HEIGHT, PORTAL_WIDTH);
	}
	
	public Rectangle getRect() {
		return portal;
	}
	
	
	public void portal_dispose() {
		portalTexture.dispose();
	}
}
