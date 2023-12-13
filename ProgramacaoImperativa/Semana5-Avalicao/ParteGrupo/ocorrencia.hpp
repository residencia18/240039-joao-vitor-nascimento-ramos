#ifndef OCORRENCIA_HPP
#define OCORRENCIA_HPP

#include "dataHora.hpp"
#include <iostream>
#include "verificacoesOcorrencia.hpp"

using namespace std;

typedef struct
{
    string descricao;
    DataHora horario;
    string numeroApolice;
    int id;

    void imprimeOcorrencia()
    {
        cout << "\tDescrição da ocorrencia : " << descricao << endl;
        cout << "\tHorario : ";
        horario.data.mostraData();
        cout << endl;
        horario.hora.mostraHorario();
        cout << "\tNumero de Apolice : " << numeroApolice << endl;
        cout << "\t========================================\n";
    }

    bool inserirDescricao()
    {
        string descricaoInserida;

        cout << "\n\tInsira a descrição do ocorrido : ";
        getline(cin, descricaoInserida);

        descricao = descricaoInserida;
        return true;
    }

    bool inserirApolice()
    {
        string apoliceInserida;

        do
        {
            cout << "\n\tInsira a apolice de seguro : ";
            getline(cin, apoliceInserida);
        } while (!verificaApolice(apoliceInserida));

        numeroApolice = apoliceInserida;
        return true;
    }

} Ocorrencia;

#endif
