package com.badlogic.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen{
	
	private Texture login;
	
	final Manager manager;
	OrthographicCamera camera;
	
	public MainMenuScreen(final Manager manager) {
	this.manager = manager;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1512, 982);
		login = new Texture(Gdx.files.internal("LoginExample.png"));
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		manager.batch.setProjectionMatrix(camera.combined);

		manager.batch.begin();
		manager.batch.draw(login, 0, 0, camera.viewportWidth, camera.viewportHeight);
		//manager.font.draw(manager.batch, "Welcome to Drop!!! ", 100, 150);
		manager.font.draw(manager.batch, "Tap anywhere to begin!", 100, 100);
		manager.batch.end();

		if (Gdx.input.isTouched()) {
			manager.setScreen(new Platformer(manager));
			dispose();
		}
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
