#include<iostream>
#include<cmath>

typedef struct{
    float x,y;
}Ponto;

float quadrado(float num);
void distancia(Ponto Ponto1, Ponto Ponto2);

int main(){

    Ponto p1={1,2};
    Ponto p2={4,5};

    distancia(p1,p2);

}

void distancia(Ponto Ponto1, Ponto Ponto2){
    float d = sqrt(quadrado(Ponto1.x-Ponto2.x) + quadrado(Ponto1.y-Ponto2.y));
    printf("Distancia entre {%.2f,%.2f} e {%.2f,%.2f} = %.2f\n",Ponto1.x,Ponto1.y,Ponto2.x,Ponto2.y,d);
}

float quadrado(float num){
    return num*num;
}
