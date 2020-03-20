package com.pixelrifts.enviro.engine.input;

import org.lwjgl.glfw.GLFWScrollCallback;

public class MouseWheel extends GLFWScrollCallback {
	public static final MouseWheel instance = new MouseWheel();
	
	private float XScroll;
	private float YScroll;
	
	private MouseWheel() {
	}
	
	@Override
	public void invoke(long window, double xoffset, double yoffset) {
		this.XScroll = (float) xoffset;
		this.YScroll = (float) yoffset;
	}

	public float getXScroll() {
		return XScroll;
	}
	
	public float getYScroll() {
		return YScroll;
	}
}
