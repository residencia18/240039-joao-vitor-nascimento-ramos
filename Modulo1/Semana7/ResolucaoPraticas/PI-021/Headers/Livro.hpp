#ifndef LIVRO_HPP
#define LIVRO_HPP

#include <iostream>

using namespace std;

class Livro
{

private:

    static int quantidadeDeIdentificadores;
    int id;
    string titulo;
    string author;
    int numPaginas;

public:

    Livro();

    Livro(const string &titulo, const string &author, const int &numPaginas);

    int getId();
    void setId(int id);

    string getTitulo();
    void setTitulo(string titulo);

    string getAuthor();
    void setAuthor(string author);

    int getNumPaginas();
    void setNumPaginas(int numPaginas);

};

#endif
