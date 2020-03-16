package com.pixelrifts.enviro.game.base;

import static org.lwjgl.opengl.GL11.*;

import com.pixelrifts.enviro.engine.batch.Batch;
import com.pixelrifts.enviro.engine.interfaces.IRenderer;
import com.pixelrifts.enviro.engine.rendering.Shader;

public class StaticRenderer implements IRenderer<Entity> {
	private StaticShader shader;
	private Batch<Entity> batch;

	public StaticRenderer() {
		shader = new StaticShader();
		batch = new Batch<>();
	}

	@Override
	public void prepare() {
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_DEPTH_TEST);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void render() {
		shader.bind();
		shader.loadForBatch();
		batch.foreach(this::process);
		shader.unbind();
	}

	private void process(Entity e) {
		shader.load();
		e.bind();
		e.render();
		e.unbind();
	}

	@Override
	public Shader getShader() {
		return shader;
	}

	@Override
	public void submit(Entity obj) {
		batch.add(obj);
	}

	@Override
	public Batch<Entity> getBatch() {
		return batch;
	}
}
