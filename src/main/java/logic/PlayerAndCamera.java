package logic;

import engine.RenderJob;

public class PlayerAndCamera
{
	private int playerX;
	private int playerY;
	private int ctexi;
	private static final int texiStart = 12;

	private float cameraX;
	private float cameraY;

	private final RenderJob bg;

	private final int gw;
	private final int gh;

	private int oldX;
	private int oldY;
	private int oldTexi;

	public PlayerAndCamera(RenderJob tile, int gridWidth, int gridHeight)
	{
		/* Right-down coordinates */
		playerX = 2;
		playerY = 12;
		ctexi = texiStart;

		oldX = playerX;
		oldY = playerY;
		oldTexi = AssetLoader.mapData[(oldY * AssetLoader.mapWidth) + oldX];

		cameraX = 0.0f;
		cameraY = 0.0f;

		this.bg = tile;

		gw = gridWidth;
		gh = gridHeight;
	}

	public void centerCameraOverPlayer(int px, int py)
	{
		float hx = (int) ((float) gw / 2);
		float hy = (int) ((float) gh / 2);

		float cx = px - hx;
		float cy = py - hy;

		cx = cx < 0 ? 0 : cx;
		cx = cx > AssetLoader.mapWidth - hx ? AssetLoader.mapWidth - hx : cx;

		cy = cy < 0 ? 0 : cy;
		cy = cy > AssetLoader.mapHeight - hy ? AssetLoader.mapHeight - hy : cy;

		cameraX = cx;
		cameraY = cy;
	}

	public void moveUp()
	{
		if(Camera.getTexi(playerX, playerY - 1) > 0 && playerY - 1 > 0 && playerY < AssetLoader.mapHeight)
			playerY = playerY - 1;
		ctexi = 1 + texiStart;
		assetPush(playerX, playerY, ctexi);
	}

	public void moveDown()
	{
		if(Camera.getTexi(playerX, playerY + 1) > 0 && playerY > 0 && playerY + 1 < AssetLoader.mapHeight)
			playerY = playerY + 1;
		ctexi = texiStart;
		assetPush(playerX, playerY, ctexi);
	}

	public void moveLeft()
	{
		if(Camera.getTexi(playerX - 1, playerY) > 0 && playerX - 1 > 0 && playerX < AssetLoader.mapWidth)
			playerX = playerX - 1;
		ctexi = 3 + texiStart;
		assetPush(playerX, playerY, ctexi);
	}

	public void moveRight()
	{
		if(Camera.getTexi(playerX + 1, playerY) > 0 && playerX > 0 && playerX + 1 < AssetLoader.mapWidth)
			playerX = playerX + 1;
		ctexi = 2 + texiStart;
		assetPush(playerX, playerY, ctexi);
	}

	public void cmove(float x, float y)
	{
		if(!(x < 0 || x > AssetLoader.mapWidth - gw || y < 0 || y > AssetLoader.mapHeight - gh))
		{
			cameraX = x;
			cameraY = y;
		}
	}

	public void add()
	{
		bg.clear();

		centerCameraOverPlayer(playerX, playerY);

		Camera.set(bg, cameraX, cameraY, gw, gh);
	}

	private boolean inCamera(float px, float py)
	{
		float xo = (float) 1 / gw;
		float yo = (float) 1 / gh;

		/* There's definitely bugs in here, but they're just performance related */
		return px + xo > cameraX && px < cameraX + gw && py + yo > cameraY && py < cameraY + gh;
	}

	private void assetPush(int x, int y, int i)
	{
		x = x < 0 ? 0 : x;
		x = x > AssetLoader.mapWidth ? AssetLoader.mapWidth : x;

		y = y < 0 ? 0 : y;
		y = y > AssetLoader.mapHeight ? AssetLoader.mapHeight : y;

		AssetLoader.mapData[(oldY * AssetLoader.mapWidth) + oldX] = oldTexi;

		oldTexi = AssetLoader.mapData[(y * AssetLoader.mapWidth) + x];
		AssetLoader.mapData[(y * AssetLoader.mapWidth) + x] = i;

		oldY = y;
		oldX = x;
	}
}
