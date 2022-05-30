package engine;

/*
 sol's renderer in a nutshell:
 - everything is tile based, so RenderJobs basically give an atlas, and the coordinates for what to draw
 */

import loaders.Shader;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.util.Arrays;

public class Render
{
	private final long window;

	private int VAO;
	private int VBO;

	public Render(int width, int height, String name)
	{
		GLFW.glfwInit();

		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

		/* The resize is kinda messy, so we want to avoid it if possible */
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

		window = GLFW.glfwCreateWindow(width, height, name, 0, 0);

		GLFW.glfwMakeContextCurrent(window);

		/*
		GLFW.glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallbackI()
		{
			@Override
			public void invoke(long window, int w, int h)
			{
				/* Width major, proper scaling
				float aspectRatio = (float) width / height;
				int fakeHeight = (int) (w / aspectRatio);
				int blackbarHeight = (h - fakeHeight) / 2;
				GL33.glViewport(0, blackbarHeight, w, fakeHeight);
			}
		});
		*/

		GL.createCapabilities();

		GL33.glViewport(0, 0, width, height);

		GL33.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		/* Fancy transparency */
		GL33.glEnable(GL33.GL_BLEND);
		GL33.glBlendFunc(GL33.GL_ONE, GL33.GL_ONE_MINUS_SRC_ALPHA);

		VAO = GL33.glGenVertexArrays();
		VBO = GL33.glGenBuffers();

		genLayout();
	}

	/**
	 Generates the VertexAttribPointer data
	 */
	private void genLayout()
	{
		GL33.glBindVertexArray(VAO);
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, VBO);

		int stride = Float.BYTES * (3 + 2 + 2 + 2);

		/* Top-left position (vec3) */
		GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, stride, 0);
		GL33.glEnableVertexAttribArray(0);

		/* Width + height (vec2) */
		GL33.glVertexAttribPointer(1, 2, GL33.GL_FLOAT, false, stride, 12);
		GL33.glEnableVertexAttribArray(1);

		/* Tile's texture coordinate U.XY (vec2) */
		GL33.glVertexAttribPointer(2, 2, GL33.GL_FLOAT, false, stride, 20);
		GL33.glEnableVertexAttribArray(2);

		/* Tile's texture coordinate V.XY (vec2) */
		GL33.glVertexAttribPointer(3, 2, GL33.GL_FLOAT, false, stride, 28);
		GL33.glEnableVertexAttribArray(3);
	}

	public void start()
	{
		GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
		GL33.glBindVertexArray(VAO);
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, VBO);
	}

	/**
	 Draws the content of the RenderJob to the buffer, can be called multiple times before end()
	 @param r The RenderJob to draw on the screen
	 */
	public void draw(RenderJob r, Shader s)
	{
		float[] data = r.get();

		GL33.glUseProgram(s.getId());
		GL33.glBindTexture(GL33.GL_TEXTURE_2D, r.atlasIndex());

		GL33.glBufferData(GL33.GL_ARRAY_BUFFER, data, GL33.GL_DYNAMIC_DRAW);
		GL33.glDrawArrays(GL33.GL_POINTS, 0, r.size());
		// System.out.println(Arrays.toString(data));
	}

	public void end()
	{
		GLFW.glfwSwapBuffers(window);
	}

	public long getWindow()
	{
		return window;
	}
}
