#include "../Headers/Cliente.hpp"

int Cliente::numeroDeIdentificadores=0;

Cliente::Cliente(){

}

Cliente::Cliente(const string &nome , const string &cpf){
    Cliente::numeroDeIdentificadores++; 
    setNome(nome);
    setCpf(cpf);
    setId(Cliente::numeroDeIdentificadores);
}


void Cliente::setNumeroDeIdentificadores(const int &numero){
    Cliente::numeroDeIdentificadores = numero;
}

int Cliente::getNumeroDeIdentificadores(){
    return Cliente::numeroDeIdentificadores;
}

vector<Dependente>* Cliente::getListaDependentes(){
    return &(this->dependentes);
}

void Cliente::mostraDados(){
    cout << "ID   : " << getId() << endl;
    cout << "Nome : " << getNome() << endl;
    cout << "CPF  : " << getCpf() << endl;
}