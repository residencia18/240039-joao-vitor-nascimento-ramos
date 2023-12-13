#ifndef REDE_SOCIAL_HPP
#define REDE_SOCIAL_HPP

#include "Tweet.hpp"

class RedeSocial{

    vector<Usuario> listaUsuarios;

    public:

    vector<Usuario>* getListaDeUsuarios();

    static void menuPrincipal(vector<Usuario> &listaUsuarios);
    static void mostraMenuPrincipal();
    static void cadastraUsuario(vector<Usuario> &listaUsuarios);
    static void listarUsuarios(vector<Usuario> &listaUsuarios);
    static void pause();
    static void menuUsuario(Usuario* usuario,vector<Usuario> &listaUsuarios);
    static void mostraMenuUsuario();
    static void excluiUsuario(vector<Usuario> &listaUsuarios);

    static void loginUsuario(vector<Usuario> &listaUsuarios);

    static void seguirUsuario(Usuario* usuario,vector<Usuario> &listaUsuarios);
    static void listarSeguidores(Usuario* usuario);
    static void listarSeguindo(Usuario* usuario);
    static void postarTweet(Usuario* usuario);
    static void deixarDeSeguir(Usuario* usuario,vector<Usuario> &listaUsuarios);
    static void listaDeTweets(Usuario* usuario);
    static void mostraFeed(Usuario* usuario);

    static void insereUsuarioNoBanco(Usuario* usuario);
    static void atualizaBancoDeUsuarios(vector<Usuario> &listaUsuarios);
    static void recuperaUsuarios(vector<Usuario> &listaUsuarios);

    static void insereTweetNoBanco(Tweet* tweet);
    static void atualizaBancoDeTweets(vector<Usuario> &listaUsuarios);
    static void recuperaTweets(vector<Usuario> &listaUsuarios);
    static void atualizaSeguindoNoBanco(vector<Usuario> &listaUsuarios);
    static void recuperaSeguindo(vector<Usuario> &listaUsuarios);
    private:

    static Usuario preencheUsuario();
};


#endif