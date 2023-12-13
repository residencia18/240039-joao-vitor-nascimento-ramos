#ifndef PESSOA_HPP
#define PESSOA_HPP

#include<iostream>

using namespace std;

class Pessoa{


    private:
        int id;
        string nome;
        string cpf;


    public:

        int getId();
        string getNome();
        string getCpf();

        void setId(const int &id);
        void setNome(const string &nome);
        void setCpf(const string &cpf);

        virtual void mostraDados()=0;

};



#endif