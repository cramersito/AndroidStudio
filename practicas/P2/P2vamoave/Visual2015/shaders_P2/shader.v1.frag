#version 330 core

out vec4 outColor;

in vec3 color;
in vec3 normalout;
in vec3 posout;

//Variables locales
vec3 pos;
vec3 normalVertex;

//Propiedades de la luz
vec3 Ia = vec3(0.2);
vec3 Id = vec3(1,1,1);
vec3 Is = vec3(1,1,1);
vec3 Lpos = vec3(3,0,0);

//Propiedades del objeto
vec3 Ka;
vec3 Kd;
vec3 Ks = vec3(1.0);
float nexp = 10;


vec3 shade(){
	vec3 c = vec3(0);

	//Ambiental
	c = clamp(Ia*Ka,0,1);
	
	//Difusa
	vec3 L = normalize(Lpos - pos);
	c = clamp(Id*Kd*dot(L,normalVertex),0,1);

	//Especular
	vec3 R = normalize(reflect(-L,normalVertex));
	vec3 V = normalize(-pos);
	float factor = clamp(dot(R,V),0.001,1); 
	c += clamp(Is*Ks*pow(factor,nexp),0,1);

	return c;
}

void main()
{
	normalVertex = normalize(normalout);
	pos = posout;
	Ka = color;
	Kd = color;

	outColor = vec4(shade(), 1.0);
}
