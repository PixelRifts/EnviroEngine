#version 330 core

in vec4 p_col;
in vec2 p_uv;

uniform sampler2D u_TextureSampler;

void main() {
	gl_FragColor = p_col * texture(u_TextureSampler, p_uv);
}
