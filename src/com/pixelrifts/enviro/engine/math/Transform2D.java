package com.pixelrifts.enviro.engine.math;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform2D {
	private Vector2f translation;
	private float rotation;
	private Vector2f scale;
	
	private Matrix4f transformationMatrix;
	
	public static final Vector3f Z = new Vector3f(0, 0, 1); 
	
	public Transform2D() {
		this(new Vector2f(), 0);
	}
	
	public Transform2D(Vector2f translation, float rotation) {
		this(translation, rotation, new Vector2f(1, 1));
	}

	public Transform2D(Vector2f translation, float rotation, Vector2f scale) {
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
		transformationMatrix = new Matrix4f();
		calculateTransformationMatrix();
	}
	
	private void calculateTransformationMatrix() {
		transformationMatrix.identity();
		transformationMatrix.translate(translation.x, translation.y, 0);
		transformationMatrix.rotate(rotation, Z);
		transformationMatrix.scale(scale.x, scale.y, 0);
	}

	public void translate(Vector2f d) {
		translation.add(d);
		calculateTransformationMatrix();
	}
	
	public void rotate(float r) {
		rotation += r;
		calculateTransformationMatrix();
	}
	
	public void scale(Vector2f s) {
		scale.add(s);
		calculateTransformationMatrix();
	}

	public Vector2f getTranslation() {
		return translation;
	}

	public void setTranslation(Vector2f translation) {
		this.translation = translation;
		calculateTransformationMatrix();
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		calculateTransformationMatrix();
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
		calculateTransformationMatrix();
	}
	
	public Matrix4f toMatrix() {
		return transformationMatrix;
	}
}
