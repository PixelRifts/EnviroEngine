package com.pixelrifts.enviro.game.main;

import com.pixelrifts.enviro.engine.base.Display;
import com.pixelrifts.enviro.engine.math.Colour;

public class RunEnviroEngine {
	public static void main(String[] args) {
		Display.init();
		Display.createWindow(1080, 720, "Hello There!");
		Display.setBackground(Colour.Blue());

		while (!Display.shouldClose()) {
			Display.update();
		}

		Display.destroyWindow();
		Display.terminate();
	}
}
