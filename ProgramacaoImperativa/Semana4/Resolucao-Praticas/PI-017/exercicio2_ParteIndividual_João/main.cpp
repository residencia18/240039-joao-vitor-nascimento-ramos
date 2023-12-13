#include "Ponto.hpp"
#include<iostream>

using namespace std;

int main(){

    Ponto p2(-2, 7);
    p2.setCoordenadas(1, 1);
    double distancia = p2.calcularDistancia();
    cout << distancia;
// Resposta: A distância do ponto (1, 1) até a origem é aproximadamente 1.4142


    return 0;
}