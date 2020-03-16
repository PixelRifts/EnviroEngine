package com.pixelrifts.enviro.engine.base;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.pixelrifts.enviro.engine.util.Cleaner;

public class Loader {
	public static RawMesh loadColouredQuadToVAO(float[] vertices, float[] colours, int[] indices) {
		int vaoID = createVAO();
		storeDataInAttribute(0, 2, vertices);
		storeDataInAttribute(1, 4, colours);
		unbindVAO();
		int iboID = createIBO(indices);
		return new RawMesh(vaoID, indices.length, iboID);
	}

	private static int createVAO() {
		int vaoID = glGenVertexArrays();
		Cleaner.AddVAO(vaoID);
		glBindVertexArray(vaoID);
		return vaoID;
	}

	private static void storeDataInAttribute(int attrib, int dims, float[] data) {
		int vboID = glGenBuffers();
		Cleaner.AddVBO(vboID);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(data), GL_STATIC_DRAW);
		glVertexAttribPointer(attrib, dims, GL_FLOAT, false, 0, 0);
	}

	private static void unbindVAO() {
		glBindVertexArray(0);
	}
	
	private static int createIBO(int[] data) {
		int vboID = glGenBuffers();
		Cleaner.AddVBO(vboID);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(data), GL_STATIC_DRAW);
		return vboID;
	}
	
	private static FloatBuffer createBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	private static IntBuffer createBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
