#ifndef DESLOCAMENTO_HPP
#define DESLOCAMENTO_HPP

#include "Evento.hpp"

class Deslocamento : public Evento{

    static int numeroDeIdentificadores;

    public:

    Deslocamento();
    Deslocamento(const string &nome ,const string &descricao,const int &duracaoHoras , const double &preco);
    static int getNumeroDeIdentificadores();
    static void setNumeroDeIdentificadores(const int &numero);
    void mostraEvento();

};

#endif
