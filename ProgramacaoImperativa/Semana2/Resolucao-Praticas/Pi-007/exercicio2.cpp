<<<<<<< HEAD
/*Exercício 1: Instale e configure o Git
Exercício 2:
Escreva um programa em C++ que leia um número inteiro e verifique se ele é um palíndromo. Um número é palíndromo se ele permanece o mesmo quando seus
dígitos são invertidos.*/
=======
#include <iostream>
#include <algorithm>
#include <string>
>>>>>>> ff48af3 (atividades concluidas)

#include <iostream>
#include <string>
using namespace std;

<<<<<<< HEAD
int main()
{
    int numero, numeroInvertido = 0, resto, numeroOriginal;

    cout << "Digite um numero inteiro: ";
    cin >> numero;

    numeroOriginal = numero;
    while (numero != 0)
    {
        resto = numero % 10;
        numeroInvertido = numeroInvertido * 10 + resto;
        numero /= 10;
    }
    if (numeroOriginal == numeroInvertido)
        cout << numeroOriginal << " eh um numero palindromo" << endl;
    else
        cout << numeroOriginal << " nao eh um numero palindromo" << endl;
=======
int main() {
    int numero;
    string num;
    string num_invertido;

    cout << "Entre com um numero: ";
    cin >> numero;

    num = to_string(numero);
    num_invertido = num;
    
    reverse(num_invertido.begin(), num_invertido.end());
    
    if (num == num_invertido) {
        cout << numero << " é um palíndromo" << endl;
    } else {
        cout << numero << " não é um palíndromo" << endl;
    }

    return 0;
>>>>>>> ff48af3 (atividades concluidas)
}