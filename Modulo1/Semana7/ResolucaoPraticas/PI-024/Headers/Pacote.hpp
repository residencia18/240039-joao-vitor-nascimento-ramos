#ifndef PACOTES_HPP
#define PACOTES_HPP

#include "Pernoite.hpp"
#include "Roteiro.hpp"
#include "Deslocamento.hpp"

#include<vector>

class Pacote{

    static int numeroDeIdentificadores;

    int id;
    int qtdRoteiros;
    int qtdDeslocamentos;
    int qtdPernoite;
    string nome;
    vector<Evento*> listaEventos;

    public:

        vector<Evento*>* getListaEventos();

        Pacote();
        Pacote(const string &nome);

        int getId();
        string getNome();
        int getQtdRoteiros();
        int getQtdDeslocamentos();
        int getQtdPernoite();

        void setID(const int &id);
        void setNome(const string &nome);
        void setQtdRoteiros(const int &qtd);
        void setQtdDeslocamentos(const int &qtd);
        void setQtdPernoite(const int &qtd);

        static int getNumeroDeIdentificadores();
        static void setNumeroDeIdentificadores(const int &numero);
        void mostraPacote();


};


#endif