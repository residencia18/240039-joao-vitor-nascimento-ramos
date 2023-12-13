#ifndef DEPENDENTE_HPP
#define DEPENDENTE_HPP

#include "Pessoa.hpp"
#include<vector>

class Dependente: public Pessoa{


    private:
    
        static int numeroDeIdentificadores;

    public:
    
        Dependente();
        Dependente(const string &nome , const string &cpf);
        static int getNumeroDeIdentificadores();
        static void setNumeroDeIdentificadores(int numero);
        void mostraDados();


};


#endif