package logic;

import org.lwjgl.glfw.*;

import engine.*;

public class InputHandler
{
	public float cposX = 0.0f;
	public float cposY = 0.0f;
	public float cstep = 1.0f;

	public PlayerAndCamera camera;
	public RenderJob player;
	public FontRenderer font;

	public int gw;
	public int gh;

	/* This is all one shoe-horned mess, but I'm really tired of OOP at this point */
	public InputHandler(long window, RenderJob r, FontRenderer f, int gw, int gh)
	{
		player = new RenderJob(AssetLoader.PLR_WIDTH, AssetLoader.PLR_HEIGHT, AssetLoader.playerAtlas);
		camera = new PlayerAndCamera(r, gw, gh);
		font = f;

		this.gw = gw;
		this.gh = gh;

		GLFW.glfwSetKeyCallback(window, new GLFWKeyCallbackI()
		{
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods)
			{
				clear(window, key, scancode, action, mods);
			}
		});
	}

	public void clear(long dwindow, int key, int scancode, int action, int mods)
	{
		if(action == GLFW.GLFW_RELEASE)
			switch(key)
			{
				case GLFW.GLFW_KEY_W:
					camera.moveUp();
					break;
				case GLFW.GLFW_KEY_S:
					camera.moveDown();
					break;
				case GLFW.GLFW_KEY_A:
					camera.moveLeft();
					break;
				case GLFW.GLFW_KEY_D:
					camera.moveRight();
					break;

				case GLFW.GLFW_KEY_UP:
					cposY = cposY - cstep;
					break;
				case GLFW.GLFW_KEY_DOWN:
					cposY = cposY + cstep;
					break;
				case GLFW.GLFW_KEY_LEFT:
					cposX = cposX - cstep;
					break;
				case GLFW.GLFW_KEY_RIGHT:
					cposX = cposX + cstep;
					break;

				case GLFW.GLFW_KEY_SPACE:
					font.clear();
					break;

				case GLFW.GLFW_KEY_ESCAPE:
					GLFW.glfwSetWindowShouldClose(dwindow, true);

				default:
					System.out.println(key);
			}

		cposX = cposX < 0 ? 0 : cposX;
		cposX = cposX > AssetLoader.mapWidth - gw ? AssetLoader.mapWidth - gw : cposX;

		cposY = cposY < 0 ? 0 : cposY;
		cposY = cposY > AssetLoader.mapHeight - gh ? AssetLoader.mapHeight - gh : cposY;

		camera.cmove(cposX, cposY);
		camera.add();
	}
}
