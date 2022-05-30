package loaders;

import org.lwjgl.BufferUtils;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Data
{
	public static byte[] load(InputStream stream)
	{
		/* Initialization */
		byte[] data = new byte[8];
		int point = 0;

		/* Reading */
		try
		{
			byte[] b = new byte[1];
			while(stream.read(b) > 0)
			{
				/* Classic C reallocation without realloc */
				/* Garbage collector gonna suffer */
				if(point + 1 >= data.length)
				{
					byte[] temp = new byte[data.length * 2];
					System.arraycopy(data, 0, temp, 0, data.length);
					data = temp;
				}

				data[point++] = b[0];
			}
		}
		catch(Exception e)
		{
			System.err.println("Could not read from InputStream!");
			System.exit(-1);
		}

		/* Resizing */
		if(point < data.length - 1)
		{
			byte[] temp = new byte[point];
			System.arraycopy(data, 0, temp, 0, point);
		//	System.out.println(Arrays.toString(temp) + "\n" + temp.length);
			return temp;
		}

		return data;
	}

	public static ByteBuffer loadBB(InputStream stream)
	{
		/* Java: complete pain when working with memory */
		byte[] data = load(stream);
		ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
