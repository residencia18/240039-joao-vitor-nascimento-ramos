#include <iostream>
#include <vector>
#include <math.h>
#include <string>
#ifndef PONTO_H
#define PONTO_H

using namespace std;

#pragma once

class Ponto
{
public:

    double x, y;
    vector<Ponto> listPontos;

    Ponto(double x, double y)
    {
        this->x = x;
        this->y = y;
    }

    Ponto() {}

    ~Ponto() {}

    double getX()
    {
        return this->x;
    }

    void setX(double x)
    {
        this->x = x;
    }

    double getY()
    {
        return this->y;
    }

    void setY(double y)
    {
        this->y = y;
    }

    void setCoordenadas(double x, double y)
    {
        this->x = x;
        this->y = y;
    }

    void limpaTela()
    {
#ifdef _WIN32
        system("cls");
#else
        system("clear");
#endif
    }

    void lerPontos()
    {
        cout << "\n\t==========LER COORDENADAS DO PONTO==========\n"
             << endl;

        cout << "Digite o valor de x: ";
        cin >> this->x;

        cout << "Digite o valor de y: ";
        cin >> this->y;
    }

    string toString()
    {
        return "(" + to_string(x) + ", " + to_string(y) + ")";
    }

    void escrevePontos(double distancia)
    {
        printf("\n\tResposta: A distância do ponto (%.0f, %.0f) até a origem é aproximadamente %.1f\n\n", x, y, distancia);
    }

    void listarPontos(Ponto pontos[])
    {
        int i = 0;
        listPontos.push_back(pontos[0]);
        listPontos.push_back(pontos[1]);
        listPontos.push_back(pontos[2]);
        
        cout << "\n\tPontos:\n\tponto[0](2, 2)\n\tponto[1](-1, 5)\n\tponto[2](0, 0) ";
        cout << "\n\t=========LISTAR COORDENADAS DO PONTO=========" << endl;

        for (auto it = listPontos.begin(); it != listPontos.end(); it++, i++)
        {
            double distancia = it->calcularDistancia();
            cout << "\n\tDistância do ponto " << i + 1 << " até a origem: " << distancia << endl;
        }
        cout << "\t============================================\n" << endl;
    }

    double calcularDistancia()
    {
        return sqrt(pow(x, 2) + pow(y, 2));
    }

private:
};

#endif