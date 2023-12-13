#include "../Headers/Usuario.hpp"

int Usuario::numeroDeIdentificadores = 0;

Usuario::Usuario(){

}

Usuario::Usuario(const string &nome,const string &user, const string &senha) : nome(nome), user(user) , senha(senha) {
    Usuario::numeroDeIdentificadores++;
    setID(Usuario::numeroDeIdentificadores);
}

vector<Usuario*>* Usuario::getListaDeSeguindo(){
    return &(this->listaDeSeguindo);
}

vector<Usuario*>* Usuario::getListaDeSeguidores(){
    return &(this->listaDeSeguidores);
}

vector<Tweet>* Usuario::getDeTweets(){
    return &(this->listaDeTweets);
}

int Usuario::getID(){
    return this->id;
}

int Usuario::getNumeroDeIdentificadores(){
    return Usuario::numeroDeIdentificadores;
}

string Usuario::getSenha(){
    return this->senha;
}

void Usuario::setNumeroDeIdentificadores(const int &numero){
    Usuario::numeroDeIdentificadores = numero;
}

void Usuario::setNome(const string &nome){
    this->nome = nome;
}

void Usuario::setUser(const string &user){
    this->user = user;
}

void Usuario::setID(const int &id){
    this->id = id;
}

string Usuario::getNome(){
    return this->nome;
}

string Usuario::getUser(){
    return this->user;
}

void Usuario::postarTweet(Tweet tweet){
    getDeTweets()->push_back(tweet);
}

void Usuario::seguir(Usuario* usuario){
    getListaDeSeguindo()->push_back(usuario);
}

void Usuario::mostraUsuario(){
    cout << "ID   : " << getID() << endl;
    cout << "User : " << getUser() << endl;
    cout << "Nome : " << getNome() << endl;
}


void Usuario::receber_feed(){
    system("clear || cls");
    for(auto it=getListaDeSeguindo()->begin() ; it!=getListaDeSeguindo()->end() ; it++){
        for(auto itera=(*it)->getDeTweets()->begin() ; itera!=(*it)->getDeTweets()->end() ; itera++){
            itera->mostraTweet();
            cout << "---------------------" << endl;
        }
    }

    cout << "----------FIM DO FEED----------" << endl;
}