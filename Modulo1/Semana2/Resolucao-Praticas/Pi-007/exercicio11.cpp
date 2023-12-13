<<<<<<< HEAD
/*Escreva um programa em C++ que leia um número inteiro e imprima a sequência de Fibonacci (Referência abaixo) até o número fornecido pelo usuário.*/

#include <iostream>
using namespace std;

int main()
{
    int numero, primeiro = 0, segundo = 1, proximo;

    cout << "Digite um numero inteiro: ";
    cin >> numero;

    cout << "Sequencia de Fibonacci: " << endl;

    for (int i = 0; i <= numero; i++)
    {
        if (i <= 1)
        {
            proximo = i;
        }
        else
        {
            proximo = primeiro + segundo;
            primeiro = segundo;
            segundo = proximo;
        }
        cout << proximo << " ";
    }
    cout << endl;
}
=======
#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int numero;
    int numDigitos = 0;
    int temp;
    int soma = 0;
    cout << "Entre com um numero: ";
    cin >> numero;

    temp = numero;

    while (temp > 0) {
        temp /= 10;
        numDigitos++;
    }

    temp = numero;

    while (temp > 0) {
        int digito = temp % 10;
        soma += pow(digito, numDigitos);
        temp /= 10;
    }

    if (soma == numero) {
        cout << numero << " é um número Armstrong." << endl;
    } else {
        cout << numero << " não é um número Armstrong." << endl;
    }

    return 0;
}
>>>>>>> abb4680 (exercicio de fibonacci inserido)
