#include "../Headers/Tweet.hpp"

int Tweet::numeroDeIdentificadores = 0;


Tweet::Tweet(){

}

Tweet::Tweet(Usuario* author,const string &descricao){
    Tweet::numeroDeIdentificadores++;
    time_t tempoAtual = time(nullptr);
    Data* dataPublicacao = localtime(&tempoAtual);
    dataPublicacao->tm_mon +=1;
    dataPublicacao->tm_year +=1900;
    setAuthor(author);
    setDescricao(descricao);
    setDataPublicacao(*dataPublicacao);
    setID(Tweet::numeroDeIdentificadores);
}

Tweet::Tweet(Usuario *author,const string &descricao, Data &dataPublicacao){
    Tweet::numeroDeIdentificadores++;
    setAuthor(author);
    setDescricao(descricao);
    setDataPublicacao(dataPublicacao);
    setID(Tweet::numeroDeIdentificadores);
}

Usuario* Tweet::getAuthor(){
    return this->author;
}

string Tweet::getDescricao(){
    return this->descricao;
}

Data Tweet::getDataPublicacao(){
    return this->dataPublicacao;
}

int Tweet::getID(){
    return this->id;
}

void Tweet::setID(const int &id){
    this->id = id;
}

void Tweet::setAuthor(Usuario* autor){
    this->author = autor;
}

void Tweet::setDescricao(string descricao){
    this->descricao = descricao;
}

void Tweet::setDataPublicacao(Data dataPublicacao){
    this->dataPublicacao = dataPublicacao;
}

int Tweet::getNumeroDeIdentificadores(){
    return Tweet::numeroDeIdentificadores;
}

void Tweet::setNumeroDeIdentificadores(const int &numero){
    Tweet::numeroDeIdentificadores = numero;
}

void Tweet::mostraTweet(){
    cout << "-----------------" << endl;
    getAuthor()->mostraUsuario();
    cout << "-----------------" << endl;
    cout << "Descrição : " << getDescricao() << endl;
    cout << "Data de Publicação : " << dataForString(getDataPublicacao());
}

string Tweet::dataForString(Data data){
    return to_string(data.tm_mday) + "/" + to_string(data.tm_mon) + "/" + to_string(data.tm_year)
    + "  " +  to_string(data.tm_hour) + ":" + to_string(data.tm_min) + ":" + to_string(data.tm_sec); 
}
