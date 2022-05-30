#version 330 core

layout (location = 0) in vec3 tl_posV;
layout (location = 1) in vec2 wh_szeV;
layout (location = 2) in vec2 tx_posV;
layout (location = 3) in vec2 bx_posV;

out GEO_DATA
{
	vec2 size;
	vec2 topl;
	vec2 botr;
}
sz_datG;

void main()
{
	gl_Position = vec4(tl_posV, 1.0f);

	sz_datG.size = wh_szeV;
	sz_datG.topl = tx_posV;
	sz_datG.botr = bx_posV;
}