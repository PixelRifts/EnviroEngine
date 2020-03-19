package com.pixelrifts.enviro.engine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtils {
	public static final String FILE_SEPARATOR = "/";
	public static final String SEPARATOR = ";";
	public static PixelFile RES_FOLDER;
	public static final int TRUE = 1;
	public static final int FALSE = 0;

	static {
		FileUtils.RES_FOLDER = new PixelFile("res");
	}

	public static boolean readBoolean(final String value) {
		final int boolValue = Integer.parseInt(value);
		return boolValue == 1;
	}

	public static int booleanToInt(final boolean bool) {
		return bool ? 1 : 0;
	}

	public static void closeBufferedReader(final BufferedReader reader) {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void renameFile(final File original, final File newName) {
		try {
			Files.move(original.toPath(), newName.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteFile(final File file) {
		if (!file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			File[] listFiles;
			for (int length = (listFiles = file.listFiles()).length, i = 0; i < length; ++i) {
				final File innerFile = listFiles[i];
				deleteFile(innerFile);
			}
		}
		file.delete();
	}

	public static File getRootFolder() {
		final File file = new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile());
		final File parent = file.getParentFile();
		return new File(parent.getAbsolutePath().replace("%20", " "));
	}
}
