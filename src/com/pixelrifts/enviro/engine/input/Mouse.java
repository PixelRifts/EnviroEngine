package com.pixelrifts.enviro.engine.input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Mouse extends GLFWMouseButtonCallback  {
	public static final Mouse instance = new Mouse();

	private boolean[] buttons;

	private Mouse() {
		buttons = new boolean[10];
	}

	@Override
	public void invoke(long window, int button, int action, int mods) {
		buttons[button] = action == 1;
	}
	
	public boolean isButtonDown(int button) {
		return buttons[button];
	}
}
