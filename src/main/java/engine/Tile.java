package engine;

import linmath.Vec3;

public class Tile
{
	public Vec3 pos;
	public int idx;

	public Tile(Vec3 position, int textureIndex)
	{
		pos = position;
		idx = textureIndex;
	}
}
