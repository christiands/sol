#version 330 core
/* Does some really fancy stuff, because graphical acceleration is fun */

layout (points) in;
layout (triangle_strip, max_vertices = 4) out; /* Because quads + triangle_strip */

in GEO_DATA
{
	vec2 size;
	vec2 topl;
    vec2 botr;
}
sz_data[];

out vec2 uv_itxF;

void main()
{
	vec4 pt_pos = gl_in[0].gl_Position;
	vec2 wh_szeG = sz_data[0].size;

	/* Top left */
	gl_Position = pt_pos;
	uv_itxF = sz_data[0].topl;
	EmitVertex();

	/* Top right */
	gl_Position = pt_pos + vec4(wh_szeG.x, 0.0f, 0.0f, 0.0f);
	uv_itxF = vec2(sz_data[0].botr.x, sz_data[0].topl.y);
	EmitVertex();

	/* Bottom left */
	gl_Position = pt_pos + vec4(0.0f, -wh_szeG.y, 0.0f, 0.0f);
	uv_itxF = vec2(sz_data[0].topl.x, sz_data[0].botr.y);
    EmitVertex();

	/* Bottom right */
    gl_Position = pt_pos + vec4(wh_szeG.x, -wh_szeG.y, 0.0f, 0.0f);
    uv_itxF = sz_data[0].botr;
    EmitVertex();

    EndPrimitive();
}