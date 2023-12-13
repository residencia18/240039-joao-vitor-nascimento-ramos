/*Exercício 7: 
Exercício 6: 
Escreva um programa em C++ que gere e imprima o seguinte padrão de caracteres alfanuméricos, onde o número fornecido pelo usuário é a altura do padrão:
A
BC
DEF
GHIJ
KLMNO*/

#include <iostream>
using namespace std;

int main()
{
    int altura;
    char caracter = 'A';

    printf("Entre com a altura do padrão : ");
    scanf("%d",&altura);

    for(int i = 0 ; i < altura ; i++){
        for(int j=0 ; j <= i ; j++){
            printf("%c",caracter);
            caracter++;
        }
        cout << endl;
    }


    return 0;
}