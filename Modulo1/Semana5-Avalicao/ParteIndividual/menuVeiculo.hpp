#ifndef MENU_VEICULO_HPP
#define MENU_VEICULO_HPP
#include<iostream>
#include "listaVeiculos.hpp"    

using namespace std;

void mostraMenuVeiculo();
void menuVeiculo(vector<Veiculo> &listaVeiculo);

int recebeEscolhaVeiculo();
void realizaEscolhaVeiculo(const int &escolha, vector<Veiculo> &listaVeiculo);

#endif