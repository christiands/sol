package linmath;

public class Vec2
{
	public float x;
	public float y;

	public Vec2(){}

	public Vec2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public float[] toArray()
	{
		return new float[] {x, y};
	}
}
