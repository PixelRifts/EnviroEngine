package com.pixelrifts.enviro.engine.base;

import org.lwjgl.opengl.GL20;

import com.pixelrifts.enviro.engine.interfaces.IBindable;

public class MeshTexture implements IBindable {
	public final int textureID;

	public MeshTexture(int textureID) {
		this.textureID = textureID;
	}

	public int getTextureID() {
		return textureID;
	}

	@Override
	public void bind() {
		GL20.glBindTexture(GL20.GL_TEXTURE_2D, textureID);
	}

	@Override
	public void unbind() {
		GL20.glBindTexture(GL20.GL_TEXTURE_2D, 0);
	}
}
