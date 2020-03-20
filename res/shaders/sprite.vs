#version 330 core

layout (location = 0) in vec2 i_pos;
layout (location = 1) in vec2 i_uv;

out vec2 p_uv;

uniform mat4 u_ProjectionMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ModelMatrix;

void main() {
	gl_Position = u_ProjectionMatrix * u_ViewMatrix * u_ModelMatrix * vec4(i_pos, 0.0, 1.0);
	p_uv = i_uv;
}
