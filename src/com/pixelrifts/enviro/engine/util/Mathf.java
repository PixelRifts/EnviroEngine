package com.pixelrifts.enviro.engine.util;

public class Mathf {
	public static float Clamp(float val, float min, float max) {
		if (val < min) return min;
		if (val > max) return max;
		return val;
	}
}
