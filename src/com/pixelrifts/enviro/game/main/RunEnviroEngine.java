package com.pixelrifts.enviro.game.main;

import org.joml.Vector2f;

import com.pixelrifts.enviro.engine.base.Display;
import com.pixelrifts.enviro.engine.math.Colour;
import com.pixelrifts.enviro.engine.math.Transform2D;
import com.pixelrifts.enviro.engine.rendering.Texture;
import com.pixelrifts.enviro.engine.sprites.Sprite;
import com.pixelrifts.enviro.engine.sprites.SpriteRenderer;

public class RunEnviroEngine {
	public static void main(String[] args) {
		Display.init();
		Display.createWindow(1080, 720, "Hello There!");
		Display.setBackground(Colour.DarkGrey());

		SpriteRenderer spriterenderer = new SpriteRenderer();
		Sprite s = new Sprite(0, 0, 40, 40, new Texture("./res/textures/smiley.png"), new Transform2D());

		while (!Display.shouldClose()) {
			s.getTransform().scale(new Vector2f(1).mul(Display.getDelta()));
			spriterenderer.submit(s);
			Display.update(spriterenderer);
		}

		Display.destroyWindow();
		Display.terminate();
	}
}
