#include "BOX.h"
#include <IGL/IGlib.h>

#define GLM_FORCE_RADIANS
#define M_PI 3.14159265
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <iostream>


//Idenficadores de los objetos de la escena
int objId1 = -1;
int objId2 = 1;

//Matrices globales

glm::mat4 modelMat;

//variables globales
float xMov = 0.0f, yMov = 0.0f, zMov = 0.0f, pitch = 0.0f, yaw = 0.0f;

//Declaración de CB
void resizeFunc(int width, int height);
void idleFunc();
void keyboardFunc(unsigned char key, int x, int y);
void mouseFunc(int button, int state, int x, int y);
void mouseMotionFunc(int x, int y);


int main(int argc, char** argv)
{
	std::locale::global(std::locale("spanish"));// acentos ;)
	if (!IGlib::init("../shaders_P2/shader.v3.vert", "../shaders_P2/shader.v3.frag"))
		return -1;

	//CBs
	IGlib::setResizeCB(resizeFunc);
	IGlib::setIdleCB(idleFunc);
	IGlib::setKeyboardCB(keyboardFunc);
	IGlib::setMouseCB(mouseFunc);
	IGlib::setMouseMoveCB(mouseMotionFunc);

	//Se ajusta la cámara


	glm::mat4 proj = glm::mat4(1.0f);
	float far = 100;
	float near = 0.1f;
	float f = 1.0f / tan(M_PI / 6.0f);

	proj[0][0] = f;
	proj[1][1] = f;
	proj[2][2] = -(far + near) / (far - near);
	proj[2][3] = -1;
	proj[3][2] = (-2 * far*near) / (far - near);
	proj[3][3] = 0;

	glm::mat4 view = glm::mat4(1.0);

	view[3][2] = -6.0f;
	//Si no se da valor se cojen valores por defecto
	IGlib::setProjMat(proj);
	IGlib::setViewMat(view);

	//Creamos el objeto que vamos a visualizar
	objId1 = IGlib::createObj(cubeNTriangleIndex, cubeNVertex, cubeTriangleIndex,
		cubeVertexPos, cubeVertexColor, cubeVertexNormal, cubeVertexTexCoord, cubeVertexTangent);

	objId2 = IGlib::createObj(cubeNTriangleIndex, cubeNVertex, cubeTriangleIndex,
		cubeVertexPos, cubeVertexColor, cubeVertexNormal, cubeVertexTexCoord, cubeVertexTangent);

	modelMat = glm::mat4(1.0f);
	IGlib::setModelMat(objId1, modelMat);
	IGlib::setModelMat(objId2, modelMat);
	//Incluir texturas aquí.

	IGlib::addColorTex(objId1, "../img/color.png");
	IGlib::addColorTex(objId2, "../img/color.png");

	//CBs
	IGlib::setIdleCB(idleFunc);
	IGlib::setResizeCB(resizeFunc);
	IGlib::setKeyboardCB(keyboardFunc);
	IGlib::setMouseCB(mouseFunc);

	//Mainloop
	IGlib::mainLoop();
	IGlib::destroy();
	return 0;
}

void resizeFunc(int width, int height)
{
	//Ajusta el aspect ratio al tamaño de la venta
	float w = width;
	float h = height;
	float ratio = w / h;
	float ratio2 = h / w;

	glm::mat4 proj = glm::mat4(1.0f);

	float far = 100;
	float near = 0.1f;
	float f = 1.0f / tan(M_PI / 6.0f);

	float c = 1.0f / (far - near);

	if (w < h) {
		proj[0][0] = f;
		proj[1][1] = f * ratio;
	}
	else {
		proj[0][0] = f * ratio2;
		proj[1][1] = f;
	}
	proj[2][2] = -(far + near) / (far - near);
	proj[2][3] = -1;
	proj[3][2] = (-2 * far*near) / (far - near);
	proj[3][3] = 0;

	IGlib::setProjMat(proj);
}

void idleFunc()
{
	static float angle = 0.0f;

	if (angle > 2 * M_PI)
		angle = 0.0f;
	else
		angle = angle + 0.1f;
	//modelMat = glm::rotate(glm::mat4(1), angle, glm::vec3(1, 1, 0));
	IGlib::setModelMat(objId1, modelMat);


	//BOX2
	static float angle2 = 0.0f;
	angle2 = (angle2 < 2.0f * M_PI) ? angle2 + 0.003f : 0.0f;
	float x = sin(angle2) * 10;
	float z = cos(angle2) * 10;
	glm::mat4 modelMat2(1.0f);
	modelMat2[3].x = x;
	modelMat2[3].z = z;

	modelMat2 = glm::translate(glm::rotate(modelMat2, angle2, glm::vec3(0.0f, 1.0f, 0.0f)), glm::vec3(5.0f, 0.0f, 0.0f));
	modelMat2 = glm::rotate(modelMat2, angle2, glm::vec3(0.0f, 1.0f, 0.0f));

	IGlib::setModelMat(objId2, modelMat2);
}

void keyboardFunc(unsigned char key, int x, int y)
{
	std::cout << "Se ha pulsado la tecla " << key << std::endl << std::endl;

	glm::mat4 view = glm::mat4(1.0);

	view[3][2] = -6.0f;

	glm::mat4 view2;

	switch (key)
	{
		//Teclas Movimiento cámara
	case 'z':
		yMov++;
		break;
	case 'a':
		xMov++;
		break;
	case 'x':
		yMov--;
		break;
	case 'd':
		xMov--;
		break;
	case 'w':
		zMov++;
		break;
	case 's':
		zMov--;
		break;

		//Teclas Cabeceo cámara
	case 'i':
		pitch += 0.025f;
		break;
	case 'k':
		pitch -= 0.025f;
		break;
	case 'j':
		yaw += 0.025f;
		break;
	case 'l':
		yaw -= 0.025f;
		break;
	default:
		break;
	}

	//////////////////////
	//roll can be removed from here. because is not actually used in FPS camera
	glm::mat4 matPitch = glm::mat4(1.0f);//identity matrix
	glm::mat4 matYaw = glm::mat4(1.0f);//identity matrix

	//roll, pitch and yaw are used to store our angles in our class
	matPitch = glm::rotate(matPitch, pitch, glm::vec3(1.0f, 0.0f, 0.0f));
	matYaw = glm::rotate(matYaw, yaw, glm::vec3(0.0f, 1.0f, 0.0f));

	//order matters
	glm::mat4 rotate = matPitch * matYaw;

	glm::mat4 translate = glm::translate(view, glm::vec3(xMov, yMov, zMov));
	////////////////////////

	view = rotate * translate;
	IGlib::setViewMat(view);
}

void mouseFunc(int button, int state, int x, int y)
{
	if (state == 0)
		std::cout << "Se ha pulsado el botón ";
	else
		std::cout << "Se ha soltado el botón ";

	if (button == 0) std::cout << "de la izquierda del ratón " << std::endl;
	if (button == 1) std::cout << "central del ratón " << std::endl;
	if (button == 2) std::cout << "de la derecha del ratón " << std::endl;

	std::cout << "en la posición " << x << " " << y << std::endl << std::endl;
}

void mouseMotionFunc(int x, int y)
{
}