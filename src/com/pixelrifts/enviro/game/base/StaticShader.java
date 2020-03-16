package com.pixelrifts.enviro.game.base;

import com.pixelrifts.enviro.engine.rendering.Shader;

public class StaticShader extends Shader {
	public StaticShader() {
		super("static");
	}

	@Override
	public void loadForBatch(Object... objs) {
	}

	@Override
	public void load(Object... objs) {
	}
}
