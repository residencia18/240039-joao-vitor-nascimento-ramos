#include "../Headers/Deslocamento.hpp"

int Deslocamento::numeroDeIdentificadores=0;


Deslocamento::Deslocamento(const string &nome , const string &descricao,const int &duracaoHoras , const double &preco){
    Deslocamento::numeroDeIdentificadores++;
    setDescricao(descricao);
    setDuracaoHoras(duracaoHoras);
    setPreco(preco);
    setNome(nome);
    setTipo(2);
    setId(Deslocamento::numeroDeIdentificadores);
}

void Deslocamento::mostraEvento(){
    cout << "Id : " << getId() <<  endl;
    cout << "Nome : " << getNome() << endl;
    cout << "Tipo : Deslocamento " << endl;
    cout << "Descrição : " << getDescricao() << endl;
    cout << "Duração : " << getDuracaoHoras() << " hrs" << endl;
    cout << "Preço : " << getPreco() << " R$" << endl;

}

void Deslocamento::setNumeroDeIdentificadores(const int &numero){
    Deslocamento::numeroDeIdentificadores = numero;
}

int Deslocamento::getNumeroDeIdentificadores(){
    return Deslocamento::numeroDeIdentificadores;
}


