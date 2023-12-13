/*Exercício 2: Operações com inteiros
● Escreva um programa que:
g. Utilizando o último valor calculado de c, recalcule o valor da mesma utilizando a expressão 𝑎 , imprima o resultado na tela;*/

#include <iostream>
using namespace std;

int main()
{

    int a, b, c;

    cout << "Digite o valor de a: ";
    cin >> a;

    cout << "Digite o valor de b: ";
    cin >> b;

    c = 4 * (a + b) / (3 - 5);

    cout << "c = " << c << endl;

    c = 4 * (a + b) / 3 - 5;

    cout << "c = " << c << endl;
}