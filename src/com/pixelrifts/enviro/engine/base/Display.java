package com.pixelrifts.enviro.engine.base;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.pixelrifts.enviro.engine.input.Cursor;
import com.pixelrifts.enviro.engine.input.Keyboard;
import com.pixelrifts.enviro.engine.input.Mouse;
import com.pixelrifts.enviro.engine.input.MouseWheel;
import com.pixelrifts.enviro.engine.interfaces.IRenderer;
import com.pixelrifts.enviro.engine.math.Colour;
import com.pixelrifts.enviro.engine.rendering.Camera;
import com.pixelrifts.enviro.engine.rendering.Texture;
import com.pixelrifts.enviro.engine.util.TextureUtils;

public class Display {
	public static Texture whiteTexture;
	public static int displaywidth, displayheight;

	private static long win;
	private static float last = 0, now = System.nanoTime(), delta = 0;
	private static boolean hasResized;
	private static GLFWWindowSizeCallback windowsizeCallback;

	public static void init() {
		hasResized = false;
		if (!glfwInit()) {
			System.err.println("Could not initialize GLFW");
			System.exit(-1);
		}
	}

	public static void createWindow(int width, int height, String name) {
		win = glfwCreateWindow(width, height, name, 0, 0);
		displaywidth = width;
		displayheight = height;
		glfwMakeContextCurrent(win);
		GL.createCapabilities();

		windowsizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				displaywidth = width;
				displayheight = height;
				hasResized = true;
			}
		};
		glfwSetWindowSizeCallback(win, windowsizeCallback);
		glfwSetMouseButtonCallback(win, Mouse.instance);
		glfwSetKeyCallback(win, Keyboard.instance);
		glfwSetScrollCallback(win, MouseWheel.instance);
		glfwSetCursorPosCallback(win, Cursor.instance);

		createWhiteTexture();
	}

	private static void createWhiteTexture() {
		whiteTexture = new Texture("./res/textures/White.png");
		TextureUtils.ActivateBank(0);
		whiteTexture.bind();
	}

	public static void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	public static boolean shouldClose() {
		return glfwWindowShouldClose(win);
	}

	public static void testResize() {
		if (hasResized) {
			GL11.glViewport(0, 0, displaywidth, displayheight);
			Camera.instance.calculateProjectionMatrix();
		}
	}

	public static void terminate() {
		glfwTerminate();
	}

	public static void update(IRenderer<?>... renderers) {
		clear();
		glfwPollEvents();
		testResize();
		hasResized = false;
		Camera.instance.applyTransfer(Camera.instance.mover.move());
		 
		for (IRenderer<?> renderer : renderers) {
			renderer.prepare();
			renderer.render();
			renderer.clear();
		}

		last = now;
		now = System.nanoTime();
		delta = now - last;
		glfwSwapBuffers(win);
	}

	public static void setBackground(float r, float g, float b, float a) {
		GL11.glClearColor(r, g, b, a);
	}

	public static void setBackground(Colour c) {
		GL11.glClearColor(c.r, c.g, c.b, c.a);
	}

	public static float getDelta() {
		return delta / 1000000000;
	}

	public static void destroyWindow() {
		windowsizeCallback.close();
		glfwDestroyWindow(win);
	}
}
