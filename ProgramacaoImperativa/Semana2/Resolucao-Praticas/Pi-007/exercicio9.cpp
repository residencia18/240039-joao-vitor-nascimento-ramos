#include<iostream>

using namespace std;

int main(){

    int numero;
    int somatorio=0;

    printf("Entre com um numero :");
    scanf("%d",&numero);

    for(int i = 0 ; i < ((numero/2)+1) ; i++){
        if(numero%(i+1) == 0){
            somatorio +=(i+1);
        }
    }
    if(somatorio == numero){
        printf("%d é um número perfeito",numero);
    }else{
        printf("%d não é um número perfeito",numero);
    }



    return 0;
}