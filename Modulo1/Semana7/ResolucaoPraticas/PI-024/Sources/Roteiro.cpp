#include "../Headers/Roteiro.hpp"

int Roteiro::numeroDeIdentificadores=0;


Roteiro::Roteiro(const string &nome , const string &descricao,const int &duracaoHoras , const double &preco){
    Roteiro::numeroDeIdentificadores++;
    setDescricao(descricao);
    setDuracaoHoras(duracaoHoras);
    setPreco(preco);
    setNome(nome);
    setTipo(1);
    setId(Roteiro::numeroDeIdentificadores);
}

void Roteiro::setNumeroDeIdentificadores(const int &numero){
    Roteiro::numeroDeIdentificadores = numero;
}

int Roteiro::getNumeroDeIdentificadores(){
    return Roteiro::numeroDeIdentificadores;
}


