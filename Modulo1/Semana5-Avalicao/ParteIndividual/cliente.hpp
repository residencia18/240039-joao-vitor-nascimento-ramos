#ifndef CLIENTE_HPP
#define CLIENTE_HPP
#include<iostream>
#include "verificacoesCliente.hpp"

using namespace std;

typedef struct{
    string cpf;
    string nome;
    Data dataNascimento;
    string cnh;

    bool inserirCPF(){
        string cpfInserido;
        bool cpfValido;
        do{
            cout << "Insira um cpf válido(apenas numeros) : ";
            cin >> cpfInserido;
            cpfValido = verificaCPF(cpfInserido);
            if(!cpfValido){
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }
        }while(!cpfValido);
        cpf = cpfInserido;
        return true;
    }

    bool inserirNome(){
        string nomeInserido;
        bool nomeValido;
        do{
            cout << "Insira um nome válido : ";
            limpaBuffer();
            getline(cin,nomeInserido);
            nomeValido = verificaNome(nomeInserido);
            if(!nomeValido){
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }
        }while(!nomeValido);
        nome = nomeInserido;
        return true;
    }

    bool inserirDataNascimento(){
        Data dataNascInserida;
        string data;
        bool dataNascValida;
        do{
            cout << "Insira uma data de nascimento válida : ";
            limpaBuffer();
            getline(cin,data);
            dataNascInserida.preencheData(data);
            dataNascValida = verificaDataNascimento(dataNascInserida);
            if(!dataNascValida){
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }

        }while(!dataNascValida);
        dataNascimento = dataNascInserida;
        return true;
    }

    bool inserirCNH(){
        string cnhInserida;
        bool cnhValida;
        do{
            cout << "Insira um número de cnh válido(apenas números) : ";
            cin >> cnhInserida;
            cnhValida = verificaCNH(cnhInserida);
            if(!cnhValida){
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }
        }while(!cnhValida);
        cnh = cnhInserida;
        return true;
    }

    bool preencheCliente(){
        if(inserirNome()){
            if(inserirCPF()){
                if(inserirDataNascimento()){
                    if(inserirCNH()){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    void mostraCliente(){
        cout << endl << "Cliente " << nome << endl;
        cout << "CPF : " << cpf << endl;
        cout << "CNH : " << cnh << endl;
        cout << "Data de Nascimento : ";
        dataNascimento.mostraData() ;
    }



}Cliente;

#endif

