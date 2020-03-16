package com.pixelrifts.enviro.engine.vertex;

import org.joml.Vector2f;

public class TexturedVertex2D extends Vertex2D {
	private Vector2f uv;

	public TexturedVertex2D(Vector2f position, Vector2f uv) {
		super(position);
		this.uv = uv;
	}

	public Vector2f getUv() {
		return uv;
	}

	public void setUv(Vector2f uv) {
		this.uv = uv;
	}

	public float[] getOrderedUVs() {
		return new float[] { uv.x, uv.y };
	}
}
