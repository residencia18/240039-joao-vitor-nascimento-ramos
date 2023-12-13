/*Exercício 10:
Escreva uma função que recebe uma string de caracteres e uma letra e devolve um vetor de inteiros contendo as posições (índices no vetor da string) onde a letra foi encontrada) e um inteiro contendo o tamanho do vetor criado (total de letras iguais encontradas). Utilize o retorno de um vetor para retornar os índices e um ponteiro para guardar o tamanho do vetor.*/

#include <iostream>
#include <string>
using namespace std;

int *find_char(string palavra, char caracter, int &qntdCaracteres);

int main()
{
    string palavra;
    char caracter;
    int qntdCaracteres;

    cout << "\nDigite uma palavra: ";
    cin >> palavra;

    cout << "\nDigite um caracter: ";
    cin >> caracter;

    int *vetor = find_char(palavra, caracter, qntdCaracteres);

    cout << "\nO caracter " << caracter << " aparece " << qntdCaracteres << " vezes na palavra " << palavra << " nas posições: ";
    for (int i = 0; i < qntdCaracteres; i++)
    {
        cout << vetor[i] << " ";
    }
    cout << endl;
    delete[] vetor;
    return 0;
}

int *find_char(string palavra, char caracter, int &qntdCaracteres)
{
    int *vetor = new int[palavra.length()];
    int contador = 0;
    for (int i = 0; i < palavra.length(); i++)
    {
        if (palavra[i] == caracter)
        {
            vetor[contador] = i;
            contador++;
        }
    }
    qntdCaracteres = contador;
    
    return vetor;
}