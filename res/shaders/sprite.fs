#version 330 core

in vec2 p_uv;

uniform sampler2D u_TextureSampler;

void main() {
	gl_FragColor = texture(u_TextureSampler, p_uv);
}
