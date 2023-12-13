#ifndef HORARIO_HPP
#define HORARIO_HPP
#include "utils.hpp"
#include <cstdio>
#include <iostream>

using namespace std;

typedef struct
{
    int hora, minuto, segundo;

    bool preencheHorario(string horario)
    {
        return armazenaVerificaSeparaHora(segundo, minuto, hora, horario);
    }

    void mostraHorario()
    {
        printf("%02d:%02d:%02d\n", segundo, minuto, hora);
    }
    
} Horario;

#endif