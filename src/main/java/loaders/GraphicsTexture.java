package loaders;

import java.io.*;
import java.nio.*;
import java.util.Arrays;

import org.lwjgl.opengl.*;
import org.lwjgl.stb.*;

/*
* Pretty much constant city, generates an OpenGL texture and returns the int pointer
* */
public class GraphicsTexture
{
	public static int load(String path) throws IOException
	{
		/* More hacky stuff because I want to avoid buffers as much as possible */
		int[][] imageProps = new int[3][1];
		/* This works, by the way */
		ByteBuffer data = STBImage.stbi_load(path, imageProps[0], imageProps[1], imageProps[2], 4); /* RGBA only */
		return gentex(imageProps, data);
	}

	public static int load(InputStream stream) throws IOException
	{
		/* More hacky stuff because I want to avoid buffers as much as possible */
		int[][] imageProps = new int[3][1];
		/* This works, by the way */
		ByteBuffer image = Data.loadBB(stream);
		System.out.println(image.capacity());
		ByteBuffer data = STBImage.stbi_load_from_memory(image, imageProps[0], imageProps[1], imageProps[2], 4);
		return gentex(imageProps, data);
	}

	private static int gentex(int[][] imageProps, ByteBuffer data) throws IOException
	{
		if(data == null)
			throw new IOException();

		System.out.println(data);

		int point = GL33.glGenTextures();
		GL33.glBindTexture(GL33.GL_TEXTURE_2D, point);
		GL33.glTexImage2D(GL33.GL_TEXTURE_2D, 0, GL33.GL_RGBA, imageProps[0][0], imageProps[1][0], 0,
			GL33.GL_RGBA, GL33.GL_UNSIGNED_BYTE, data);
		GL33.glGenerateMipmap(GL33.GL_TEXTURE_2D);
		GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MIN_FILTER, GL33.GL_NEAREST);
		GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MAG_FILTER, GL33.GL_NEAREST);

		STBImage.stbi_image_free(data);

		return point;
	}
}
