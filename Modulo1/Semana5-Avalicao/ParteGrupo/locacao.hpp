#ifndef LOCACAO_HPP
#define LOCACAO_HPP

#include <iostream>
#include "dataHora.hpp"
#include "ocorrencia.hpp"
#include "veiculo.hpp"
#include "cliente.hpp"
#include<vector>

using namespace std;

typedef struct
{
    Cliente cliente;
    Veiculo veiculo;
    bool realizada;
    DataHora retirada;
    DataHora entrega;
    vector<Ocorrencia> ocorrencias;
} Locacao;

#endif