package com.badlogic.platformer;

import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Platformer extends ApplicationAdapter {
	private final int SCREEN_WIDTH = 1512;
	private final int SCREEN_HEIGHT = 982;	
	private Texture backgroundImage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Biker player1;
	private Punk player2;
	private ArrayList<PowerUp> items;
	private ShapeRenderer shapeRenderer;
	

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture(Gdx.files.internal("race.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		player1 = new Biker();
		player2 = new Punk();
		
		shapeRenderer = new ShapeRenderer();
		createItems();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		player1.biker_render();
		player2.punk_render();
		
		
		// From template
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		// Batch draws stuff onto the screen
		batch.begin();
		batch.draw(backgroundImage, 0, 0, camera.viewportWidth, camera.viewportHeight);
		
		
		for(PowerUp p : items) {
			if(p.getVisibility()) {
				Rectangle r = p.getR();
				batch.draw(p.getTexture(), r.x, r.y);
			}
		}

		// Draw animation
		player1.biker_batch(batch);
		player2.punk_batch(batch);
		
		batch.end();
		
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(new Color(1, 0, 0, 0.9f)); // 50% transparent red
//	    shapeRenderer.rect(755, 675, 115, 37); // Draw a rectangle (x, y, width, height)
//	    shapeRenderer.end();
		player1.inputs(items);
		player2.inputs(items);
		
		/*
		 * update the physics - updates inputs and positions
		 *  after the input
		 * 
		 */
		
		if (Gdx.input.justTouched()) { // Check if the screen was just touched/clicked
	        int x = Gdx.input.getX();
	        int y = Gdx.input.getY();

	        // Flip the y-coordinate to match the libGDX coordinate system where y=0 is the bottom
	        int flippedY = Gdx.graphics.getHeight() - y;

	        System.out.println("Screen touched at: (" + x + ", " + flippedY + ")");
	    }
	}
	

	public void createItems() {
		items = new ArrayList<PowerUp>();
		items.add(new Fast(new Rectangle(445,90,70,70), 1)); // Fast
		items.add(new Fast(new Rectangle(440,820,70,70), 1)); // Fast
		items.add(new Fast(new Rectangle(1150,500,70,70), 1)); // Fast
		items.add(new Slow(new Rectangle(1120,120,70,70), 0)); // Slow
		items.add(new Slow(new Rectangle(820,545,70,70), 0)); // Slow
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		shapeRenderer.dispose();
		backgroundImage.dispose();
		player1.biker_dispose();
		player2.punk_dispose();
		
		for(PowerUp p : items)
			p.powerup_dispose();
	}
}
