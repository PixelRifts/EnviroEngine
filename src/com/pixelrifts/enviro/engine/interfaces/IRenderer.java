package com.pixelrifts.enviro.engine.interfaces;

import com.pixelrifts.enviro.engine.batch.Batch;
import com.pixelrifts.enviro.engine.rendering.Shader;

public interface IRenderer<T extends IRenderable> {
	public void prepare();
	public void render();
	
	default void clear() {
		getBatch().clear();
	}
	
	public Batch<T> getBatch();
	public Shader getShader();
	public void submit(T obj);
}
