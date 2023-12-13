#ifndef MENU_LOCACAO_HPP
#define MENU_LOCACAO_HPP

#include<iostream>
#include "veiculo.hpp"
#include "cliente.hpp"
#include<vector>
#include "locacao.hpp"

using namespace std;

void menuLocacao(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);
void mostraMenuLocacao();
void realizaEscolhaLocacao(const int &escolha,vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);
int recebeEscolhaLocacao();
#endif