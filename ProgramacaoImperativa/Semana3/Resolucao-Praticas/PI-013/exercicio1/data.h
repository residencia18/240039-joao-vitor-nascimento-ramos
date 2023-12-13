#ifndef DATA_H
#define DATA_H

#include "estruturas.h"

using namespace std;


bool verificaData(int dia, int mes, int ano);
bool anoBissexto(int ano);
int separaDia(int pos1, string data);
int separaMes(int pos1, int pos2, std::string data);
int separaAno(int pos2, string data);
int posicaoDaPrimeiraBarra(string data);
int posicaoDaSegundaBarra(string data);
void separaData(int &dia, int &mes, int &ano, string data);
string buscaData();


#endif 
