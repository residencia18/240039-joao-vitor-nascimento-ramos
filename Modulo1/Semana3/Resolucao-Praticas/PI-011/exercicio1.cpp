/*Exercício 1:
Escreva uma função chamada maxmin que receba um vetor de números inteiros chamado vetor, uma variável inteira n contendo o tamanho do vetor, e os endereços de duas variáveis inteiras, maximo e minimo nas quais será retornado o valor do elemento de maior valor e o valor do elemento de menor valor

Protótipo da Função:
void maxmin(int vetor[], int n, int &maximo, int &minimo);

Escreva também uma função principal (main) que use a função maxmin.*/

#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

void preencheVetor(int vetor[], int n);

void maxmin(int vetor[], int n, int &maximo, int &minimo);

int main()
{
    srand(time(nullptr));
    const int n = 10;

    int vet[n];
    int maior = 0;
    int menor = 0;

    preencheVetor(vet, n);

    maxmin(vet, n, maior, menor);

    cout << "Maior = " << maior << endl;

    cout << "Menor = " << menor << endl;
    cout << endl;
}

void maxmin(int vetor[], int n, int &maximo, int &minimo)
{

    for (int i = 0; i < n; i++)
    {
        if (vetor[i] > maximo)
        {
            maximo = vetor[i];
        }
    }

    for (int i = 0; i < n; i++)
    {
        if (vetor[i] < minimo)
        {
            minimo = vetor[i];
        }
    }
}

void preencheVetor(int vetor[], int n)
{
    for (int i = 0; i < n; i++)
    {
        vetor[i] = rand() % 1000;
    }
    cout << endl;

    for (int i = 0; i < n; i++)
    {
        cout << vetor[i] << ", ";
    }
    cout << "\n"
         << endl;
}
