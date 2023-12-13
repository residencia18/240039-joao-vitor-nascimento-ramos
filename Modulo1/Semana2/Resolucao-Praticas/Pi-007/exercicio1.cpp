/*Exercício 1: Instale e configure o Git
Escreva um programa em C++ que imprima na tela os números de 1 a 100. Porém, para múltiplos de 3, o programa deve imprimir "Fizz", e para múltiplos de 5, deve imprimir "Buzz". Para números que são múltiplos de ambos, imprimir "FizzBuzz".*/

#include <iostream>
using namespace std;

int main(){

    for(int i = 0 ; i < 100 ; i++){
        if( ((i+1)%3==0) && ((i+1)%5==0) ){
            printf("FizzBuzz\n");
        }else if((i+1)%3==0){
            printf("Fizz\n");
        }else if((i+1)%5==0){
            printf("Buzz\n");
        }else{
            printf("%d\n",i+1);
        }
    }


    return 0;
}