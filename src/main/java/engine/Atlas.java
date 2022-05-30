package engine;

import linmath.*;

public class Atlas
{
	private final Texture[] textures;
	private final int atlas;

	public Atlas(Texture[] textures, int bufferLocation)
	{
		this.textures = textures;
		this.atlas = bufferLocation;
	}

	public int findTexture(Texture t)
	{
		for(int i = 0; i < textures.length; ++i)
			if(textures[i].equals(t))
				return i;
		return -1;
	}

	public Texture getTexture(int index)
	{
		return textures[index];
	}

	public Vec2 getU(int index)
	{
		return textures[index].u;
	}

	public Vec2 getV(int index)
	{
		return textures[index].v;
	}

	public int getAtlasPointer()
	{
		return atlas;
	}
}
