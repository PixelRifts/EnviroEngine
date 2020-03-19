package com.pixelrifts.enviro.engine.quads;

import static org.lwjgl.opengl.GL11.*;

import com.pixelrifts.enviro.engine.batch.Batch;
import com.pixelrifts.enviro.engine.interfaces.IRenderer;
import com.pixelrifts.enviro.engine.rendering.Shader;

public class QuadRenderer implements IRenderer<Quad> {
	private QuadShader shader;
	private Batch<Quad> batch;
	
	public QuadRenderer() {
		shader = new QuadShader();
		batch = new Batch<>();
	}
	
	@Override
	public void prepare() {
		glDisable(GL_CULL_FACE);
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void render() {
		shader.bind();
		shader.loadForBatch();
		batch.foreach(this::process);
		shader.unbind();
	}
	
	private void process(Quad q) {
		q.bind();
		if (q instanceof ColouredQuad) shader.load(0);
		else shader.load(1);
		q.render();
		q.unbind();
	}

	@Override
	public Batch<Quad> getBatch() {
		return batch;
	}

	@Override
	public Shader getShader() {
		return shader;
	}

	@Override
	public void submit(Quad obj) {
		batch.add(obj);
	}
}
