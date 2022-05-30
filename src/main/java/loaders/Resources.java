package loaders;

import java.io.InputStream;
import java.util.Objects;

public class Resources
{
	/**
	 Gets the file path of a resource in the resources folder
	 * @param path A file within the main/resources directory
	 * @return The path to that file
	 */
	public static String get(String path)
	{
		return Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(path)).getFile();
	}

	public static InputStream getIS(String path)
	{
		return ClassLoader.getSystemClassLoader().getResourceAsStream(path);
	}
}
