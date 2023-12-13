/*Exercício 7:
Faça uma função que receba um vetor de números inteiros por parâmetro, a quantidade de elementos do vetor, e um multiplicador e multiplique cada elemento do vetor pelo multiplicador

Protótipo da função: void multiplica_por_n(int *vet, int qtde, int n);*/

#include <iostream>
#include <ctime>

using namespace std;

void preencheVetor(int vet[], int qtde);

void multiplica_por_n(int *vet, int qtde, int n);

int main()
{
    srand(time(nullptr));

    int qtde = 10, n;
    int vetor[qtde];

    preencheVetor(vetor, qtde);

    multiplica_por_n(vetor, qtde, n);

}

void preencheVetor(int vet[], int qtde)
{
    for (int i = 0; i < qtde; i++)
    {
        vet[i] = rand() % 100;
    }
    cout << endl;

    for (int i = 0; i < qtde; i++)
    {
        cout << vet[i] << ", ";
    }
    cout << endl;
}

void multiplica_por_n(int *vet, int qtde, int n)
{
    cout << "\nInforme um número para multiplicar o vetor: ";
    cin >> n;

    for (int i = 0; i < qtde; i++)
    {
        vet[i] *= n;
    }

    cout << endl;
    for (int i = 0; i < qtde; i++)
    {
        cout << vet[i] << ", ";
    }
    cout << endl;
}