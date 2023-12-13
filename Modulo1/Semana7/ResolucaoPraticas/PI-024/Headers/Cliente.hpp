#ifndef CLIENTE_HPP
#define CLIENTE_HPP

#include "Dependente.hpp"
#include<vector>

class Cliente: public Pessoa{

    private:
        
        static int numeroDeIdentificadores; // quantidade de id
        vector<Dependente> dependentes;

    public:

        Cliente();
        Cliente(const string &nome , const string &cpf);
        static int getNumeroDeIdentificadores();
        static void setNumeroDeIdentificadores(const int &numero);
        vector<Dependente>* getListaDependentes();
        void mostraDados();


};


#endif