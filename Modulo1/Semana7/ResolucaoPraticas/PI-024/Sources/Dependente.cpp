#include "../Headers/Dependente.hpp"

int Dependente::numeroDeIdentificadores=0;

Dependente::Dependente(const string &nome , const string &cpf){
    Dependente::numeroDeIdentificadores++; 
    setNome(nome);
    setCpf(cpf);
    setId(Dependente::numeroDeIdentificadores);
}

void Dependente::setNumeroDeIdentificadores(int numero){
    Dependente::numeroDeIdentificadores = numero;
}

int Dependente::getNumeroDeIdentificadores(){
    return Dependente::numeroDeIdentificadores;
}

void Dependente::mostraDados(){
    cout << "ID   : " << getId() << endl;
    cout << "Nome : " << getNome() << endl;
    cout << "CPF  : " << getCpf() << endl;
}