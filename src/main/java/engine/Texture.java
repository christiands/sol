package engine;

import linmath.*;

/* Data storage type for quad coordinates */

public class Texture
{
	public Vec2 u;
	public Vec2 v;

	public Texture(Vec2 u, Vec2 v)
	{
		this.u = u;
		this.v = v;
	}

	public boolean equals(Texture t)
	{
		return t.u == u && t.v == v;
	}
}
