package logic;

import engine.*;
import linmath.*;

public class FontRenderer extends RenderJob
{
	private final float width = super.tileSizeX;
	private final float height = super.tileSizeY;

	private final Atlas atlas = super.atlas;

	public FontRenderer(float w, float h, Atlas a)
	{
		super(w, h, a);
	}

	public void add(float x, float y, float z, String string)
	{
		char[] iterator = string.toCharArray();
		int xcount = 0;
		int ycount = 0;

		for(char c: iterator)
		{
			if(c >= ' ' && c <= '~')
			{
				Vec3 pos = new Vec3(x + width * xcount++, y - height * ycount, z);
				super.add(pos, c - ' ');
			}
			if(c == '\n')
			{
				++ycount;
				xcount = 0;
			}
		}
	}


	public void add(Vec3 pos, String string)
	{
		add(pos.x, pos.y, pos.z, string);
	}

	public void clear()
	{
		super.clear();
	}

	@Override
	public float[] get()
	{
		return super.get();
	}
}
