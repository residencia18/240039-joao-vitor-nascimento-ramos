#include "../Headers/Livro.hpp"

int Livro::quantidadeDeIdentificadores = 0;

Livro::Livro(){}

Livro::Livro(const string &titulo, const string &author, const int &numPaginas) : titulo(titulo), author(author), numPaginas(numPaginas){
    
    Livro::quantidadeDeIdentificadores++;
    setId(Livro::quantidadeDeIdentificadores);
}

int Livro::getId(){
    return this->id;
}

void Livro::setId(int id){
    this->id = id;
}

string Livro::getAuthor()
{
    return this->author;
}

void Livro::setAuthor(string author)
{
    this->author = author;
}

string Livro::getTitulo()
{
    return this->titulo;
}

void Livro::setTitulo(string titulo)
{
    this->titulo = titulo;
}

int Livro::getNumPaginas()
{
    return this->numPaginas;
}

void Livro::setNumPaginas(int numPaginas)
{
    this->numPaginas = numPaginas;
}