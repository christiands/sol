package loaders;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class Text
{
	/**
	 Gets the contents of a file and puts them in a String
	 * @param path The path of the file
	 * @return The contents of the file, or a blank string if unable to read
	 */
	public static String fromFile(String path)
	{
		Path filepath = Path.of(path);
		try
		{
			return Files.readString(filepath);
		}
		catch (Exception e)
		{
			System.err.println("ERROR: Could not read from file \"" + filepath.toString() + "\"!");
			return "";
		}
	}

	/**
	 Casts the bytes within the array to a char, and StringBuilders them
	 * @param b An array of bytes
	 * @return A String from the typecasted bytes
	 */
	public static String fromBytes(byte[] b)
	{
		StringBuilder temp = new StringBuilder();

		for(byte d: b)
			temp.append((char) d);

		return temp.toString();
	}

	public static String fromInputStream(InputStream i) throws IOException
	{
		StringBuilder temp = new StringBuilder();
		char c;
		while((c = (char) i.read()) != (char) -1) /* Terrible, terrible things */
			temp.append(c);

		return temp.toString();
	}
}
