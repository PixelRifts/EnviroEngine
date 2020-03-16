#version 330 core

layout(location = 0) in vec2 i_pos;
layout(location = 1) in vec4 i_colour;
layout(location = 2) in vec2 i_uv;

out vec4 p_colour;
out vec2 p_uv;

void main() {
	gl_Position = vec4(i_pos, 0.0, 1.0);
	p_colour = i_colour;
	p_uv = i_uv;
}
