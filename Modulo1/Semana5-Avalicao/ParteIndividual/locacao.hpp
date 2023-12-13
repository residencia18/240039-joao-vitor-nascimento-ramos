#ifndef LOCACAO_HPP
#define LOCACAO_HPP

#include<iostream>
#include"dataHora.hpp"

using namespace std;

typedef struct{
    bool realizada;
    DataHora retirada;
    DataHora entrega;
}Locacao;

#endif  