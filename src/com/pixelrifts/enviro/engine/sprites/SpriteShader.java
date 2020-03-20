package com.pixelrifts.enviro.engine.sprites;

import org.joml.Matrix4f;

import com.pixelrifts.enviro.engine.rendering.Shader;

public class SpriteShader extends Shader {
	public SpriteShader() {
		super("sprite");
	}

	@Override
	public void loadForBatch(Object... objs) {
		super.loadMatrix4f("u_ProjectionMatrix", (Matrix4f) objs[0]);
		super.loadMatrix4f("u_ViewMatrix", (Matrix4f) objs[1]);
	}

	@Override
	public void load(Object... objs) {
		super.loadMatrix4f("u_ModelMatrix", (Matrix4f) objs[0]);
		super.loadInt("u_TextureSampler", (int) objs[1]);
	}
}
