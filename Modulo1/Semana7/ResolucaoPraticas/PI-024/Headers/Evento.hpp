#ifndef EVENTO_HPP
#define EVENTO_HPP

#include<iostream>

using namespace std;

class Evento{

    int id;
    short int tipo;
    string nome;
    string descricao;
    int duracaoHoras;
    double preco;

    public:

        int getId();
        string getNome();
        string getDescricao();
        int getDuracaoHoras();
        double getPreco();
        short int getTipo();

        void setId(const int &id);
        void setNome(const string &nome);
        void setDescricao(const string &descricao);
        void setPreco(const double &preco);
        void setDuracaoHoras(const int &duracaoHoras);
        void setTipo(const short int &tipo);
        void mostraEvento();

};


#endif