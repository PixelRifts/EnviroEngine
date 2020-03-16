package com.pixelrifts.enviro.game.main;

import com.pixelrifts.enviro.engine.quads.ColouredQuad;
import com.pixelrifts.enviro.engine.quads.QuadRenderer;
import com.pixelrifts.enviro.engine.util.Colour;

public class RunEnviroEngine {
	public static void main(String[] args) {
		Display.init();
		Display.createWindow(1080, 720, "Hello There!");
		Display.setBackground(Colour.DarkGrey());
		
		QuadRenderer renderer = new QuadRenderer();
		ColouredQuad quad = new ColouredQuad(-0.5f, -0.5f, 1, 1, Colour.Green(), Colour.Red(), Colour.Blue(), Colour.Yellow());
//		TexturedQuad quad = new TexturedQuad(-0.5f, -0.5f, 1, 1, new Texture("./res/textures/Smiley.png"));
		
		while (!Display.shouldClose()) {
			renderer.submit(quad);
			Display.update(renderer);
		}
		
		Display.destroyWindow();
		Display.terminate();
	}
}
