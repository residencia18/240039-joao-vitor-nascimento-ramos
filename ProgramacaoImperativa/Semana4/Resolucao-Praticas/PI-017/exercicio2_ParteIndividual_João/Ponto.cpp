#include "Ponto.hpp"
#include<cmath>

Ponto::Ponto(float x , float y){
    setX(x);
    setY(y);
}

float Ponto::getX(){
    return this->x;
}

void Ponto::setX(float x){
    this->x = x;
}

float Ponto::getY(){
    return this->y;
}

void Ponto::setY(float y){
    this->y = y;
}

void Ponto::setCoordenadas(float x , float y){
    setY(y);
    setX(x);
}

double Ponto::calcularDistancia(){
    return sqrt(y*y + x*x);
}
