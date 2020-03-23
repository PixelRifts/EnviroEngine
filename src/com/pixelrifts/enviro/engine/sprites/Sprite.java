package com.pixelrifts.enviro.engine.sprites;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import com.pixelrifts.enviro.engine.base.Loader;
import com.pixelrifts.enviro.engine.base.TexturedMesh;
import com.pixelrifts.enviro.engine.events.EventData;
import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.interfaces.IRenderable;
import com.pixelrifts.enviro.engine.math.Rectangle;
import com.pixelrifts.enviro.engine.math.Transform2D;
import com.pixelrifts.enviro.engine.rendering.Texture;

public class Sprite implements IBindable, IRenderable {
	protected TexturedMesh mesh;
	protected float width;
	protected float height;
	protected Transform2D transform;

	public Sprite(Rectangle bounds, Texture t, Transform2D transform) {
		this.width = bounds.getWidth();
		this.height = bounds.getHeight();

		this.mesh = Loader.loadSpriteToVAO(bounds.getX(), bounds.getY(), width, height, t);
		this.transform = transform;
	}

	public void translate(Vector2f d) {
		transform.translate(d);
	}

	public void rotate(float r) {
		transform.rotate(r);
	}

	public void scale(Vector2f s) {
		transform.scale(s);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
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
	public void OnCollision(EventData data) {
		System.out.println("HI");
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
