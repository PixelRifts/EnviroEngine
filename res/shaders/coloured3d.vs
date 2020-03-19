#version 330 core

layout(location = 0) in vec3 i_pos;
layout(location = 1) in vec4 i_col;
layout(location = 2) in vec3 i_normal;

flat out vec4 p_flatCol;
out vec4 p_col;
out vec3 p_normal;

uniform mat4 u_ProjMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ModelMatrix;

void main() {
	gl_Position = u_ProjMatrix * u_ViewMatrix * u_ModelMatrix * vec4(i_pos, 1.0);
	p_col = i_col;
	p_flatCol = i_col;
	p_normal = i_normal;
}
