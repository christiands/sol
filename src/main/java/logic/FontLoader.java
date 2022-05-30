package logic;

import engine.*;
import linmath.*;
import loaders.*;

import java.io.InputStream;
import java.security.spec.ECField;

/* Loads the IBM VGA 8x16-ish font */
public class FontLoader
{
	public Atlas atlas;

	public void run()
	{
		try
		{
			InputStream fontTexturePath = Resources.getIS("textures/font.png");
			int fontPointer = GraphicsTexture.load(fontTexturePath);
			Texture[] fontTextures = new Texture[95]; /* ASCII only */

			float xStride = (float) 1 / 48;
			float yStride = (float) 1 / 2;

			for(int i = 0; i < 95; ++i)
			{
				int xoff = i % 48;
				int yoff = i / 48;

				Vec2 tr = new Vec2(xoff * xStride, yoff * yStride);
				Vec2 br = new Vec2(tr.x + xStride, tr.y + yStride);
				fontTextures[i] = new Texture(tr, br); /* because space */
			}

			atlas = new Atlas(fontTextures, fontPointer);
		}
		catch(Exception e)
		{
			System.err.println("Could not load font!");
			System.exit(-1);
		}
	}
}
