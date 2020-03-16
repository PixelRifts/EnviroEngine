package com.pixelrifts.enviro.engine.vertex;

import org.joml.Vector3f;

public class Vertex3D {
	protected Vector3f position;
	
	public Vertex3D(Vector3f position) {
		this.position = position;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float[] getOrderedPositions() {
		return new float[] { position.x, position.y, position.z };
	}
}
