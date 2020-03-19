package com.pixelrifts.enviro.engine.base;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

import com.mokiat.data.front.parser.IMTLParser;
import com.mokiat.data.front.parser.IOBJParser;
import com.mokiat.data.front.parser.MTLColor;
import com.mokiat.data.front.parser.MTLLibrary;
import com.mokiat.data.front.parser.MTLMaterial;
import com.mokiat.data.front.parser.MTLParser;
import com.mokiat.data.front.parser.OBJDataReference;
import com.mokiat.data.front.parser.OBJFace;
import com.mokiat.data.front.parser.OBJMesh;
import com.mokiat.data.front.parser.OBJModel;
import com.mokiat.data.front.parser.OBJNormal;
import com.mokiat.data.front.parser.OBJObject;
import com.mokiat.data.front.parser.OBJParser;
import com.mokiat.data.front.parser.OBJVertex;
import com.pixelrifts.enviro.engine.data.ColouredMeshData;
import com.pixelrifts.enviro.engine.util.Cleaner;
import com.pixelrifts.enviro.engine.util.PixelFile;

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

	public static ColouredMeshData loadColouredMeshFromFile(PixelFile file) {
		MTLLibrary library = null;
		List<Integer> indicesList = new ArrayList<>();

		int[] indices = null;
		float[] vertices = null;
		float[] colours = null;
		float[] normals = null;

		try (InputStream in = new FileInputStream(file.getPath())) {
			final IOBJParser parser = new OBJParser();
			final OBJModel model = parser.parse(in);
			for (String lib : model.getMaterialLibraries()) {
				try (InputStream mtlIn = new FileInputStream("./res/materials/" + lib)) {
					final IMTLParser mtlParser = new MTLParser();
					library = mtlParser.parse(mtlIn);
				}
			}

			vertices = new float[model.getVertices().size() * 3];
			colours = new float[model.getVertices().size() * 4];
			normals = new float[model.getNormals().size() * 3];

			for (OBJObject object : model.getObjects()) {
				// Objects contain Mesh <-> Name
				for (OBJMesh mesh : object.getMeshes()) {
					// Meshes contain Material <-> Face
					for (OBJFace face : mesh.getFaces()) {
						// Faces Contain 3 Vertices
						for (OBJDataReference reference : face.getReferences()) {
							processColouredVertex(model, reference, mesh, library, indicesList, colours, normals);
						}
					}
				}
			}
			
			int currentVertexPointer = 0;
			for (OBJVertex vertex : model.getVertices()) {
				vertices[currentVertexPointer++] = vertex.x;
				vertices[currentVertexPointer++] = vertex.y;
				vertices[currentVertexPointer++] = vertex.z;
			}
			
			indices = new int[indicesList.size()];
			currentVertexPointer = 0;
			for (int index : indicesList) {
				indices[currentVertexPointer++] = index;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int vaoID = createVAO();
		storeDataInAttribute(0, 3, vertices);
		storeDataInAttribute(1, 4, colours);
		storeDataInAttribute(2, 3, normals);
		int indicesID = createIBO(indices);
		
		return new ColouredMeshData(new RawMesh(vaoID, indices.length, indicesID), colours);
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

	private static void processColouredVertex(OBJModel model, OBJDataReference reference, OBJMesh mesh,
			MTLLibrary materials, List<Integer> indices, float[] colours, float[] normals) {
		int currentVertexPointer = reference.vertexIndex;
		indices.add(currentVertexPointer);
		OBJNormal currentNormal = model.getNormal(reference);
		normals[currentVertexPointer * 3 + 0] = currentNormal.x;
		normals[currentVertexPointer * 3 + 1] = currentNormal.y;
		normals[currentVertexPointer * 3 + 2] = currentNormal.z;
		MTLMaterial currentMaterial = materials.getMaterial(mesh.getMaterialName());
		MTLColor vertexColour = currentMaterial.getAmbientColor();
		colours[currentVertexPointer * 4 + 0] = vertexColour.r;
		colours[currentVertexPointer * 4 + 1] = vertexColour.g;
		colours[currentVertexPointer * 4 + 2] = vertexColour.b;
		colours[currentVertexPointer * 4 + 3] = 1;
	}
}
