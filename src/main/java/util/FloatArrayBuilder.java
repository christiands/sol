package util;

public class FloatArrayBuilder
{
	public static final int INITIAL_SIZE = 8;

	private float[] data;
	private int point = 0;

	public FloatArrayBuilder()
	{
		data = new float[INITIAL_SIZE];
	}

	public FloatArrayBuilder(int initial)
	{
		data = new float[initial];
	}

	/**
	 Appends a float to the builder, and reallocates if it does not have enough space (ala ArrayList)
	 * @param number The float to append
	 */
	public void append(float number)
	{
		if(point + 1 >= data.length)
		{
			float[] temp = new float[data.length * 2];
			System.arraycopy(data, 0, temp, 0, data.length);
			/* Classic C trick when realloc isn't available, except we let the GC do all the work */
			data = temp;
		}

		data[point++] = number;
	}

	/**
	 Appends an array of floats to the builder
	 * @param array An array of floating point numbers
	 */
	public void append(float[] array)
	{
		for(float f: array)
			this.append(f);
	}

	/**
	 Converts the FloatArrayBuilder object to a float array
	 * @return An array of floats
	 */
	public float[] toArray()
	{
		if(point + 1 != data.length)
		{
			float[] temp = new float[point + 1];
			System.arraycopy(data, 0, temp, 0, temp.length);
			data = temp;
		}

		return data;
	}
}
