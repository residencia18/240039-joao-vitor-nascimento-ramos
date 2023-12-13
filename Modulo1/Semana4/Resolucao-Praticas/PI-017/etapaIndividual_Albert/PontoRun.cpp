#include <iostream>
#include "Ponto.h"

using namespace std;

int main(){
  
  Ponto p1(3, 4);
  double distancia = p1.calcularDistancia();

  p1.limpaTela();
  cout << "\n\t==========ESCREVER COORDENADAS DO PONTO==========\n"<< endl;
  
  cout << "\n\tPonto p1:";
  p1.escrevePontos(distancia);

  Ponto p2(-2, 7);
  p2.setCoordenadas(1, 1);
  distancia = p2.calcularDistancia();

  cout << "\n\tPonto p2:";
  p2.escrevePontos(distancia);

  Ponto p3(0, 3);
  Ponto p4(4, 0);
  double distancia_p3 = p3.calcularDistancia();
  double distancia_p4 = p4.calcularDistancia();

  printf("\n\tPonto p3\n\tPonto p4:\n\tResposta: A distância do ponto p3(%.0f, %.0f) até a origem é %.1f e do ponto p4(%.0f, %.0f) até a origem é %.1f\n\n", p3.getX(), p3.getY(), distancia_p3, p4.getX(), p4.getY(), distancia_p4);

  Ponto pontos[3];
  pontos[0].setCoordenadas(2, 2);
  pontos[1].setCoordenadas(-1, 5);
  pontos[2].setCoordenadas(0, 0);

  pontos->listarPontos(pontos);

  Ponto p5;
  cout << "\n\tPonto p5:\n\tCoordenadas do ponto p5: (" << p5.getX() << ", " << p5.getY() << ")" << endl;

  p5.setCoordenadas(8, -3);
  cout << "\n\tNovas coordenadas do ponto p5: (" << p5.getX() << ", " << p5.getY() << ")" << endl;

  cout << "\n\tResposta: Coordenadas do ponto p5: (0, 0)\n\tNovas coordenadas do ponto p5: (8, -3)\n\n";
  
}