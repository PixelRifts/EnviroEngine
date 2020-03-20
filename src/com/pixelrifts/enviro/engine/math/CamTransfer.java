package com.pixelrifts.enviro.engine.math;

import org.joml.Vector3f;

public class CamTransfer {
	public final Vector3f translation;
	public final float rotation;

	public CamTransfer(Vector3f translation, float rotation) {
		this.translation = translation;
		this.rotation = rotation;
	}
}
