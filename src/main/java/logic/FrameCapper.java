package logic;

public class FrameCapper
{
	private long runStart;
	private final long mspf; /* haha seconds per frame */

	/* Waits (1 / fps) - frameTime seconds before continuing */
	public FrameCapper(int fps)
	{
		mspf = 1000 / 60;
	}

	public void begin()
	{
		runStart = System.currentTimeMillis();
	}

	public void end()
	{
		try
		{
			Thread.sleep(mspf - runStart);
		}
		catch(Exception ignored)
		{}
	}
}
