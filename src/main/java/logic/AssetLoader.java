package logic;

import engine.*;
import linmath.*;
import loaders.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetLoader
{
	public static Atlas lazybgAtlas;
	public static Atlas blockAtlas;
	public static final int BLK_WIDTH = 4;
	public static final int BLK_HEIGHT = 4;

	public static Atlas playerAtlas;
	public static final int PLR_WIDTH = 2;
	public static final int PLR_HEIGHT = 2;

	public static final int[] rawMapData =
		{
			1,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,3,0,0,
			1,4,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,1,1,1,2,2,2,2,1,0,0,0,0,0,0,0,3,0,0,3,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,3,0,0,
			1,4,1,4,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,2,2,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,0,0,0,0,0,0,0,3,0,0,3,0,0,3,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,3,0,0,
			1,4,1,4,1,1,1,4,1,1,1,1,1,4,4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,0,0,0,0,0,0,0,3,0,0,3,0,0,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,
			1,4,1,4,1,1,1,4,1,1,1,1,1,4,1,1,1,1,1,1,4,1,1,1,1,4,4,4,4,4,2,2,2,2,1,1,2,1,2,2,2,2,2,1,1,2,1,1,1,1,1,1,2,2,2,2,2,2,1,1,0,3,3,3,3,3,0,3,0,0,3,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,
			1,4,4,4,4,4,4,4,4,1,1,1,1,4,1,1,1,1,1,1,4,1,1,1,1,4,1,1,1,1,1,1,1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,2,1,2,1,1,0,3,0,0,0,3,0,3,0,0,3,0,0,0,0,3,3,3,3,3,3,3,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,
			1,4,1,1,1,1,1,1,4,1,1,1,1,4,1,1,1,1,1,1,4,1,1,1,1,4,1,1,1,1,1,1,1,2,2,2,2,1,2,1,1,1,1,1,1,2,1,1,2,2,2,1,2,1,1,2,1,2,1,1,0,3,0,0,0,3,3,3,0,0,3,3,3,3,3,3,0,0,0,0,0,0,0,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,
			1,4,1,1,1,1,1,1,4,1,1,1,1,4,1,1,1,1,1,1,4,1,1,1,1,4,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,2,1,1,2,1,1,2,1,1,1,2,1,1,2,1,2,1,1,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,8,9,0,0,0,0,0,
			1,4,4,4,4,1,1,1,4,4,4,4,4,4,4,4,4,1,1,1,4,1,1,1,1,4,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,2,1,1,2,1,1,2,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,6,0,0,0,0,0,0,
			1,1,1,1,4,1,1,1,1,1,4,1,1,4,1,1,4,1,1,1,4,1,1,1,1,4,1,1,1,1,1,2,2,2,1,2,2,2,2,2,2,2,2,1,1,2,1,1,2,1,1,1,2,1,1,1,2,1,1,1,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,5,5,5,6,10,5,0,0,0,0,
			1,11,11,11,11,11,11,11,1,1,4,1,1,4,1,1,4,1,1,1,4,4,4,4,4,4,4,4,4,4,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,2,1,1,2,1,1,1,2,1,1,1,2,1,1,1,0,3,3,3,0,3,3,3,3,3,0,0,3,3,3,3,3,0,0,3,3,3,3,0,3,3,3,3,0,0,11,11,11,11,11,11,12,12,12,12,
			1,11,11,11,11,11,11,11,1,1,4,1,1,4,1,1,4,1,1,1,1,1,1,4,1,1,1,1,1,1,1,2,1,2,1,1,2,2,2,1,1,1,1,1,1,2,1,1,2,1,1,1,2,1,1,1,2,1,1,1,3,3,0,0,0,3,0,0,0,3,0,0,3,0,0,0,3,0,0,3,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			1,11,11,11,11,11,11,11,1,1,4,1,1,4,4,4,4,1,1,1,1,1,1,4,1,1,1,1,1,1,1,2,1,2,1,1,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,2,1,1,1,3,0,0,3,3,3,0,0,0,3,0,0,3,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,
			1,11,11,11,11,11,11,11,1,1,4,4,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,3,0,0,3,0,0,0,0,0,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,0,0,0,0,3,3,3,3,3,0,0
		};
	public static int[] mapData;

	public static final int[] rawBeachData =
		{
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,7,8,9,7,8,9,0,0,0,0,0,
			0,0,0,0,0,6,0,0,6,0,0,0,0,0,0,
			0,0,0,0,0,6,10,0,6,0,0,0,0,0,0,
			11,11,11,11,11,11,11,11,11,11,11,12,12,12,12
		};
	public static int[] beachData;

	public static final int mapWidth = 100;
	public static final int mapHeight = 15;

	public void run()
	{
		try
		{
			genLBGA();
			genBLKA();
			genPLRA();
			genMAPD();
		}
		catch(Exception e)
		{
			System.err.println("Could not open one or more textures.");
			System.exit(-1);
		}
	}

	private void genLBGA() throws IOException
	{
		InputStream realpath;

		/* Easter egg, 1 in 5 chance on launch */
		double rand = Math.random();
		System.out.println(rand);
		if(rand <= 0.2)
			realpath = Resources.getIS("textures/easteregg.jpg");
		else
			realpath = Resources.getIS("textures/lazybg.png");

		int point = GraphicsTexture.load(realpath);
		Texture[] texture = new Texture[1];
		texture[0] = new Texture(new Vec2(0.0f, 0.0f), new Vec2(1.0f, 1.0f));
		lazybgAtlas = new Atlas(texture, point);
	}

	private void genBLKA() throws IOException
	{
		InputStream realpath = Resources.getIS("textures/bgv2.png");
		blockAtlas = texToAtlas(realpath, BLK_WIDTH, BLK_HEIGHT);
	}

	private void genPLRA() throws IOException
	{
		InputStream realpath = Resources.getIS("textures/plv2.png");
		playerAtlas = texToAtlas(realpath, PLR_WIDTH, PLR_HEIGHT);
	}

	private Atlas texToAtlas(String realpath, int width, int height) throws IOException
	{
		int point = GraphicsTexture.load(realpath);
		return atlaser(width, height, point);
	}

	private Atlas texToAtlas(InputStream i, int width, int height) throws IOException
	{
		int point = GraphicsTexture.load(i);
		return atlaser(width, height, point);
	}

	private Atlas atlaser(int width, int height, int point)
	{
		Texture[] texture = new Texture[width * height];

		float sx = (float) 1 / width;
		float sy = (float) 1 / height;

		for(int i = 0; i < height; ++i)
			for(int j = 0; j < width; ++j)
			{
				Vec2 tl = new Vec2(j * sx, i * sy);
				Vec2 br = new Vec2(tl.x + sx, tl.y + sy);
				texture[(i * width) + j] = new Texture(tl, br);
			}

		return new Atlas(texture, point);
	}

	/* If an indice is -1, then don't render it */
	private void genMAPD()
	{
		mapData = new int[rawMapData.length];
		for(int i = 0; i < mapData.length; ++i)
			mapData[i] = rawMapData[i] - 1;

		beachData = new int[rawBeachData.length];
		for(int i = 0; i < rawBeachData.length; ++i)
			beachData[i] = rawBeachData[i] - 1;
	}
}
