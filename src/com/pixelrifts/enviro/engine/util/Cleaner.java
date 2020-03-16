package com.pixelrifts.enviro.engine.util;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import com.pixelrifts.enviro.engine.rendering.Shader;
import com.pixelrifts.enviro.engine.rendering.Texture;

public class Cleaner {
	private static List<Integer> vaos;
	private static List<Integer> vbos;
	private static List<Texture> textures;
	private static List<Shader> shaders;
	
	static {
		vaos = new ArrayList<>();
		vbos = new ArrayList<>();
		textures = new ArrayList<>();
		shaders = new ArrayList<>();
	}
	
	public static void AddVAO(int vaoID) {
		vaos.add(vaoID);
	}

	public static void AddVBO(int vboID) {
		vbos.add(vboID);
	}
	
	public static void AddTexture(Texture texture) {
		textures.add(texture);
	}
	
	public static void AddShader(Shader shader) {
		shaders.add(shader);
	}
	
	public static void CleanUp() {
		for (int vao : vaos) GL30.glDeleteVertexArrays(vao);
		for (int vbo : vbos) GL15.glDeleteBuffers(vbo);
		for (Texture tex : textures) tex.cleanUp();
		for (Shader shader : shaders) shader.cleanUp();
	}
}
