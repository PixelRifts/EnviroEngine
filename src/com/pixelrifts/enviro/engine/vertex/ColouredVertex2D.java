package com.pixelrifts.enviro.engine.vertex;

import org.joml.Vector2f;

import com.pixelrifts.enviro.engine.math.Colour;

public class ColouredVertex2D extends Vertex2D {
	private Colour colour;

	public ColouredVertex2D(Vector2f position, Colour colour) {
		super(position);
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public float[] getOrderedColours() {
		return new float[] { colour.r, colour.g, colour.b, colour.a };
	}
}
