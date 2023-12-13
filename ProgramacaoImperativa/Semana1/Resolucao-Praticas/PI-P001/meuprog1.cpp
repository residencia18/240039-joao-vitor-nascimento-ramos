#include<iostream>
#include<string>

using namespace std;

int main(){

    string nome_de_usuario;

    cout << "Insira o seu nome de usuário : ";
    getline(cin,nome_de_usuario);

    cout << "Bom dia " << nome_de_usuario << endl;


    return 0;
}