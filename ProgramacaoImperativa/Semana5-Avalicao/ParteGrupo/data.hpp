#ifndef DATA_HPP
#define DATA_HPP

#include <iostream>
#include "utils.hpp"
#include <ctime>
using namespace std;

typedef struct
{
    int dia, mes, ano;

    tm *getTempo()
    {
        time_t t;
        time(&t);
        struct tm *data;
        data = localtime(&t);
        return data;
    }

    int anosCompletos()
    {
        int x = (getTempo()->tm_year + 1900) - ano;

        // getTempo()->tm_mon siguinifica o mes atual
        if (getTempo()->tm_mon < mes)
        {
            x--;
        }
        else
        { // getTempo()->tm_mday siguinifica o dia atual
            if (getTempo()->tm_mon == mes && getTempo()->tm_mday < dia)
            {
                x--;
            }
        }
        return x;
    }

    bool preencheData(string data)
    {
        return armazenaVerificaSepara(dia, mes, ano, data);
    }

    void mostraData()
    {
        cout << "\n\t" << dia << " de " << nomeDoMes(mes) << " de " << ano;
    }

    void mostraDataAtual()
    {
        // int diaSemana = getTempo()->tm_wday;

        if (getTempo()->tm_hour >= 0 && getTempo()->tm_hour < 12)
        {
            printf("\n\tBOM DIA!...");
        }
        else if (getTempo()->tm_hour >= 12 && getTempo()->tm_hour < 18)
        {
            printf("\n\tBOA TARDE!...");
        }
        else
        {
            printf("\n\tBOA NOITE!...");
        }

        printf("\n\tHORA ATUAL: %02d:%02d:%02d\n", getTempo()->tm_hour, getTempo()->tm_min, getTempo()->tm_sec);
        printf("\tDATA ATUAL: %02d/%02d/%4d %s\n ", getTempo()->tm_mday, getTempo()->tm_mon + 1, getTempo()->tm_year + 1900, diaDaSemana().c_str());

        if (getTempo()->tm_year % 4 == 0 && getTempo()->tm_year % 100 != 0 || getTempo()->tm_year % 400 == 0)
        {
            printf("\tANO BISSEXTO, FALTA %i DIAS PARA TERMINAR O ANO!...\n", 366 - getTempo()->tm_yday);
            printf("\n");
        }
        else
        {
            printf("\tANO NÃO BISSEXTO, FALTA %i DIAS PARA TERMINAR O ANO!...\n", 365 - getTempo()->tm_yday);
            printf("\n");
        }
    }

    string diaDaSemana()
    {

        switch (getTempo()->tm_wday)
        {
        case 0:
            return "DOMINGO";
        case 1:
            return "SEGUNDA-FEIRA";
        case 2:
            return "TERÇA-FEIRA";
        case 3:
            return "QUARTA-FEIRA";
        case 4:
            return "QUINTA-FEIRA";
        case 5:
            return "SEXTA-FEIRA";
        case 6:
            return "SABADO";
        }
        return "ERRO";
    }


} Data;

#endif