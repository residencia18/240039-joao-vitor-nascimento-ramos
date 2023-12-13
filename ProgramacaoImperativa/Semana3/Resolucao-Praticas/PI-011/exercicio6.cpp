#include<iostream>


using namespace std;

int* intercala(int * , int , int*, int );
void preencheVetor(int * , int);

int main(){

    srand(time(0));

    int tam1 = rand()%10;
    int vetor1[tam1];
    preencheVetor(vetor1,tam1);

    int tam2 = rand()%10;
    int vetor2[tam2];
    preencheVetor(vetor2,tam2);


    int* intercalado = intercala(vetor1,tam1,vetor2,tam2);


    return 0;
}

void preencheVetor(int *vetor ,int tam){
    for(int i = 0 ; i < tam ; i++){
        vetor[i] = rand()%100;
    }
}

int* intercala(int *vetor1,int tam1, int* vetor2, int tam2){
    int* intercalado = new int[tam1 + tam2];
    int pos1 = 0 ,pos2 = 0 ;
    for (int i = 0; i < (tam1 + tam2); i++) {
        if (pos1 < tam1 && pos2 < tam2) {
            if (i % 2 == 0) {
                intercalado[i] = vetor1[pos1];
                pos1++;
            } else {
                intercalado[i] = vetor2[pos2];
                pos2++;
            }
        } else if (pos1 < tam1) {
            intercalado[i] = vetor1[pos1];
            pos1++;
        } else if (pos2 < tam2) {
            intercalado[i] = vetor2[pos2];
            pos2++;
        }
    }

    return intercalado;
}