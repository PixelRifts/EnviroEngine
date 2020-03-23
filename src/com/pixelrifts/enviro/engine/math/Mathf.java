package com.pixelrifts.enviro.engine.math;

import org.joml.Vector2f;

public class Mathf {
	public static float Clamp(float val, float min, float max) {
		if (val < min) return min;
		if (val > max) return max;
		return val;
	}
	
	public static Vector2f Add(Vector2f a, Vector2f b) {
		Vector2f added = new Vector2f(a.x, a.y);
		added.add(b);
		return added;
	}
}
