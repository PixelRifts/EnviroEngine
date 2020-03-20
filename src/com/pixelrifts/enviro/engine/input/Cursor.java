package com.pixelrifts.enviro.engine.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Cursor extends GLFWCursorPosCallback {
	public static final Cursor instance = new Cursor();
	
	private float x;
	private float y;
	
	private Cursor() {
	}
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		this.x = (float) xpos;
		this.y = (float) ypos;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
