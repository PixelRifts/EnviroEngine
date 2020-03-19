package com.pixelrifts.enviro.engine.base;

import org.lwjgl.opengl.GL20;

import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.rendering.Texture;
import com.pixelrifts.enviro.engine.util.TextureUtils;

public class MeshTexture implements IBindable {
	private Texture t;
	
	public MeshTexture(Texture t) {
		this.t = t;
	}

	@Override
	public void bind() {
		TextureUtils.ActivateBank(2);
		GL20.glBindTexture(GL20.GL_TEXTURE_2D, t.getTextureID());
	}

	@Override
	public void unbind() {
		GL20.glBindTexture(GL20.GL_TEXTURE_2D, 0);
	}
}
