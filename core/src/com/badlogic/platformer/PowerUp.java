package com.badlogic.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerUp {
	private final String[] power = {"Slow", "Fast"};
	private Rectangle box;
	private String p;
	private boolean isVisible;
	private float timer;
	private Texture img; 

	
	public PowerUp(Rectangle r, int p, Texture t) {
		box = r;
		this.p = power[p];
		isVisible = true;
		timer = 0;
		img = t;
	}	 
	
	public Texture getTexture() {
		return img;
	}
	
	public Rectangle getR() {
		return box;
	}
	
	public String getPower() {
		return p;
	}
	
	public void checkBox(float dt) {
		if(!isVisible) {
			timer += dt;
			if(timer >= 3 * 2) {
				isVisible = true;
				timer = 0;
			}
		}
	}
	
	public boolean getVisibility() {
		return isVisible;
	}
	
	public void disable() {
		isVisible = false;
	}
	
	public void enable() {
		isVisible = true;
	}
	
	public void powerup_dispose() {
		img.dispose();
	}
}