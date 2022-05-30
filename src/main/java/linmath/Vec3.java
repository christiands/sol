package linmath;

public class Vec3
{
	public float x;
	public float y;
	public float z;

	public Vec3() {}

	public Vec3(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float[] toArray()
	{
		return new float[] {x, y, z};
	}
}
