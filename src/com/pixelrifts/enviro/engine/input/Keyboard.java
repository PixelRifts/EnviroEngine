package com.pixelrifts.enviro.engine.input;

import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {
	public static final Keyboard instance = new Keyboard();
	
	private boolean[] keys;
	
	public Keyboard() {
		keys = new boolean[10000];
	}
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action == 1 || action == 2; 
	}
	
	public boolean isKeyDown(int key) {
		return keys[key];
	}
}
