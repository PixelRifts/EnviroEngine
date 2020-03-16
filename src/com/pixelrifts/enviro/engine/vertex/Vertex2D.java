package com.pixelrifts.enviro.engine.vertex;

import org.joml.Vector2f;

public class Vertex2D {
	protected Vector2f position;
	
	public Vertex2D(Vector2f position) {
		this.position = position;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public float[] getOrderedPositions() {
		return new float[] { position.x, position.y };
	}
}
