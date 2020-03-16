package com.pixelrifts.enviro.game.base;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import com.pixelrifts.enviro.engine.base.TexturedMesh;
import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.interfaces.IRenderable;

public class Entity implements IRenderable, IBindable {
	private TexturedMesh mesh;
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	
	public Entity(TexturedMesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.mesh = mesh;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void translate(Vector3f d) {
		position.add(d);
	}
	
	public void rotate(Vector3f r) {
		rotation.add(r);
	}
	
	public void scale(Vector3f s) {
		scale.add(s);
	}
	
	public TexturedMesh getMesh() {
		return mesh;
	}

	public void setMesh(TexturedMesh mesh) {
		this.mesh = mesh;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	@Override
	public void render() {
		GL15.glDrawElements(GL11.GL_TRIANGLES, mesh.getMesh().vertexCount, GL11.GL_UNSIGNED_INT, 0);
	}

	@Override
	public void bind() {
		mesh.bind();
	}

	@Override
	public void unbind() {
		mesh.unbind();
	}
}
