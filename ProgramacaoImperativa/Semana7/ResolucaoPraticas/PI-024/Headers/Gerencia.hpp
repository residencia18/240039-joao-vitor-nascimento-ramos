#ifndef GERENCIA_HPP
#define GERENCIA_HPP

#include "Cliente.hpp"
#include "Pacote.hpp"

class Gerencia{

    static int numeroDeIdentificadores;

    int id;
    Cliente* cliente;
    Pacote* pacote;

    public:

        Gerencia();
        Gerencia(Cliente* cliente, Pacote* pacote);
        int getId();
        Cliente* getCliente();
        Pacote* getPacote();
        
        void setId(const int &id);
        void setCliente(Cliente* cliente);
        void setPacote(Pacote* pacote);
        void mostraGerencia();

        
        static int getNumeroDeIdentificadores();
        static void setNumeroDeIdentificadores(const int &numero);
};


#endif