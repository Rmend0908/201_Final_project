package com.badlogic.platformer;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.platformer.Platformer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
	      config.setTitle("201 Final Proj");
	      config.setWindowedMode(1512, 982);
	      config.useVsync(true);
	      config.setForegroundFPS(60);
	      new Lwjgl3Application(new Platformer(), config);
	}
}
