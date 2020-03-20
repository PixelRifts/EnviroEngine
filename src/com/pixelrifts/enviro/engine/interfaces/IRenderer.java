package com.pixelrifts.enviro.engine.interfaces;

import com.pixelrifts.enviro.engine.batch.Batch;
import com.pixelrifts.enviro.engine.rendering.Shader;

public interface IRenderer<T extends IRenderable> {
	public void prepare();
	public void render();
	
	public void submit(T obj);
		
	public Batch<T> getBatch();
	public Shader getShader();
	
	default void clear() {
		getBatch().clear();
	}
}
