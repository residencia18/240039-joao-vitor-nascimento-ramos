#include "../Headers/Pacote.hpp"

int Pacote::numeroDeIdentificadores = 0;

Pacote::Pacote(){

}

Pacote::Pacote(const string &nome){
    Pacote::numeroDeIdentificadores++;
    setNome(nome);
    setID(Pacote::numeroDeIdentificadores);
    setQtdRoteiros(0);
    setQtdPernoite(0);
    setQtdDeslocamentos(0);
}

vector<Evento*>* Pacote::getListaEventos(){
    return &(this->listaEventos);
}

void Pacote::setNumeroDeIdentificadores(const int &numero){
    Pacote::numeroDeIdentificadores = numero;
}

int Pacote::getNumeroDeIdentificadores(){
    return Pacote::numeroDeIdentificadores;
}

int Pacote::getId(){
    return this->id;
}

string Pacote::getNome(){
    return this->nome;
}

int Pacote::getQtdRoteiros(){
    return this->qtdRoteiros;
}

int Pacote::getQtdDeslocamentos(){
    return this->qtdDeslocamentos;
}

int Pacote::getQtdPernoite(){
    return this->qtdPernoite;
}

void Pacote::setID(const int &id){
    this->id = id;
}

void Pacote::setNome(const string &nome){
    this->nome = nome;
}

void Pacote::setQtdRoteiros(const int &qtd){
    this->qtdRoteiros = qtd;
}

void Pacote::setQtdDeslocamentos(const int &qtd){
    this->qtdDeslocamentos = qtd;
}

void Pacote::setQtdPernoite(const int &qtd){
    this->qtdPernoite = qtd;
}

void Pacote::mostraPacote(){
    cout << "ID : " << getId() << endl;
    cout << "nome : " << getNome() << endl;
}

