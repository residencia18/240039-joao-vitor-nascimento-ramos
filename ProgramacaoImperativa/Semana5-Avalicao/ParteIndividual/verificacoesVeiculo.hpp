#ifndef VERIFICACOES_VEICULO_HPP
#define VERIFICACOES_VEICULO_HPP

#include<iostream>
#include "dataHora.hpp"

using namespace std;

bool verificaRenavan(const string &renavam);
bool verificaPlaca(const string &placa);
bool verificaRetirada(const DataHora &retirada);
bool verificaEntrega(const DataHora &retirada,const DataHora &entrega);
bool verificaLoja(const string &Loja);

#endif


