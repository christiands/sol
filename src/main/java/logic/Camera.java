package logic;

import engine.RenderJob;

public class Camera
{
	/* TODO: solve the "map smaller than camera" problem */
	/**
	 Puts the camera in a position and returns the generated tiles
	 * @param r The RenderJob to put the tiles in
	 * @param x The top-left corner of the renderer X coord
	 * @param y The top-left corner of the renderer Y coord
	 * @param vw The width of the viewport, in tiles
	 * @param vh The height of the viewport, in tiles
	 */
	public static void set(RenderJob r, float x, float y, int vw, int vh)
	{
		/* Bounds check */
		x = x > AssetLoader.mapWidth ? AssetLoader.mapWidth : x;
		x = x < 0 ? 0 : x;

		y = y > AssetLoader.mapHeight ? AssetLoader.mapHeight : y;
		y = y < 0 ? 0 : y;

		//System.out.printf("[%f, %f]\n", x, y);

		float xr = (float) 2 / vw;
		float yr = (float) 2 / vh;

		float xo = (-1.0f) - ((x % 1) * xr);
		float yo = (1.0f) + ((y % 1) * yr);

		for(int i = 0; i < vh + 1; ++i)
			for(int j = 0; j < vw + 1; ++j)
			{
				int texi = getTexi(x, y, j, i);
				//System.out.printf("[%d : %d] (int) %f + (%d) + ((int) %f * %d) + (%d * %d)", (int) x + (j) + ((int) y * AssetLoader.mapWidth) + (i * AssetLoader.mapWidth), texi, x, j, y, AssetLoader.mapWidth, i, AssetLoader.mapWidth);
				r.add(xo + (j * xr), yo - (i * yr), 0.0f, texi);
				//System.out.printf(" | [%f (%f), %f (%f), %d]\n", xo + (j * xr), x % 1, yo - (i * yr), y % 1, texi);
			}
	}

	public static int getTexi(float sx, float sy, int fx, int fy)
	{
		int idx = (int) sx + (fx) + ((int) sy * AssetLoader.mapWidth) + (fy * AssetLoader.mapWidth);
		if(idx < AssetLoader.mapData.length)
			return AssetLoader.mapData[idx];

		return -1;
	}

	public static int getTexi(int sx, int sy, int fx, int fy)
	{
		int idx = sx + (fx) + (sy * AssetLoader.mapWidth) + (fy * AssetLoader.mapWidth);
		if(idx < AssetLoader.mapData.length)
			return AssetLoader.mapData[idx];

		return -1;
	}

	public static int getTexi(int x, int y)
	{
		int idx = y * AssetLoader.mapWidth + x;
		if(idx < AssetLoader.mapData.length)
			return AssetLoader.mapData[idx];

		return -1;
	}
}
