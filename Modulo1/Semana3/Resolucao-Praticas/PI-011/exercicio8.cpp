#include<iostream>
#include<cmath>

using namespace std;

int conta_primos (int *vet, int qtde);
bool verificaPrimo(int n);

int main(){

    int vetor[5] = {73,25,2,13,6};

    cout << conta_primos(vetor,5);

    return 0;
}

int conta_primos (int *vet, int qtde){
    int contador = 0;
    for(int i = 0 ; i < qtde ; i++){
        if(verificaPrimo(vet[i])){
            contador++;
        }
    }
    return contador;
}

bool verificaPrimo(int n){
    if(n == 1 || n == 2){
        return true;
    }
    for(int i = 2 ; i < (sqrt(n)+1) ; i++){
        if(n%i==0){
            return false;
        }
    }
    return true;
}