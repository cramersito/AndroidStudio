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


vec3 N; //normal
vec3 Pos;



//fuente de luz

//Fuente 1 // puntual
vec3 Ia = vec3(0.3); //iluminacion ambiental
vec3 Il = vec3(0.1);  //intensidad luminica
vec3 Pl = vec3(-1.0,-1.0,0.0); //Posición de la luz

//Fuente 2
vec3 Ia2 = vec3(0.3);
vec3 Il2 = vec3(0.3);  
vec3 Pl2 = vec3(1.0,1.0,0.0);

vec3 shade();
vec3 shade2();
vec3 shade()
{
    vec3 color = vec3(0.0);
	color = Ia*Ka;

	vec3 D = normalize(Pl-Pos);
	
	color += Il*Kd*dot(N,D);

	vec3 V = normalize(-Pos);
	vec3 R = normalize(reflect(-D,N));
	float factor= clamp (dot(V,R),0.0001,1.0);
	factor = pow (factor,n);
	color += Il * Ks * factor;

	color +=Ke;

    return color;
}

vec3 shade2()
{
    vec3 color = vec3(0.0);
	color = Ia2*Ka;

	vec3 D = normalize(Pl2-Pos);
	color += Il2*Kd*dot(N,D);

	vec3 V = normalize(-Pos);
	vec3 R = normalize(reflect(-D,N));
	float factor= clamp (dot(V,R),0.0001,1.0); 
	factor = pow (factor,n);
	color += Il2 * Ks * factor;

	color +=Ke;

    return color;
}

void main()
{
	
	Ka = texture (colorTex, texCoord).rgb;
    Kd = Ka;
	Ks = vec3(1.0);
	Ke = texture (emiTex, texCoord).rgb;

	N = normalize(outNormal);
	Pos = outPos;

	outColor = vec4(shade(), 1.0)+vec4(shade2(),1.0);
	


	
}


