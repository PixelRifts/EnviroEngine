package com.pixelrifts.enviro.game.main;

import com.pixelrifts.enviro.engine.math.Colour;
import com.pixelrifts.enviro.engine.quads.ColouredQuad;
import com.pixelrifts.enviro.engine.quads.QuadRenderer;

public class RunEnviroEngine {
	public static void main(String[] args) {
		Display.init();
		Display.createWindow(1080, 720, "Hello There!");
		Display.setBackground(Colour.DarkGrey());
		
		QuadRenderer quadrenderer = new QuadRenderer();
		ColouredQuad quad = new ColouredQuad(-1, -1, 0.25f, 0.25f, Colour.Cyan());

		while (!Display.shouldClose()) {
			quadrenderer.submit(quad);
			Display.update(quadrenderer);
		}

		Display.destroyWindow();
		Display.terminate();
	}
}
