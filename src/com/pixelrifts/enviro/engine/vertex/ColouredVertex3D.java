package com.pixelrifts.enviro.engine.vertex;

import org.joml.Vector3f;

import com.pixelrifts.enviro.engine.util.Colour;

public class ColouredVertex3D {
	private Vector3f position;
	private Colour colour;

	public ColouredVertex3D(Vector3f position, Colour colour) {
		this.position = position;
		this.colour = colour;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}
	

	public float[] getOrderedPositions() {
		return new float[] { position.x, position.y, position.z };
	}
}
