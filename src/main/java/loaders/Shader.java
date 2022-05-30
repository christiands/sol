package loaders;

import org.lwjgl.opengl.*;
import java.io.*;

public class Shader
{
	private final int id;

	/**
	 Creates a shader object within OpenGL
	 * @param vertex Path to the vertex shader source
	 * @param geometry Path to the geometry shader source
	 * @param fragment Path to the fragment shader source
	 */
	public Shader(String vertex, String geometry, String fragment)
	{
		int vs = compileShader(vertex, GL33.GL_VERTEX_SHADER);
		int gs = compileShader(geometry, GL33.GL_GEOMETRY_SHADER);
		int fs = compileShader(fragment, GL33.GL_FRAGMENT_SHADER);

		id = GL33.glCreateProgram();

		GL33.glAttachShader(id, vs);
		GL33.glAttachShader(id, gs);
		GL33.glAttachShader(id, fs);

		GL33.glLinkProgram(id);

		int[] buf = new int[1];
		GL33.glGetProgramiv(id, GL33.GL_LINK_STATUS, buf);
		if(buf[0] != 1)
		{
			String error = GL33.glGetProgramInfoLog(id);
			System.out.println("Error linking shaders! Message: \n" + error);
		}

		GL33.glDeleteShader(vs);
		GL33.glDeleteShader(gs);
		GL33.glDeleteShader(fs);
	}

	public Shader(InputStream vertex, InputStream geometry, InputStream fragment)
	{
		int vs = compileShader(vertex, GL33.GL_VERTEX_SHADER);
		int gs = compileShader(geometry, GL33.GL_GEOMETRY_SHADER);
		int fs = compileShader(fragment, GL33.GL_FRAGMENT_SHADER);

		id = GL33.glCreateProgram();

		GL33.glAttachShader(id, vs);
		GL33.glAttachShader(id, gs);
		GL33.glAttachShader(id, fs);

		GL33.glLinkProgram(id);

		int[] buf = new int[1];
		GL33.glGetProgramiv(id, GL33.GL_LINK_STATUS, buf);
		if(buf[0] != 1)
		{
			String error = GL33.glGetProgramInfoLog(id);
			System.out.println("Error linking shaders! Message: \n" + error);
		}

		GL33.glDeleteShader(vs);
		GL33.glDeleteShader(gs);
		GL33.glDeleteShader(fs);
	}

	public int compileShader(String path, int type)
	{
		String source = Text.fromFile(path);

		return pilar(type, source);
	}

	private int pilar(int type, String source)
	{
		int point = GL33.glCreateShader(type);
		GL33.glShaderSource(point, source);
		GL33.glCompileShader(point);

		int[] buf = new int[1];
		GL33.glGetShaderiv(point, GL33.GL_COMPILE_STATUS, buf);
		if(buf[0] != 1)
		{
			String error = GL33.glGetShaderInfoLog(point);
			System.out.println("Error compiling shaders! Message: \n" + error);
		}

		return point;
	}

	public int compileShader(InputStream stream, int type)
	{
		String source = null;
		try
		{
			source = Text.fromInputStream(stream);
		}
		catch(Exception e)
		{
			System.err.println("Error loading text from stream!");
			System.exit(1);
		}

		return pilar(type, source);
	}

	public int getId()
	{
		return id;
	}
}
