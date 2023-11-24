package com.badlogic.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Punk extends Character {
	private Texture playerIdle;
	private Texture playerCrouch;

	private Animation<TextureRegion> runAnimLeft;
	private Animation<TextureRegion> runAnimRight;
	private Animation<TextureRegion> jumpAnimLeft;
	private Animation<TextureRegion> jumpAnimRight;

	private Texture[] runLeftFrames;
	private Texture[] runRightFrames;
	private Texture[] jumpLeftFrames;
	private Texture[] jumpRightFrames;

	private TextureRegion currentFrame;
	private float stateTime;

	private final int[] KEY_MOVE = { Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.DOWN };
	private final int KEY_JUMP = Input.Keys.UP;

	public Punk() {
		super();
		punk_init();
	}

	public void punk_init() {
		playerIdle = new Texture(Gdx.files.internal("punk/Punk_idle.png"));
		playerCrouch = new Texture(Gdx.files.internal("punk/Punk_crouch.png"));

		punk_init_run_animations();
		punk_init_jump_animations();

		stateTime = 0f;
	}

	public void punk_init_run_animations() {
		/* Making Running animation */
		runRightFrames = new Texture[6];
		runLeftFrames = new Texture[6];
		for (int i = 0; i < runRightFrames.length; i++) {
			runRightFrames[i] = new Texture(Gdx.files.internal("punk/run-right/P" + (i + 1) + ".png"));
			runLeftFrames[i] = new Texture(Gdx.files.internal("punk/run-left/P" + (i + 1) + ".png"));
		}

		TextureRegion[] regionsLeft = new TextureRegion[6];
		TextureRegion[] regionsRight = new TextureRegion[6];

		for (int i = 0; i < 6; i++) {
			regionsLeft[i] = new TextureRegion(runLeftFrames[i]);
			regionsRight[i] = new TextureRegion(runRightFrames[i]);
		}

		runAnimLeft = new Animation<>(0.08f, regionsLeft);
		runAnimRight = new Animation<>(0.08f, regionsRight);
	}

	public void punk_init_jump_animations() {
		/* Making Jump Animations */
		jumpLeftFrames = new Texture[4];
		jumpRightFrames = new Texture[4];
		for (int i = 0; i < jumpLeftFrames.length; i++) {
			jumpRightFrames[i] = new Texture(Gdx.files.internal("punk/jump-right/P" + (i + 1) + ".png"));
			jumpLeftFrames[i] = new Texture(Gdx.files.internal("punk/jump-left/P" + (i + 1) + ".png"));
		}

		TextureRegion[] regionsJumpLeft = new TextureRegion[4];
		TextureRegion[] regionsJumpRight = new TextureRegion[4];

		for (int i = 0; i < 4; i++) {
			regionsJumpLeft[i] = new TextureRegion(jumpLeftFrames[i]);
			regionsJumpRight[i] = new TextureRegion(jumpRightFrames[i]);
		}

		jumpAnimLeft = new Animation<>(0.1f, regionsJumpLeft);
		jumpAnimRight = new Animation<>(0.1f, regionsJumpRight);
	}

	/*
	 * Renders movement that has multiple frames ex: run and jump
	 */
	public void punk_render() {
		// Determines frame
		stateTime += Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(KEY_MOVE[1])) {
			if (!getIsJumping()) {
				currentFrame = runAnimRight.getKeyFrame(stateTime, true);
			} else {
				currentFrame = jumpAnimRight.getKeyFrame(stateTime, true);
			}
		} else { // Moving left
			if (!getIsJumping()) {
				currentFrame = runAnimLeft.getKeyFrame(stateTime, true);
			} else {
				currentFrame = jumpAnimLeft.getKeyFrame(stateTime, true);
			}
		}

	}

	public void punk_batch(SpriteBatch batch) {
		if (Gdx.input.isKeyPressed(KEY_MOVE[0])) {
			batch.draw(currentFrame, getPlayerX(), getPlayerY());
		} else if (Gdx.input.isKeyPressed(KEY_MOVE[1])) {
			batch.draw(currentFrame, getPlayerX(), getPlayerY());
		} else if (Gdx.input.isKeyPressed(KEY_MOVE[2])) {
			batch.draw(playerCrouch, getPlayerX(), getPlayerY());
		} else if (getIsJumping()) {
			batch.draw(currentFrame, getPlayerX(), getPlayerY());
		} else {
			batch.draw(playerIdle, getPlayerX(), getPlayerY());
		}
	}

	public void inputs(ArrayList<PowerUp> items) {
		move(KEY_MOVE);
		checkForPowerUp(items);
		checkIfFloored();
		jump(KEY_JUMP);
		boundsCheck();
	}

	public void punk_dispose() {
		playerIdle.dispose();
		playerCrouch.dispose();
		for (Texture frame : runRightFrames) {
			frame.dispose();
		}
		for (Texture frame : runLeftFrames) {
			frame.dispose();
		}
	}
}