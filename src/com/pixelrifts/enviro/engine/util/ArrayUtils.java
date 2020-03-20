package com.pixelrifts.enviro.engine.util;

import java.util.Arrays;

public class ArrayUtils {
	/**
	 * Appends the second array at the end of the first
	 * 
	 * @param a1 First array
	 * @param a2 Second Array
	 */
	public static float[] concat(float[] first, float[] second) {
	    float[] both = Arrays.copyOf(first, first.length + second.length);
	    System.arraycopy(second, 0, both, first.length, second.length);
	    return both;
	}
}
