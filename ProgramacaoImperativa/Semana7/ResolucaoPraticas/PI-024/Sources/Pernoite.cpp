#include "../Headers/Pernoite.hpp"

int Pernoite::numeroDeIdentificadores=0;


Pernoite::Pernoite(const string &nome , const string &descricao,const int &duracaoHoras , const double &preco){
    Pernoite::numeroDeIdentificadores++;
    setDescricao(descricao);
    setDuracaoHoras(duracaoHoras);
    setPreco(preco);
    setNome(nome);
    setTipo(3);
    setId(Pernoite::numeroDeIdentificadores);
}

void Pernoite::mostraEvento(){
    cout << "Id : " << getId() <<  endl;
    cout << "Nome : " << getNome() << endl;
    cout << "Tipo : Pernoite " << endl;
    cout << "Descrição : " << getDescricao() << endl;
    cout << "Duração : " << getDuracaoHoras() << " hrs" << endl;
    cout << "Preço : " << getPreco() << " R$" << endl;

}

void Pernoite::setNumeroDeIdentificadores(const int &numero){
    Pernoite::numeroDeIdentificadores = numero;
}

int Pernoite::getNumeroDeIdentificadores(){
    return Pernoite::numeroDeIdentificadores;
}

