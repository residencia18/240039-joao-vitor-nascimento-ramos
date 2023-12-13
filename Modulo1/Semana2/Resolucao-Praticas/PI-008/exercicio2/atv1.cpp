#include<iostream>
#include<time.h>

using namespace std;

void preencheVetor(int * , int* , int&);
void insereNumerosAleatorios(int *, int);
void preencheVetorAparicoes(int * , int *, int );
void guardaMaximoAparicoes(int * , int *, int , int &);
void imprimeSaida(int * , int&);
void imprimeAparicoes(int*);
void imprimeMaisAparicoes(int *, int &);


int main(){

    srand(time(0));

    int vetor[100];
    int aparicoes[20]={};
    int maisAparicoes = 0;
    preencheVetor(vetor,aparicoes,maisAparicoes);
    imprimeSaida(aparicoes,maisAparicoes);

    return 0;
}

void preencheVetor(int *vetor, int *aparicoes , int &maisAparicoes){
    
    for(int i = 0 ; i < 100 ; i++){
        insereNumerosAleatorios(vetor,i);
        preencheVetorAparicoes(vetor,aparicoes,i);
        guardaMaximoAparicoes(vetor,aparicoes,i,maisAparicoes);
    }
}

void insereNumerosAleatorios(int *vetor, int i){
    vetor[i] = rand()%20 +1;
}

void preencheVetorAparicoes(int *vetor , int *aparicoes, int i){
    aparicoes[vetor[i]-1]++;
}

void guardaMaximoAparicoes(int *vetor , int *aparicoes, int i, int &maisAparicoes){
    if(aparicoes[vetor[i]-1] > maisAparicoes){
        maisAparicoes = aparicoes[vetor[i]-1];
    }
}

void imprimeSaida(int *aparicoes, int &maisAparicoes){
    imprimeAparicoes(aparicoes);
    imprimeMaisAparicoes(aparicoes, maisAparicoes);
}

void imprimeAparicoes(int *aparicoes){
    
    for(int i = 0 ; i < 20 ; i++){
        printf("%d aparece %d vezes\n",i+1,aparicoes[i]);
    }
}

void imprimeMaisAparicoes(int *aparicoes, int &maisAparicoes){
    for(int i = 0 ; i < 20 ; i++){
        if(aparicoes[i] == maisAparicoes){
            printf("O numero %d tem o maior numero de aparições com %d \n",i+1,aparicoes[i]);
        }
    }
}