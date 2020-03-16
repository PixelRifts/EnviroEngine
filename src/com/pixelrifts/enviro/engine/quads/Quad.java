package com.pixelrifts.enviro.engine.quads;

import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import com.pixelrifts.enviro.engine.base.RawMesh;
import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.interfaces.IRenderable;

public abstract class Quad implements IRenderable, IBindable {
	protected RawMesh mesh;
	protected float x;
	protected float y;
	protected float width;
	protected float height;

	protected static final int[] quadIndices = { 0, 1, 2, 3, 0, 2 };
	
	protected Quad(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void render() {
		GL15.glDrawElements(GL11.GL_TRIANGLES, mesh.vertexCount, GL11.GL_UNSIGNED_INT, 0);
	}

	@Override
	public void bind() {
		mesh.bind();
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
	}

	@Override
	public void unbind() {
		glDisableVertexAttribArray(2);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
		mesh.unbind();
	}
}
