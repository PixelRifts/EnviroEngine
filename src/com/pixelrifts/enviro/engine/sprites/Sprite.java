package com.pixelrifts.enviro.engine.sprites;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import com.pixelrifts.enviro.engine.base.Loader;
import com.pixelrifts.enviro.engine.base.TexturedMesh;
import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.interfaces.IRenderable;
import com.pixelrifts.enviro.engine.math.Transform2D;
import com.pixelrifts.enviro.engine.rendering.Texture;

public class Sprite implements IBindable, IRenderable {
	private TexturedMesh mesh;
	
	private float width;
	private float height;
	
	private Transform2D transform;
	
	public Sprite(float x, float y, float width, float height, Texture t, Transform2D transform) {
		this.width = width;
		this.height = height;
		
		this.mesh = Loader.loadSpriteToVAO(x, y, width, height, t);
		this.transform = transform;
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Transform2D getTransform() {
		return transform;
	}

	public void setTransform(Transform2D transform) {
		this.transform = transform;
	}
	
	public int getTexUnit() {
		return 2;
	}

	@Override
	public void render() {
		GL15.glDrawElements(GL11.GL_TRIANGLES, mesh.getMesh().vertexCount, GL11.GL_UNSIGNED_INT, 0);
	}

	@Override
	public void bind() {
		mesh.bind();
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
	}

	@Override
	public void unbind() {
		GL30.glDisableVertexAttribArray(1);
		GL30.glDisableVertexAttribArray(0);
		mesh.unbind();
	}
}
