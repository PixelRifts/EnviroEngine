package com.pixelrifts.enviro.engine.rendering;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.pixelrifts.enviro.engine.base.Display;
import com.pixelrifts.enviro.engine.input.Keyboard;
import com.pixelrifts.enviro.engine.interfaces.ICamMover;
import com.pixelrifts.enviro.engine.math.CamTransfer;
import com.pixelrifts.enviro.engine.math.Transform2D;

public class Camera {
	public static final Camera instance = new Camera(new Vector2f(), 0);
	
	private Vector2f position;
	private float rotation;
	private float left;
	private float right;
	private float top;
	private float bottom;
	private float nearPlane;
	private float farPlane;
	
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	
	private final ICamMover basicMover;
	public ICamMover mover;
	
	private static final Keyboard keyboard = Keyboard.instance;
	private static final float BASIC_SPEED = 0.2f;

	private static final Matrix4f UIProjection = new Matrix4f();
	
	static {
		UIProjection.identity();
		UIProjection.ortho(0, Display.displaywidth, 0, Display.displayheight, 0, 1000);
	}
	
	private Camera(Vector2f position, float rotation) {
		this.position = position;
		this.rotation = rotation;
		projectionMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
		setOrthoBounds(-(Display.displaywidth / 2), Display.displaywidth / 2, -(Display.displayheight / 2), Display.displayheight / 2, 0, 1000);
		calculateProjectionMatrix();
		calculateViewMatrix();
		basicMover = this::basicMove;
		this.mover = basicMover;
	}
	
	public void applyTransfer(CamTransfer transfer) {
		this.position.add(transfer.translation);
		this.rotation += transfer.rotation;
		calculateViewMatrix();
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public CamTransfer basicMove() {
		Vector2f position = new Vector2f();
		float rotation = 0;
		if (keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
			position.y += BASIC_SPEED;
		}
		if (keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
			position.y -= BASIC_SPEED;
		}
		if (keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
			position.x += BASIC_SPEED;
		}
		if (keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
			position.x -= BASIC_SPEED;
		}
		return new CamTransfer(position, rotation);
	}
	
	public void setMovement(ICamMover mover) {
		this.mover = mover;
	}
	
	public void setOrthoBounds(float left, float right, float bottom, float top, float nearPlane, float farPlane) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.nearPlane = nearPlane;
		this.farPlane = farPlane;
		calculateProjectionMatrix();
	}
	
	public void calculateProjectionMatrix() {
		projectionMatrix.identity();
		projectionMatrix.ortho(left, right, bottom, top, nearPlane, farPlane);
	}

	public void calculateViewMatrix() {
		viewMatrix.identity();
		Vector3f negativePos = new Vector3f(position.x, position.y, 0).mul(-1);
		viewMatrix.translate(negativePos);
		viewMatrix.rotate(-rotation, Transform2D.Z);
	}
	
	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	public static Matrix4f getUIProjection() {
		return UIProjection;
	}
}
