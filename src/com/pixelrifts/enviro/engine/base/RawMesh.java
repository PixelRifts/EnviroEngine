package com.pixelrifts.enviro.engine.base;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import com.pixelrifts.enviro.engine.interfaces.IBindable;

public class RawMesh implements IBindable {
	public final int vaoID;
	public final int vertexCount;
	public final int indicesID;
	
	public RawMesh(int vaoID, int vertexCount, int indicesID) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.indicesID = indicesID;
	}

	@Override
	public void bind() {
		GL30.glBindVertexArray(vaoID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesID);
	}

	@Override
	public void unbind() {
		GL30.glBindVertexArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
}
