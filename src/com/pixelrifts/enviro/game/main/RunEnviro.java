package com.pixelrifts.enviro.game.main;

import com.pixelrifts.enviro.engine.base.ColouredQuad;
import com.pixelrifts.enviro.engine.quads.QuadRenderer;
import com.pixelrifts.enviro.engine.util.Colour;

public class RunEnviro {
	public static void main(String[] args) {
		Display.init();
		Display.createWindow(1080, 720, "Hello There!");
		
		QuadRenderer renderer = new QuadRenderer();
		ColouredQuad quad = new ColouredQuad(-0.7f, -0.12f, 0.3f, 1, Colour.Yellow());
		
		while (!Display.shouldClose()) {
			renderer.submit(quad);
			Display.update(renderer);
		}
		
		Display.destroyWindow();
		Display.terminate();
	}
}
