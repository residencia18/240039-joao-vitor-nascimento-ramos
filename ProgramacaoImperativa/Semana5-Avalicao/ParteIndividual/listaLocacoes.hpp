#ifndef LISTA_LOCACOES_HPP
#define LISTA_LOCACOES_HPP

#include<iostream>
#include<vector>
#include "locacao.hpp"
#include "cliente.hpp"
#include "veiculo.hpp"

using namespace std;

void insereLocacao( vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);
void excluiLocacao( vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);
void alteraLocacao( vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);
void listarLocacao( vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);

#endif
