package com.pixelrifts.enviro.engine.util;

import org.lwjgl.opengl.GL15;

public class TextureUtils {
	private static int currentBank;
	
	public static void ActivateBank(int textureBank) {
		GL15.glActiveTexture(GL15.GL_TEXTURE0 + textureBank);
		currentBank = textureBank; 
	}
	
	public static int GetActiveBank() {
		return currentBank;
	}
}
