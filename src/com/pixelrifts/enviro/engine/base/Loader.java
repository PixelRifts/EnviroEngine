package com.pixelrifts.enviro.engine.base;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.pixelrifts.enviro.engine.rendering.Texture;
import com.pixelrifts.enviro.engine.util.Cleaner;

public class Loader {
	private static final float[] whiteQuadColours = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	public static RawMesh loadQuadToVAO(float[] vertices, float[] colours, float[] uvs, int[] indices) {
		int vaoID = createVAO();

		storeDataInAttribute(0, 2, vertices);
		if (colours != null)
			storeDataInAttribute(1, 4, colours);
		else
			storeDataInAttribute(1, 4, whiteQuadColours);
		if (uvs != null)
			storeDataInAttribute(2, 2, uvs);

		unbindVAO();
		int iboID = createIBO(indices);
		return new RawMesh(vaoID, indices.length, iboID);
	}
	
	public static TexturedMesh loadSpriteToVAO(float x, float y, float width, float height, Texture t) {
		float[] vertices = {
				x, y + height,
				x, y,
				x + width, y,
				x + width, y + height
		};
		
		float[] uvs = {
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};
		
		int vaoID = createVAO();
		storeDataInAttribute(0, 2, vertices);
		storeDataInAttribute(1, 2, uvs);
		int indicesID = createIBO(new int[] {
				0, 1, 2,
				0, 2, 3
		});
		
		return new TexturedMesh(new RawMesh(vaoID, 6, indicesID), new MeshTexture(t));
	}

	// ----------------------------- HELPERS ------------------------------
	private static void unbindVAO() {
		glBindVertexArray(0);
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
