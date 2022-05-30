package engine;

import java.util.ArrayList;
import linmath.*;
import util.FloatArrayBuilder;

public class RenderJob
{
	protected final float tileSizeX;
	protected final float tileSizeY;

	protected final Atlas atlas;

	ArrayList<Tile> tiles;

	public RenderJob(float w, float h, Atlas a)
	{
		/* Because screenspace is 2.0f across, not 1.0f */
		tileSizeX = w * 2;
		tileSizeY = h * 2;

		atlas = a;

		tiles = new ArrayList<>();
	}

	public void add(Vec3 tlc, int txi)
	{
		if(txi < 0)
			return;

		tiles.add(new Tile(tlc, txi));
	}

	public void add(Tile t)
	{
		if(t.idx < 0)
			return;

		tiles.add(t);
	}

	public void add(float x, float y, float z, int i)
	{
		if(i < 0)
			return;

		tiles.add(new Tile(new Vec3(x, y, z), i));
	}

	public void clear()
	{
		tiles.clear();
	}

	/**
	 Appends the necessary data to render a tile with a texture
	 * @return A float of points and attributes that can be rendered with glDrawArrays
	 */
	public float[] get()
	{
		FloatArrayBuilder f = new FloatArrayBuilder();

		for(Tile t: tiles)
		{
			f.append(t.pos.toArray());
			f.append(tileSizeX);
			f.append(tileSizeY);
			f.append(atlas.getU(t.idx).toArray());
			f.append(atlas.getV(t.idx).toArray());
		}

		return f.toArray();
	}

	public int size()
	{
		return tiles.size();
	}

	public int atlasIndex()
	{
		return atlas.getAtlasPointer();
	}
}
