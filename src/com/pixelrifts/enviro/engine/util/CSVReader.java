package com.pixelrifts.enviro.engine.util;

import java.io.BufferedReader;
import java.io.IOException;

import org.joml.Vector3f;

public class CSVReader {
	private final String SEPARATOR;
	private BufferedReader reader;
	private LineSplitter splitter;

	public CSVReader(final PixelFile file, boolean extern) {
		if (extern)
			try {
				this.reader = file.getFileReader();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			this.reader = file.getReader();
		this.SEPARATOR = ";";
	}

	public String nextLine() {
		String line = null;
		try {
			line = this.reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (line != null) {
			this.splitter = new LineSplitter(line, this.SEPARATOR);
			return line;
		}
		return null;
	}

	public String getNextString() {
		return this.splitter.getNextString();
	}

	public String getNextLabelString() {
		this.getNextString();
		return this.getNextString();
	}

	public float getNextLabelFloat() {
		this.getNextString();
		return this.getNextFloat();
	}

	public int getNextInt() {
		return this.splitter.getNextInt();
	}

	public long getNextLong() {
		return this.splitter.getNextLong();
	}

	public float getNextFloat() {
		return this.splitter.getNextFloat();
	}

	public int[] getNextLabelIntArray() {
		this.getNextString();
		final int count = this.getNextInt();
		final int[] array = new int[count];
		for (int i = 0; i < count; ++i) {
			array[i] = this.getNextInt();
		}
		return array;
	}

	public float[] getNextLabelFloatArray() {
		this.getNextString();
		final int count = this.getNextInt();
		final float[] array = new float[count];
		for (int i = 0; i < count; ++i) {
			array[i] = this.getNextFloat();
		}
		return array;
	}

	public Vector3f getNextLabelVector() {
		this.getNextString();
		return this.getNextVector();
	}

	public Vector3f getNextVector() {
		final float x = this.splitter.getNextFloat();
		final float y = this.splitter.getNextFloat();
		final float z = this.splitter.getNextFloat();
		return new Vector3f(x, y, z);
	}

	public boolean isEndOfLine() {
		return !this.splitter.hasMoreValues();
	}

	public boolean getNextBool() {
		return this.splitter.getNextBool();
	}

	public boolean getNextLabelBool() {
		this.getNextString();
		return this.getNextBool();
	}

	public int getNextLabelInt() {
		this.getNextString();
		return this.getNextInt();
	}

	public void close() {
		FileUtils.closeBufferedReader(this.reader);
	}
}
