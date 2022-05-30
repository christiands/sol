import engine.*;
import linmath.*;
import loaders.*;
import logic.*;
import org.lwjgl.glfw.*;
import java.io.*;


public class SolGame
{
	public static int gridWidth = 10;
	public static int gridHeight = 6;
	public static int gridMul = 120;

	public static void main(String[] args)
	{
		InputStream vsPath = Resources.getIS("shaders/vertex.glsl");
		InputStream gsPath = Resources.getIS("shaders/geometry.glsl");
		InputStream fsPath = Resources.getIS("shaders/fragment.glsl");

		/* 80 px per tile or whatever */
		Render r = new Render(gridWidth * gridMul, gridHeight * gridMul, "sol - Christian Scott 2022");
		Shader s = new Shader(vsPath, gsPath, fsPath);


		/* TODO: Clean this up */
		AssetLoader assetLoader = new AssetLoader();
		assetLoader.run();
		RenderJob shaderTest = new RenderJob(1.0f / gridWidth, 1.0f / gridHeight, AssetLoader.blockAtlas);

		RenderJob backgroundTest = new RenderJob(1, 1, AssetLoader.lazybgAtlas);
		backgroundTest.add(-1.0f, 1.0f, 0.0f, 0);

		FontLoader fontLoader = new FontLoader();
		fontLoader.run();
		FontRenderer fontRenderer = new FontRenderer(1.0f / 70, 1.0f / 35, fontLoader.atlas);

		InputHandler inputHandler = new InputHandler(r.getWindow(), shaderTest, fontRenderer, gridWidth, gridHeight);
		FrameCapper capper = new FrameCapper(60);

		fontRenderer.add(-1.0f, 1.0f, 0.0f, "W : up\nS : down\nA : left\nD : right\nESC : quit\nSPC : hide message");

		while(!GLFW.glfwWindowShouldClose(r.getWindow()))
		{
			r.start();
			r.draw(backgroundTest, s);
			r.draw(shaderTest, s);
			r.draw(fontRenderer, s);

			r.end();
			GLFW.glfwPollEvents();
		}
	}

	private static void generateSameSquares(RenderJob r, int w, int h, int id)
	{
		float tw = 1.0f / w * 2;
		float th = 1.0f / h * 2;

		for(int i = 0; i <= h; ++i)
			for(int j = 0; j <= w; ++j)
			{
				Vec3 temp = new Vec3((-0.8f) + (tw * j), (-0.8f) + (th * i), 0.0f);
				r.add(temp, id);
			}
	}

	private static void generateWeirdSquares(RenderJob r, int w, int h, int lim)
	{
		float tw = 1.0f / w * 2;
		float th = 1.0f / h * 2;

		int tileI = 0;

		for(int i = 0; i <= h; ++i)
			for(int j = 0; j <= w; ++j)
			{
				Vec3 temp = new Vec3((-1.0f) + (tw * j), (-1.0f) + (th * i), 0.0f);
				r.add(temp, tileI + 1 > lim ? tileI = 0 : tileI++); /* I love C */
			}
	}
}
