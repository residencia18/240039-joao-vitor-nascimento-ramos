#ifndef ESTRUTURAS_H
#define ESTRUTURAS_H

#include <map>
#include <cstring>
#include <vector>
#include <string> 
#include<iostream>

using namespace std;

typedef struct{
    string cpf;
    string nome;
    int idade;
}Passageiro;

typedef struct{
    int assento;
    Passageiro passageiro;
}Passagem;

typedef struct{
    int assentosDisponiveis = 40;
    vector<Passagem> passagensVendidas;
}Viagem;

typedef pair<string, int> Chave;
typedef map<Chave, Viagem> MapaDeViagens;

#endif