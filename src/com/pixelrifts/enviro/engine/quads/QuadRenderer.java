package com.pixelrifts.enviro.engine.quads;

import static org.lwjgl.opengl.GL11.*;

import com.pixelrifts.enviro.engine.base.ColouredQuad;
import com.pixelrifts.enviro.engine.batch.Batch;
import com.pixelrifts.enviro.engine.interfaces.IRenderer;
import com.pixelrifts.enviro.engine.rendering.Shader;

public class QuadRenderer implements IRenderer<ColouredQuad> {
	private QuadShader shader;
	private Batch<ColouredQuad> batch;
	
	public QuadRenderer() {
		shader = new QuadShader();
		batch = new Batch<>();
	}
	
	@Override
	public void prepare() {
		glDisable(GL_CULL_FACE);
		glDisable(GL_DEPTH_TEST);
	}

	@Override
	public void render() {
		shader.bind();
		shader.loadForBatch();
		batch.foreach(this::process);
		shader.unbind();
	}
	
	private void process(ColouredQuad q) {
		shader.load();
		q.bind();
		q.render();
		q.unbind();
	}

	@Override
	public Batch<ColouredQuad> getBatch() {
		return batch;
	}

	@Override
	public Shader getShader() {
		return shader;
	}

	@Override
	public void submit(ColouredQuad obj) {
		batch.add(obj);
	}
}
