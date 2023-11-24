package com.badlogic.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Slow extends PowerUp{
	private static Texture slowTexture = new Texture(Gdx.files.internal("PowerSlow.png"));;
	
	public Slow(Rectangle r, int p) {
		super(r, p, slowTexture);
	}
	
	public float effect() {
		return 0.5f;
	}
	
}