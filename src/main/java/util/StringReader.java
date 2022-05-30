package util;

public class StringReader
{
	private final char[] data;
	private final char stop;
	private int point;

	/**
	 Constructs the StringReader class, which reads strings up to stop, and skips over it in the next call
	 * @param s The String to read
	 * @param stop What char to stop reading at, and jump over
	 */
	public StringReader(String s, char stop)
	{
		this.data = s.toCharArray();
		this.stop = stop;
		this.point = 0;
	}

	public String getNext()
	{
		if(point >= data.length)
			return null;

		StringBuilder temp = new StringBuilder();
		while(data[point] != stop)
		{
			if(point + 1 >= data.length)
			{
				temp.append(data[point]);
				break;
			}
			temp.append(data[point++]);
		}
		point++;

		return temp.toString();
	}
}
