#include<iostream>
#define NUM_DE_ESTACOES 250

using namespace std;

void preecheVetor(float* , float& , float& , float&);
void insereNumerosAleatorios(float *, int );
void preencheMedia(float *, float &, int );
void preencheMenorTemperatura(float *, float &, int );
void preencheMaiorTemperatura(float *, float &, int );
void imprimeMaxMinMedia(float&, float&, float&);
void atualizacao(float *temperaturas, float &media);


int main(){

    float temperaturas[NUM_DE_ESTACOES];
    float maxima = 9;
    float minima = 45;
    float media = 0;

    preecheVetor(temperaturas,maxima,minima,media);
    imprimeMaxMinMedia(maxima,minima,media);
    atualizacao(temperaturas,media);

    return 0;
}

void atualizacao(float *temperaturas, float &media){
    for(int i = 0 ; i < NUM_DE_ESTACOES ; i++){
        if(temperaturas[i] > media){
            temperaturas[i] +=1;
        }else if(temperaturas[i] < media){
            temperaturas[i] -=2;
        }
    } 
}

void insereNumerosAleatorios(float *temperaturas, int i){
    temperaturas[i] = 10 + ((float)rand()/RAND_MAX)*30;
}

void preencheMaiorTemperatura(float *temperaturas, float &maxima, int i){
        if(temperaturas[i] > maxima){
            maxima = temperaturas[i];
        }
}

void preencheMenorTemperatura(float *temperaturas, float &minima, int i){
        if(temperaturas[i] < minima){
            minima = temperaturas[i];
        }
}

void preencheMedia(float *temperaturas, float &media, int i){
        media += temperaturas[i];
}

void preecheVetor(float *temperaturas,float &maxima, float &minima , float &media ){
    for(int i = 0 ; i < NUM_DE_ESTACOES ; i++){
        insereNumerosAleatorios(temperaturas,i);
        preencheMedia(temperaturas,media,i);
        preencheMaiorTemperatura(temperaturas,maxima,i);
        preencheMenorTemperatura(temperaturas,minima,i);
    }
}

void imprimeMaxMinMedia(float &maxima , float &minima, float &media){
    printf("\nTemperatura Maxima : %f\n",maxima);
    printf("\nTemperatura Minima : %f\n",minima);
    printf("\nTemperatura Media  : %f\n",media/NUM_DE_ESTACOES);
}