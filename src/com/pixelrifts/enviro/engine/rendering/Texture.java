package com.pixelrifts.enviro.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.util.Cleaner;
import com.pixelrifts.enviro.engine.util.TextureUtils;

public class Texture implements IBindable
{
	private int width, height;
	private int texture;
	private int texturebank;
	private String path;

	public Texture(String path)
	{
		stbi_set_flip_vertically_on_load(false);
		this.path = path;
		load(path);
		Cleaner.AddTexture(this);
	}

	private void load(String path)
	{
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);

		ByteBuffer data = stbi_load(path, w, h, comp, 4);
		texture = glGenTextures();

		this.width = w.get();
		this.height = h.get();

		glBindTexture(GL_TEXTURE_2D, texture);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		stbi_image_free(data);
	}

	@Override
	public void bind()
	{
		texturebank = TextureUtils.GetActiveBank();
		glBindTexture(GL_TEXTURE_2D, texture);
	}

	@Override
	public void unbind()
	{
		glBindTexture(GL_TEXTURE_2D, 0);
		texturebank = -1;
	}

	public void cleanUp()
	{
		unbind();
		glDeleteTextures(texture);
	}

	public int getTextureID()
	{
		return texture;
	}

	public String getPath()
	{
		return path;
	}

	public int getTexturebank()
	{
		return texturebank;
	}

	@Override
	public String toString()
	{
		return "Texture [width=" + width + ", height=" + height + ", texture=" + texture + ", texturebank=" + texturebank + ", path=" + path + "]";
	}
}