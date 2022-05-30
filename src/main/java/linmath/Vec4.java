package linmath;

public class Vec4
{
	public float x;
	public float y;
	public float z;
	public float w;

	public float[] toArray()
	{
		return new float[] {x, y, z, w};
	}
}
