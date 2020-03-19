#version 330 core

flat in vec4 p_flatCol;
in vec4 p_col;
in vec3 p_normal;

uniform int u_IsFlat;

void main() {
	gl_FragColor = u_IsFlat == 1 ? p_flatCol : p_col; 
}
