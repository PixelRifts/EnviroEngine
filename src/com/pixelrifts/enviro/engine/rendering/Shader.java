package com.pixelrifts.enviro.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.math.Colour;
import com.pixelrifts.enviro.engine.util.Cleaner;

public abstract class Shader implements IBindable {
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private static FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);

	public Shader(String file) {
		vertexShaderID = loadShader("./res/shaders/" + file + ".vs", GL_VERTEX_SHADER);
		fragmentShaderID = loadShader("./res/shaders/" + file + ".fs", GL_FRAGMENT_SHADER);
		programID = glCreateProgram();
		glAttachShader(programID, vertexShaderID);
		glAttachShader(programID, fragmentShaderID);
		glLinkProgram(programID);
		glValidateProgram(programID);
		Cleaner.AddShader(this);
	}

	protected void loadInt(String var, int value) {
		int location = getUniformLocation(var);
		glUniform1i(location, value);
	}

	protected void loadFloat(String var, float value) {
		int location = getUniformLocation(var);
		glUniform1f(location, value);
	}

	protected void load2DVector(String var, Vector2f vector) {
		int location = getUniformLocation(var);
		glUniform2f(location, vector.x, vector.y);
	}

	protected void load3DVector(String var, Vector3f vector) {
		int location = getUniformLocation(var);
		glUniform3f(location, vector.x, vector.y, vector.z);
	}

	protected void load4DVector(String var, Vector4f vector) {
		int location = getUniformLocation(var);
		glUniform4f(location, vector.x, vector.y, vector.z, vector.w);
	}

	protected void loadColour(String var, Colour c) {
		int location = getUniformLocation(var);
		glUniform4f(location, c.r, c.g, c.b, c.a);
	}

	protected void loadBoolean(String var, boolean bool) {
		int location = getUniformLocation(var);
		glUniform1f(location, bool ? 1 : 0);
	}

	protected void loadMatrix4f(String var, Matrix4f mat) {
		int location = getUniformLocation(var);
		mat.get(matBuffer);
		glUniformMatrix4fv(location, false, matBuffer);
	}

	protected int getUniformLocation(String name) {
		return glGetUniformLocation(programID, name);
	}

	@Override
	public void bind() {
		glUseProgram(programID);
	}

	@Override
	public void unbind() {
		glUseProgram(0);
	}

	public void cleanUp() {
		unbind();
		glDetachShader(programID, vertexShaderID);
		glDetachShader(programID, fragmentShaderID);
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);
		glDeleteProgram(programID);
	}

	public abstract void loadForBatch(Object... objs);

	public abstract void load(Object... objs);

	private static int loadShader(String file, int type) {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(file)))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append("\n");
			}
		} catch (IOException e) {

		}
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, builder.toString());
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) != GL_TRUE) {
			System.err.println(glGetShaderInfoLog(shaderID));
			System.exit(-1);
		}
		return shaderID;
	}
}
