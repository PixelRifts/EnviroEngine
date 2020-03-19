#version 330 core

layout(location = 0) in vec2 i_pos;
layout(location = 1) in vec4 i_col;
layout(location = 2) in vec2 i_uv;

out vec4 p_col;
out vec2 p_uv;

void main() {
	gl_Position = vec4(i_pos, 0.0, 1.0);
	p_col = i_col;
	p_uv = i_uv;
}
