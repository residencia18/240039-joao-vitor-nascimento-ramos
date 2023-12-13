#ifndef UTILS_HPP
#define UTILS_HPP

#include<iostream>

using namespace std;

void limpaTela();
void limpaBuffer();
void pause();
bool verificaProsseguimento();

bool armazenaVerificaSepara(int &dia, int &mes, int &ano ,const string &data);
void separaData(int &dia, int &mes, int &ano , const string &data);
int separaDia(const int &pos1, const string &data);
int separaMes(const int &pos1, const int &pos2 , const string &data);
int separaAno(const int &pos2, const string &data);
bool anoBissexto(const int &ano);
bool verificaData(const int &dia , const int &mes, const int &ano);
string nomeDoMes(const int &mes);

bool armazenaVerificaSeparaHora(int &segundo, int &minuto, int &hora ,const string &horario);
void separaHorario(int &segundo, int &minuto, int &hora , const string &horario);
int separaSegundo(const int &pos1, const string &data);
int separaMinuto(const int &pos1, const int &pos2 , const string &data);
int separaHora(const int &pos2, const string &data);
bool verificaHorario(const int &segundo , const int &minuto, const int &hora);

#endif