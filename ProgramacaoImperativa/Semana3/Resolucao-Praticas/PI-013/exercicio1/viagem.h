#ifndef VIAGEM_H
#define VIAGEM_H

#include "estruturas.h"
#include "data.h"
#include "menu.h"


bool verificaOnibusCheio(Viagem &viagem);
bool verificaAssentoDisponivel(Viagem &viagem, int assento);
int valorArrecadadoViagem(Viagem &viagem);
bool validaAssento(int assento);

void mostraHorarios(int trajeto);
int escolherOnibus();
int escolheTrajeto();
int escolheHorario(int trajeto);
void cadastraPassagem(Viagem &viagem, int assento, Passageiro passageiro);
bool venderPassagem(Viagem &viagem, int assento, Passageiro passageiro);
string buscaHorario(int onibus);
int solicitaAssento();
Passageiro cadastraPassageiro();


#endif 