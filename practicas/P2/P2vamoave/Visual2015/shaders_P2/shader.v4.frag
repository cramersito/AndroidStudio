#version 330 core

out vec4 outColor;

in vec3 color;
in vec3 outNormal;
in vec3 outPos;
in vec2 texCoord;

uniform sampler2D colorTex;
uniform sampler2D emiTex;
uniform sampler2D specularTex;
uniform mat4 view;

//Propiedades del objeto
vec3 Ka; 
vec3 Kd; 
vec3 Ks;
vec3 Ke;
float n = 100.0;


vec3 N;
vec3 Pos;





 //  puntual
vec3 Ia = vec3(0.3); 
vec3 Il = vec3(1.0);  
vec3 Pl = vec3(0.0,0.0,0.0); 


vec3 shade();

void main()
{
	
	Ka = texture (colorTex, texCoord).rgb;
    Kd = Ka;

   
	Ks = vec3(1.0);
	Ke = texture (emiTex, texCoord).rgb;

	N = normalize(outNormal);
	Pos = outPos;

	outColor = vec4(shade(), 1.0);
	


	
}

vec3 shade()
{
    vec3 color = vec3(0.0);

	vec3 C1 = vec3(0.25);
	vec3 C2 = vec3(0.5);
	vec3 C3 = vec3(0.0); //este valor es tremendamente sensible.
	float L = length (Pl-Pos); // modulo del vector que une la luz y el objeto
	
	vec3 fatt = min(1/((C1+(C2*L)+C3*(L*L))),1);

	vec3 D = normalize(Pl-Pos);
	
	color += fatt*Il*Kd*dot(N,D);

    return color;
}




