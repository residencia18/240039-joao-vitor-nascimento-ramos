#ifndef CLIENTE_HPP
#define CLIENTE_HPP
#include <iostream>
#include "verificacoesCliente.hpp"

using namespace std;

typedef struct
{
    string cpf;
    string nome;
    Data dataNascimento;
    string cnh;

    bool inserirCPF()
    {
        string cpfInserido;
        bool cpfValido;
        
        do
        {
            cout << "\n\tInsira um cpf válido(apenas numeros) : ";
            cin >> cpfInserido;
            limpaBuffer();

            cpfValido = verificaCPF(cpfInserido);

            if (!cpfValido)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }
        } while (!cpfValido);
        
        cpf = cpfInserido;
        return true;
    }

    bool inserirNome()
    {
        string nomeInserido;
        bool nomeValido;
        
        do
        {
            cout << "\n\tInsira um nome válido : ";
            getline(cin, nomeInserido);

            nomeValido = verificaNome(nomeInserido);

            if (!nomeValido)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }

        } while (!nomeValido);

        nome = nomeInserido;
        return true;
    }

    bool inserirDataNascimento()
    {
        Data dataNascInserida;
        string data;
        bool dataNascValida;

        do
        {   
            do{

                cout << "\n\tInsira uma data de nascimento válida : ";
                getline(cin, data);

            } while (!dataNascInserida.preencheData(data));

            dataNascValida = verificaDataNascimento(dataNascInserida);

            if (!dataNascValida)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }

        } while (!dataNascValida);

        dataNascimento = dataNascInserida;
        return true;
    }

    bool inserirCNH()
    {
        string cnhInserida;
        bool cnhValida;

        do
        {
            cout << "\n\tInsira um número de cnh válido(apenas números) : ";
            cin >> cnhInserida;
            limpaBuffer();

            cnhValida = verificaCNH(cnhInserida);

            if (!cnhValida)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }

        } while (!cnhValida);

        cnh = cnhInserida;
        return true;
    }

    bool preencheCliente()
    {
        if (inserirNome())
        {
            if (inserirCPF())
            {
                if (inserirDataNascimento())
                {
                    if (inserirCNH())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void mostraCliente()
    {
        cout << "\tCliente " << nome << endl;
        cout << "\tCPF : " << cpf << endl;
        cout << "\tCNH : " << cnh << endl;
        cout << "\tData de Nascimento : ";
        dataNascimento.mostraData();
        cout << "\tIdade do cliente: " << dataNascimento.anosCompletos() << endl;
        cout << "\t========================================\n";
    }

} Cliente;

#endif
