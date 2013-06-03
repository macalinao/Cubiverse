#version 330

in vec2 uvcoord;
in vec4 color;
in vec4 normal;

uniform sampler2D Stars;

layout(location=0) out vec4 outputColour;
layout(location=1) out vec4 normalColour;

void main() {
    outputColour = texture(Stars, uvcoord);
}