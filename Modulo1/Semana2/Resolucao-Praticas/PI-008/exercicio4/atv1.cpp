#include<iostream>

using namespace std;

int geraIntensidade(int, int, int[][480]);
void percorreVetor(int[][480]);
void preencheHistograma( int* , int[][480]);
void imprimeHistograma(int *);

int main(){
    // [posicaoX][posicaoY][RGB]
    int intensidade[640][480];
    int histograma[256]={};
    percorreVetor(intensidade);
    preencheHistograma(histograma,intensidade);
    imprimeHistograma(histograma);

    return 0;
}

void imprimeHistograma(int *histograma){
    for (int i = 0; i < 256; i++) {
        cout << "Intensidade " << i << ": " << histograma[i] << " pixels" << endl;
    }
}

void preencheHistograma( int *histograma , int intensidade[][480]){
    for(int i = 0 ; i < 640 ; i++){
        for(int j = 0 ; j < 480 ; j++){
            histograma[intensidade[i][j]]++;
        }
    }
}

void percorreVetor(int intensidade[][480]){
    for(int i = 0 ; i < 640 ; i++){
        for(int j = 0 ; j < 480 ; j++){
            geraIntensidade(i, j, intensidade);
        }
    }
}

int geraIntensidade(int i, int j, int intensidade[][480]){
    intensidade[i][j] = rand()%256;
}
