package com.badlogic.platformer;

import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Platformer implements Screen {
	final Manager manager;
	
	private final int SCREEN_WIDTH = 1512;
	private final int SCREEN_HEIGHT = 982;
	private Texture backgroundImage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Biker player1;
	private Punk player2;
	private ArrayList<PowerUp> items;
	private WinPortal portal;
	private ShapeRenderer shapeRenderer;

	private boolean GAME_OVER = false;

	public Platformer(Manager manager) {
		this.manager = manager;
		batch = new SpriteBatch();
		backgroundImage = new Texture(Gdx.files.internal("race.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		player1 = new Biker();
		player2 = new Punk();
		portal = new WinPortal();
		shapeRenderer = new ShapeRenderer();
		createItems();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);

		player1.biker_render();
		player2.punk_render();

		// From template
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		// Batch draws stuff onto the screen
		batch.begin();
		batch.draw(backgroundImage, 0, 0, camera.viewportWidth, camera.viewportHeight);

		for (PowerUp p : items) {
			if (p.getVisibility()) {
				Rectangle r = p.getR();
				batch.draw(p.getTexture(), r.x, r.y);
			}
		}

		// Draw animation
		player1.biker_batch(batch);
		player2.punk_batch(batch);
		portal.portal_batch(batch);
		if (!GAME_OVER) {
			player1.inputs(items);
			player2.inputs(items);
		}
		batch.end();

		// update physics
		player1.updatePhysics();
		player2.updatePhysics();
		

		if (checkFinished()) {
			System.out.println("Game finished. To reset press 'SPACE'");
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				GAME_OVER = false;
				resetPositions();
				System.out.println("Restarting game");
				// Update leaderboard
			}
		}
	}

	public void resetPositions() {
		player1.resetPositions();
		player2.resetPositions();
	}

	public void createItems() {
		items = new ArrayList<PowerUp>();
		items.add(new Fast(new Rectangle(445, 90, 70, 70), 1)); // Fast
		items.add(new Fast(new Rectangle(440, 820, 70, 70), 1)); // Fast
		items.add(new Fast(new Rectangle(1150, 500, 70, 70), 1)); // Fast
		items.add(new Slow(new Rectangle(1120, 120, 70, 70), 0)); // Slow
		items.add(new Slow(new Rectangle(820, 545, 70, 70), 0)); // Slow
	}

	public boolean checkFinished() {
		if (player1.getRect().overlaps(portal.getRect()) || player2.getRect().overlaps(portal.getRect()))
			GAME_OVER = true;

		return GAME_OVER;
	}

	@Override
	public void dispose() {
		batch.dispose();
		backgroundImage.dispose();
		player1.biker_dispose();
		player2.punk_dispose();
		portal.portal_dispose();
		for (PowerUp p : items)
			p.powerup_dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
