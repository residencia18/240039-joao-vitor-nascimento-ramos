#include "verificacoesCliente.hpp"
#include<ctime>
#include<cctype>

bool verificaCPF(const string &cpf){

    if (cpf.length() != 11){
        cout << "CPF inválido" << endl;
        pause();
        return false;
    }

    for (char c : cpf) {
        if (!isdigit(c))
            return false;
    }

    int soma = 0;
    for (int i = 0; i < 9; i++) {
        soma += (cpf[i] - '0') * (10 - i);
    }
    int primeiroDigito = 11 - (soma % 11);
    if (primeiroDigito == 10 || primeiroDigito == 11){
        primeiroDigito = 0;
    }

    if (cpf[9] - '0' != primeiroDigito){
        cout << "CPF inválido" << endl;
        pause();
        return false;
    }

    soma = 0;
    for(int i = 0; i < 10; i++) {
        soma += (cpf[i] - '0') * (11 - i);
    }
    int segundoDigito = 11 - (soma % 11);
    if(segundoDigito == 10 || segundoDigito == 11){
        segundoDigito = 0;
    }

    if (cpf[10] - '0' != segundoDigito){
        cout << "CPF inválido" << endl;
        pause();
        return false;
    }

    return true;
}

bool verificaCNH(const string &cnh){
    if (cnh.length() != 10){
        cout << "CNH inválida" << endl;
        pause();
        return false;
    }

    for (char c : cnh) {
        if (!isdigit(c))
            return false;
    }
    return true;
}

bool verificaDataNascimento(const Data &dataNascimento){

    time_t tempoAtual = time(0);
    tm *dataHoraAtual = localtime(&tempoAtual);

    int diaAtual = dataHoraAtual->tm_mday;
    int mesAtual = dataHoraAtual->tm_mon + 1;  
    int anoAtual = dataHoraAtual->tm_year + 1900;  

    int idade = anoAtual - dataNascimento.ano;
    if (mesAtual < dataNascimento.mes || (mesAtual == dataNascimento.mes && diaAtual < dataNascimento.dia)) {
        idade--; 
    }

    if(idade>=18){
        return true;
    }else{
        cout << "Clientes menores de idade não tem acesso a locação de veiculos" << endl;
        pause();
        return false;
    }

}   

bool verificaNome(const string &nome) {
    for (int i = 0; i < nome.length(); i++) {
        if (!isalpha(nome[i]) && !isspace(nome[i])) {
            cout << "Nome inválido, devem conter apenas letras ou espaços." << endl;
            pause();
            return false;
        }
    }
    return true;
}







