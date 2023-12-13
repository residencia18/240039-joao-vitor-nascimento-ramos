#include "../Headers/Evento.hpp"

int Evento::getId(){
    return this->id;
}

string Evento::getDescricao(){
    return this->descricao;
}

int Evento::getDuracaoHoras(){
    return this->duracaoHoras;
}

double Evento::getPreco(){
    return this->preco;
}

string Evento::getNome(){
    return this->nome;
}

short int Evento::getTipo(){
    return this->tipo;
}

void Evento::setId(const int &id){
    this->id = id;
}

void Evento::setDescricao(const string &descricao){
    this->descricao = descricao;
}

void Evento::setPreco(const double &preco){
    this->preco = preco;
}

void Evento::setDuracaoHoras(const int &duracaoHoras){
    this->duracaoHoras = duracaoHoras;
}

void Evento::setNome(const string &nome){
    this->nome = nome;
}

void Evento::setTipo(const short int &tipo){
    this->tipo = tipo;
}


void Evento::mostraEvento(){
    cout << "Id : " << getId() <<  endl;
    cout << "Nome : " << getNome() << endl;
    switch (getTipo())
    {
    case 1:
        cout << "Tipo : Roteiro " << endl;
        break;
    case 2:
        cout << "Tipo : Deslocamento " << endl;
        break;
    case 3:
        cout << "Tipo : Pernoite " << endl;
        break;
    default:
        break;
    }
    cout << "Descrição : " << getDescricao() << endl;
    cout << "Duração : " << getDuracaoHoras() << " hrs" << endl;
    cout << "Preço : " << getPreco() << " R$" << endl;

}