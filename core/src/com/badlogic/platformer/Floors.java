package com.badlogic.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

public class Floors {
	private ArrayList<Rectangle> platforms;

	public Floors() {
		platforms = new ArrayList<Rectangle>();
		createPlatforms();
	}

	public void createPlatforms() {
		// Long x5
		platforms.add(new Rectangle(275, 350, 255, 80)); // Done
		platforms.add(new Rectangle(830, 335, 293, 40)); // Done
		platforms.add(new Rectangle(660, 487, 283, 40)); // Done
		platforms.add(new Rectangle(1190, 208, 218, 40));// Done
		platforms.add(new Rectangle(1160, 698, 248, 60));// Done

		// Med x1
		platforms.add(new Rectangle(0, 195, 160, 50)); // Done

		// Small x6
		platforms.add(new Rectangle(160, 527, 155, 67)); // Done
		platforms.add(new Rectangle(80, 717, 150, 37)); // Done
		platforms.add(new Rectangle(590, 705, 150, 37)); // Done
		platforms.add(new Rectangle(795, 785, 165, 37)); // Done
		platforms.add(new Rectangle(970, 653, 160, 37)); // Done

		// Tiny x1
		platforms.add(new Rectangle(0, 295, 95, 80)); // Done

		// Pillar x1
		platforms.add(new Rectangle(385, 500, 115, 300)); // Done

		// Floor x1
		platforms.add(new Rectangle(0, 0, 1512, 70)); // Done

	}
	
	public ArrayList<Rectangle> getPlatforms(){
		return platforms;
	}
}

