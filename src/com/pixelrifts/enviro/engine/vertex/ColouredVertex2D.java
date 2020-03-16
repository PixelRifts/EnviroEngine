package com.pixelrifts.enviro.engine.vertex;

import org.joml.Vector2f;

import com.pixelrifts.enviro.engine.util.Colour;

public class ColouredVertex2D {
	private Vector2f position;
	private Colour colour;

	public ColouredVertex2D(Vector2f position, Colour colour) {
		this.position = position;
		this.colour = colour;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}
	
	public float[] getOrderedPositions() {
		return new float[] { position.x, position.y };
	}

	public float[] getOrderedColours() {
		return new float[] { colour.r, colour.g, colour.b, colour.a };
	}
}
