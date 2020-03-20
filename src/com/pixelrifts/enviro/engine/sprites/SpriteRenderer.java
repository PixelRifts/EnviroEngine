package com.pixelrifts.enviro.engine.sprites;

import static org.lwjgl.opengl.GL11.*;

import com.pixelrifts.enviro.engine.batch.Batch;
import com.pixelrifts.enviro.engine.interfaces.IRenderer;
import com.pixelrifts.enviro.engine.rendering.Camera;
import com.pixelrifts.enviro.engine.rendering.Shader;

public class SpriteRenderer implements IRenderer<Sprite> {
	private Batch<Sprite> batch;
	private SpriteShader shader;
	private Camera camera;
	
	public SpriteRenderer() {
		batch = new Batch<>();
		shader = new SpriteShader();
		camera = Camera.instance;
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
		shader.loadForBatch(camera.getProjectionMatrix(), camera.getViewMatrix());
		batch.foreach(this::process);
		shader.unbind();
	}
	
	private void process(Sprite s) {
		shader.load(s.getTransform().toMatrix(), 2);
		s.bind();
		s.render();
		s.unbind();
	}

	@Override
	public void submit(Sprite obj) {
		batch.add(obj);
	}

	@Override
	public Batch<Sprite> getBatch() {
		return batch;
	}

	@Override
	public Shader getShader() {
		return shader;
	}
}
