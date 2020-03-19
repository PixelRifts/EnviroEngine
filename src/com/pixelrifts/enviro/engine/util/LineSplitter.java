package com.pixelrifts.enviro.engine.util;

import org.joml.Vector3f;

public class LineSplitter
{
	private int pointer;
	private String[] data;

	public LineSplitter(final String string)
	{
		this.pointer = 0;
		this.data = string.split(";");
	}

	public LineSplitter(final String string, final String separator)
	{
		this.pointer = 0;
		this.data = string.split(separator);
	}

	public String getNextString()
	{
		return this.data[this.pointer++];
	}

	public int getNextInt()
	{
		return Integer.parseInt(this.data[this.pointer++]);
	}

	public long getNextLong()
	{
		return Long.parseLong(this.data[this.pointer++]);
	}

	public float getNextFloat()
	{
		return Float.parseFloat(this.data[this.pointer++]);
	}

	public Vector3f getNextVector()
	{
		final float x = this.getNextFloat();
		final float y = this.getNextFloat();
		final float z = this.getNextFloat();
		return new Vector3f(x, y, z);
	}

	public double getNextDouble()
	{
		return Double.parseDouble(this.data[this.pointer++]);
	}

	public boolean getNextBool()
	{
		return FileUtils.readBoolean(this.data[this.pointer++]);
	}

	public boolean hasMoreValues()
	{
		return this.pointer < this.data.length;
	}
}
