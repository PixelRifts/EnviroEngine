package com.pixelrifts.enviro.engine.vertex;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class TexturedVertex3D extends Vertex3D {
	private Vector2f uv;

	public TexturedVertex3D(Vector3f position, Vector2f uv) {
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
