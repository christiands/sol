#version 330 core

in vec2 uv_itxF;

out vec4 px_col;

uniform sampler2D atlas;

void main()
{
	px_col = texture(atlas, uv_itxF);
	//px_col = vec4(uv_itxF, 0.6f, 1.0f);
}