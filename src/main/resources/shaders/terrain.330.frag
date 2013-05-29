in vec4 normal;
in vec4 colour;
in vec2 uvcoord;

uniform sampler2D diffuse;

layout(location=0) out vec4 outputColour;
layout(location=0) out vec4 normalColour;

void main() {
    vec4 fontSample = texture(diffuse, uvcoord);
    outputColour = fontSample * colour;
    normalColour = (normal + vec4(1, 1, 1, 1)) / 2;
}