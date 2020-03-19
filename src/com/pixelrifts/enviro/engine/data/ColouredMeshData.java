package com.pixelrifts.enviro.engine.data;

import com.pixelrifts.enviro.engine.base.RawMesh;

public class ColouredMeshData {
	public RawMesh mesh;
	public float[] colours;

	public ColouredMeshData(RawMesh mesh, float[] colours) {
		this.mesh = mesh;
		this.colours = colours;
	}
}
