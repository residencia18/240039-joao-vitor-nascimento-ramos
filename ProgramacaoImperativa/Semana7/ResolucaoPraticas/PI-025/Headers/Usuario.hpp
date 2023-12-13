#ifndef USUARIO_HPP
#define USUARIO_HPP

#include<iostream>
#include<vector>
#include "Tweet.hpp"

class Tweet;

using namespace std;

class Usuario{

    static int numeroDeIdentificadores;

    int id;
    string nome;
    string user;
    string senha;
    vector<Usuario*> listaDeSeguindo;
    vector<Usuario*> listaDeSeguidores;
    vector<Tweet> listaDeTweets;
    
    public:

        Usuario();
        Usuario(const string &nome,const string &user , const string &senha);
        string getNome();
        string getUser();
        string getSenha();
        int getID();
        void setNome(const string &nome);
        void setUser(const string &user);
        void setSenha(const string &senha);
        void setID(const int &id);
        static void setNumeroDeIdentificadores(const int &id);
        static int getNumeroDeIdentificadores();
        vector<Usuario*>* getListaDeSeguindo();
        vector<Usuario*>* getListaDeSeguidores();
        vector<Tweet>* getDeTweets();
        void postarTweet(Tweet tweet);
        void seguir(Usuario* usuario);
        void receber_feed();

        void mostraUsuario();

    private:





};


#endif