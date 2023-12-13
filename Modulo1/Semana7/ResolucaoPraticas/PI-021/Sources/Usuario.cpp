#include "../Headers/Usuario.hpp"

int Usuario::quantidadeDeIdentificadores = 0;

Usuario::Usuario() {}

Usuario::Usuario(string nome, string endereco, string cpf, string telefone) : nome(nome), endereco(endereco), cpf(cpf), telefone(telefone)
{

    Usuario::quantidadeDeIdentificadores++;
    setId(Usuario::quantidadeDeIdentificadores);
}

int Usuario::getId()
{
    return this->id;
}

void Usuario::setId(int id)
{
    this->id = id;
}

string Usuario::getNome()
{
    return this->nome;
}

void Usuario::setNome(string nome)
{
    this->nome = nome;
}

string Usuario::getCPF()
{
    return this->cpf;
}

void Usuario::setCPF(string cpf)
{
    this->cpf = cpf;
}

string Usuario::getEndereco()
{
    return this->endereco;
}

string Usuario::getTelefone()
{
    return this->telefone;
}

void Usuario::setTelefone(string telefone)
{
    this->telefone = telefone;
}

void Usuario::setEndereco(string endereco)
{
    this->endereco = endereco;
}

void Usuario::mostrarUsuario()
{
    cout << "\n\t==========LISTA DE USUARIOS==========\n";

    cout << "\n\tNome: " << getNome();
    cout << "\n\tCPF: " << getCPF();
    cout << "\n\tEndereço: " << getEndereco();
    cout << "\n\tTelefone: " << getTelefone();
    cout << "\n\t====================================\n";
}