#include<iostream>
#include<ctime>

using namespace std;

int insere_meio(int *,int , int );
void preencheVetor(int * ,int );

int main(){

    srand(time(0));
    int tam = rand()%12;
    int vetor[tam] ;
    preencheVetor(vetor,tam);
    for(int i = 0 ; i < (tam) ; i++){
        cout << vetor[i] << endl;
    }

    tam = insere_meio(vetor,tam,3);

    return 0;

}

int insere_meio(int *vetor,int tam, int elemento){
    int posicaoInsercao = tam/2;

    for (int i = tam; i > posicaoInsercao; i--) {
        vetor[i] = vetor[i - 1];
    }

    vetor[posicaoInsercao] = elemento;
    tam++;

    return tam;
}

void preencheVetor(int *vetor ,int tam){
    for(int i = 0 ; i < tam ; i++){
        vetor[i] = rand()%100;
    }
}