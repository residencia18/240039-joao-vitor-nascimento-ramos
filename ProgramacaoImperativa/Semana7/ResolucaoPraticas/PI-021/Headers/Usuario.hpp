#ifndef USUARIO_HPP
#define USUARIO_HPP

#include <iostream>

using namespace std;

class Usuario
{
private:
    static int quantidadeDeIdentificadores;
    int id;
    string nome;
    string cpf;
    string endereco;
    string telefone;

public:
    Usuario();

    Usuario(string nome, string cpf, string endereco, string telefone);

    int getId();
    void setId(int id);

    string getNome();
    void setNome(string nome);

    string getCPF();
    void setCPF(string cpf);

    string getEndereco();
    void setEndereco(string endereco);

    string getTelefone();

    void setTelefone(string telefone);

    void mostrarUsuario();
};
#endif