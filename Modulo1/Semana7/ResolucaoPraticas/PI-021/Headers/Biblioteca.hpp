#ifndef BIBLIOTECA_HPP
#define BIBLIOTECA_HPP
#include "Livro.hpp"
#include "Usuario.hpp"
#include <vector>

class Biblioteca
{
private:
    vector<Livro> listaLivros;
    vector<Usuario> listaUsuario;

public:

    vector<Livro>* getLivros();

    vector<Usuario>* getUsuarios();

    static void menuPrincipal(vector<Usuario> usuarios);

    static void mostraMenuPrincipal();
    
    static void mostraMenuSecundario();

    static void menuUsuarios();
    static void menuLivros();
    static void menuAlugueis();

    static void insereUsuario(vector<Usuario> &usuarios);
    static void encontraUsuario(vector<Usuario> &usuarios);
    static void listarUsuarios(vector<Usuario> &usuarios);
    static void excluirUsuario(vector<Usuario> &usuarios);



};

#endif