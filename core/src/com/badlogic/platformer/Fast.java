package com.badlogic.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Fast extends PowerUp{
	private static Texture fastTexture = new Texture(Gdx.files.internal("PowerSpeed.png"));;
	
	public Fast(Rectangle r, int p) {
		super(r, p, fastTexture);
	}
	
	public float effect() {
		return 1.5f;
	}
	
}