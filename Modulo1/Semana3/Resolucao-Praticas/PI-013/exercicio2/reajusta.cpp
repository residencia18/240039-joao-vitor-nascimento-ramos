#include "reajusta.h"

void reajusta_dez_porcento(Funcionario funcionarios[],int tamanho){
    for(int i = 0 ; i < tamanho ; i++){
        funcionarios[i].salario *=1.1;
    }
}