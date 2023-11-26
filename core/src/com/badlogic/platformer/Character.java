package com.badlogic.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/*
 * SuperClass
 */
public class Character{
	private final int SCREEN_WIDTH = 1512;
	private final int SCREEN_HEIGHT = 982;
	private final int PLAYER_HEIGHT = 120;
	private final int PLAYER_WIDTH = 80;

	private Rectangle player;
	private Floors floor;

	private boolean isJumping;
	private boolean isFloored;
	private Rectangle currentFloor;
	private float floorV = 0;

	private final float GRAVITY = -9.8f;
	private float jump_velocity = 500;
	private float velocityY = 0;

	private static int startPosition = 1;

	private float velocityX = 0;
	private float acceleration = 500;
	private float deceleration = 1000;
	private float maxSpeed = 500;
	
	private boolean hasEffect;
	private float timer;

	public Character() {
		player = new Rectangle(SCREEN_WIDTH / 8 * (startPosition++) - PLAYER_HEIGHT / 2, 70, PLAYER_HEIGHT, PLAYER_WIDTH);
		currentFloor = new Rectangle();
		floor = new Floors();
		isJumping = false;
		isFloored = true;
		
		hasEffect = false;
		timer = 0;
	}
	
	public void resetPositions() {
		startPosition %= 2;
		player.x = (SCREEN_WIDTH / 8 * (startPosition++) - PLAYER_HEIGHT / 2);
		player.y = 70;
	}
	

	public void boundsCheck() {
		if (player.x < 0) {
			player.x = 0;
		}
		if (player.x > SCREEN_WIDTH - PLAYER_WIDTH) {
			player.x = (SCREEN_WIDTH - PLAYER_WIDTH);
		}
		if (player.y > SCREEN_HEIGHT - PLAYER_WIDTH) {
			player.y = (SCREEN_HEIGHT - PLAYER_WIDTH);
		}
		if (player.y <= 70) {
			player.y = (70);
		}
	}

	/*
	 * "How can i implement a physics mechanic that resembles horizontal movement
	 * like inertia where running kind of drags based off of its momentum. How can i
	 * implement this into my games movement function?" prompt (25 lines).
	 * ChatGPT, Nov 22 Version, OpenAI, 22 Nov. 2023, chat.openai.com/chat
	 */
	public void move(int[] KEY_MOVE) {
		float deltaTime = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(KEY_MOVE[1])) { // Move right
			velocityX += acceleration * deltaTime;
		} else if (Gdx.input.isKeyPressed(KEY_MOVE[0])) { // Move left
			velocityX -= acceleration * deltaTime;
		} else { // Decelerate
			if (velocityX > 0) {
				velocityX -= deceleration * deltaTime;
				if (velocityX < 0)
					velocityX = 0; // Avoid overshooting
			} else if (velocityX < 0) {
				velocityX += deceleration * deltaTime;
				if (velocityX > 0)
					velocityX = 0; // Avoid overshooting
			}
		}
		
		if (Gdx.input.isKeyPressed(KEY_MOVE[2])) {
			player.y -= acceleration * deltaTime;
		}

		// Keep within limits
		if (velocityX > maxSpeed)
			velocityX = maxSpeed;
		if (velocityX < -maxSpeed)
			velocityX = -maxSpeed;

		// Update player X pos
		if(player.x + velocityX * deltaTime <= 0)
			player.x = 0;
		else if(player.x + velocityX * deltaTime >= SCREEN_WIDTH)
			player.x = SCREEN_WIDTH-PLAYER_WIDTH;
		else
			player.x += velocityX * deltaTime;
	}

	public void jump(int KEY_JUMP) {
		float deltaTime = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(KEY_JUMP) && !isJumping) {
			velocityY = jump_velocity;
			isJumping = true;
		}

		if (isJumping) {
			velocityY += GRAVITY;
			player.y += velocityY * deltaTime;

			
			if (player.y < 70) {
				player.y = 70;
				isFloored = true;
				isJumping = false;
				velocityY = 0;
			}
			
		}
	}
	
	public boolean checkIfFloored() {
		float deltaTime = Gdx.graphics.getDeltaTime();

		for (Rectangle f : floor.getPlatforms()) {
			if ((Math.abs((f.y + f.getHeight() - player.y)) <= 5)
					&& (player.x >= f.x && player.x <= f.x + f.getWidth())) {
				isJumping = false;
				isFloored = true;
				velocityY = 0;
				player.y = f.y + f.getHeight();
				return true;
			}
		}
		isJumping = true;
		isFloored = false;
		velocityY += GRAVITY;
		player.y += velocityY * deltaTime;

		return false;
	}

	public void checkForPowerUp(ArrayList<PowerUp> items) {
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		clearEffects(deltaTime);
		
		for(PowerUp p : items) {
		    p.checkBox(deltaTime);
		    
			if(player.overlaps(p.getR()) && p.getVisibility() && !hasEffect) {
				p.disable();
				if(p.getPower().equals("Slow")) {
					setEffects(((Slow) p).effect());
				} else {
					setEffects(((Fast) p).effect());
				}
				hasEffect = true;
			}
		}
	}
	
	private void clearEffects(float dt) {
		if(hasEffect) {
			timer += dt;
			if(timer >= 3 * 2) {
				jump_velocity = 500;
				acceleration = 500;
				deceleration = 1000;
				maxSpeed = 500;
				hasEffect = false;
				timer = 0;
			}
		}
	}
	
	private void setEffects(float modifier) {
		acceleration *= modifier;
		deceleration *= modifier;
		maxSpeed *= modifier;
	}
	
	public Rectangle getRect() {
		return player;
	}

	public boolean getIsJumping() {
		return isJumping;
	}

	public float getPlayerX() {
		return player.getX();
	}

	public float getPlayerY() {
		return player.getY();
	}

	public void setPlayerX(float x) {
		player.x = x;
	}

	public void setPlayerY(float y) {
		player.y = y;
	}
}
