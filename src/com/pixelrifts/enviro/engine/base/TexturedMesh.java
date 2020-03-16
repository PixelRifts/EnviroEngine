package com.pixelrifts.enviro.engine.base;

import com.pixelrifts.enviro.engine.interfaces.IBindable;

public class TexturedMesh implements IBindable {
	private RawMesh mesh;
	private MeshTexture texture;

	public TexturedMesh(RawMesh mesh, MeshTexture texture) {
		this.mesh = mesh;
		this.texture = texture;
	}

	public RawMesh getMesh() {
		return mesh;
	}

	public MeshTexture getTexture() {
		return texture;
	}

	@Override
	public void bind() {
		texture.bind();
		mesh.bind();
	}

	@Override
	public void unbind() {
		mesh.unbind();
		texture.unbind();
	}
}
