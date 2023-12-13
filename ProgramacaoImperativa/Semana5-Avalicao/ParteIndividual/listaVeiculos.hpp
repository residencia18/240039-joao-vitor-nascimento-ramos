#ifndef LISTA_VEICULO_HPP
#define LISTA_VEICULO_HPP

#include<iostream>
#include<vector>
#include "veiculo.hpp"

using namespace std;

void insereVeiculo(vector<Veiculo> &listaVeiculos);
void excluiVeiculo(vector<Veiculo> &listaVeiculos);
void alteraVeiculo(vector<Veiculo> &listaVeiculos);
void listarVeiculos(vector<Veiculo> &listaVeiculos);
void localizaVeiculo(vector<Veiculo> &listaVeiculos);

#endif
