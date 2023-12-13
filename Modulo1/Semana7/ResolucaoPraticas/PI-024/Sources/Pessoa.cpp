#include "../Headers/Pessoa.hpp"

int Pessoa::getId(){
    return this->id;
}

string Pessoa::getCpf(){
    return this->cpf;
}

string Pessoa::getNome(){
    return this->nome;
}

void Pessoa::setId(const int &id){
    this->id = id;
}

void Pessoa::setNome(const string &nome){
    this->nome = nome;
}

void Pessoa::setCpf(const string &cpf){
    this->cpf = cpf;
}

