#ifndef ROTEIRO_HPP
#define ROTEIRO_HPP

#include "Evento.hpp"

class Roteiro : public Evento{

    static int numeroDeIdentificadores;

    public:

    Roteiro();
    Roteiro(const string &nome ,const string &descricao,const int &duracaoHoras , const double &preco);
    static int getNumeroDeIdentificadores();
    static void setNumeroDeIdentificadores(const int &numero);


};

#endif
