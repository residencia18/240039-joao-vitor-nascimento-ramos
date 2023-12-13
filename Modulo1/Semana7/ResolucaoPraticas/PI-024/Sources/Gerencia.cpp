#include "../Headers/Gerencia.hpp"

int Gerencia::numeroDeIdentificadores=0;

Gerencia::Gerencia(){

}

Gerencia::Gerencia(Cliente* cliente, Pacote* pacote){
    Gerencia::numeroDeIdentificadores++;
    setCliente(cliente);
    setPacote(pacote);
    setId(Gerencia::numeroDeIdentificadores);
}

int Gerencia::getId(){
    return this->id;
}

void Gerencia::setId(const int &id){
    this->id = id;
}

Cliente* Gerencia::getCliente(){
    return this->cliente;
}

Pacote* Gerencia::getPacote(){
    return this->pacote;
}

void Gerencia::setCliente(Cliente* cliente){
    this->cliente = cliente;
}

void Gerencia::setPacote(Pacote* pacote){
    this->pacote = pacote;
}

void Gerencia::mostraGerencia(){
    cout << "------CLIENTE-------" << endl;
    cliente->mostraDados();
    cout << "------PACOTE-------" << endl;
    pacote->mostraPacote();
}

void Gerencia::setNumeroDeIdentificadores(const int &numero){
    Gerencia::numeroDeIdentificadores = numero;
}

int Gerencia::getNumeroDeIdentificadores(){
    return Gerencia::numeroDeIdentificadores;
}