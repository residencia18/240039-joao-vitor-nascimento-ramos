#ifndef PERNOITE_HPP
#define PERNOITE_HPP

#include "Evento.hpp"

class Pernoite : public Evento{

    static int numeroDeIdentificadores;

    public:

    Pernoite();
    Pernoite(const string &nome ,const string &descricao,const int &duracaoHoras , const double &preco);
    static int getNumeroDeIdentificadores();
    static void setNumeroDeIdentificadores(const int &numero);
    void mostraEvento();

};

#endif
