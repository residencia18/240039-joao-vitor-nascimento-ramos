#ifndef LISTA_OCORRENCIAS_HPP
#define LISTA_OCORRENCIAS_HPP

#include <iostream>
#include <vector>
#include "locacao.hpp"
#include "cliente.hpp"
#include "veiculo.hpp"

using namespace std;

void insereOcorrencia(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);

void excluiOcorrencia(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);

void alteraOcorrencia(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao);

void listarOcorrenciaPorCliente(vector<Locacao> &listaLocacao);

void listarOcorrenciaPorVeiculo(vector<Locacao> &listaLocacao);

bool segundaMaiorQuePrimeira(DataHora umaData , DataHora outraData);

bool insereDataRetirada(Locacao temp , Ocorrencia &ocorrencia);

void registrarOcorrenciaPorVeiculo(vector<Locacao> &listaLocacao);

bool listasVaziasOcorrencia(vector<Locacao> &listaLocacoes);

#endif
