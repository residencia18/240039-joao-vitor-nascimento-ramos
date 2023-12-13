#ifndef MENU_OCORRENCIA_HPP
#define MENU_OCORRENCIA_HPP

#include <iostream>
#include "cliente.hpp"
#include "veiculo.hpp"
#include "locacao.hpp"
#include <vector>
using namespace std;

void mostraMenuOcorrencia();

void menuOcorrencia(vector<Cliente> &listaCliente, vector<Veiculo> &listaVeiculo, vector<Locacao> &listaLocacao);

int recebeEscolhaOcorrencia();

void realizaEscolhaOcorrencia(const int &escolha, vector<Cliente> &listaCliente, vector<Veiculo> &listaVeiculo, vector<Locacao> &listaLocacao);

#endif