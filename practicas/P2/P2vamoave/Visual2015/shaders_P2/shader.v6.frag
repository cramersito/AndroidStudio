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
vec3 Ia = vec3(0.3); //Iluminación ambiental
vec3 Il = vec3(1.0);  //Intensidad lumínica
vec3 Pl = vec3(0.0,0.0,0.0); //Posición de la luz


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

vec3 shade() //focal
{

vec3 color = vec3(0.0);
float angle=3.1416/30;
vec3 f = vec3(0.0); 
vec3 direccion = vec3(0.0,0.0,-1.0);
vec3 D = Pl - Pos;
D = normalize(D);

if (dot(direccion,-D) > cos(angle))
{
	f=pow(clamp ((dot(direccion,-D)-cos(angle))/(1-cos(angle)),0.0001,1.0),1.0)*(Il*Kd*dot(N,D));
	f=Il*Kd*dot(N,D);
}
	else{
	f=vec3(0.0);
}

color += Ke;

color += f;
return color;
}




