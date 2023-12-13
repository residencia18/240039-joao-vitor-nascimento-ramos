#ifndef VERIFICACOES_CLIENTE_HPP
#define VERIFICACOES_CLIENTE_HPP
#include<iostream>
#include "data.hpp"
#include "utils.hpp"
using namespace std;

bool verificaCPF(const string &cpf);
bool verificaCNH(const string &cnh);
bool verificaNome(const string &nome);
bool verificaDataNascimento(const Data &dataNascimento);

#endif
