#ifndef TWEET_HPP
#define TWEET_HPP

#include "Usuario.hpp"
#include<ctime>

typedef struct tm Data;

using namespace std;

class Usuario;

class Tweet{

    static int numeroDeIdentificadores;

    Usuario* author;
    string descricao;
    Data dataPublicacao;
    int id;

    public:

        Tweet();
        Tweet(Usuario *author,const string &descricao);
        Tweet(Usuario *author,const string &descricao, Data &dataPublicacao);

        Usuario* getAuthor();
        string getDescricao();
        Data getDataPublicacao();
        int getID();
        void setID(const int &id);

        void setAuthor(Usuario* usuario);
        void setDescricao(string descricao);
        void setDataPublicacao(Data dataPublicacao);
        static void setNumeroDeIdentificadores(const int &id);
        static int getNumeroDeIdentificadores();
        void mostraTweet();

        private:
            string dataForString(Data data);


};


#endif