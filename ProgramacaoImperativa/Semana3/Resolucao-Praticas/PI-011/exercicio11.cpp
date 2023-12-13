/*Exercício 11:
Escreva uma função que codifica uma string em um código secreto. A regra secreta de codificação é extremamente simples: substitui cada letra pela letra seguinte (Z é codificado como A). Por exemplo, “Estruturas de Dados” se transformaria em “Ftusvuvsbt ef Ebept”. Escreva uma função para codificar e uma para decodificar cadeias segundo este código. Suas funções devem escrever a string produzida em uma string diferente da fornecida como entrada.*/

#include <iostream>
#include <string>
using namespace std;

string codifica(string palavra);

string decodifica(string palavra);

int main()
{
    string palavra;

    cout << "\nDigite uma palavra: ";
    getline(cin, palavra);

    cout << "\nPalavra codificada: " << codifica(palavra) << endl;

    cout << "\nPalavra decodificada: " << decodifica(codifica(palavra)) << endl;

}

string codifica(string palavra)
{
    string codificada = palavra;
    for (int i = 0; i < palavra.length(); i++)
    {
        if (palavra[i] == 'z')
        {
            codificada[i] = 'a';
        }
        else if (palavra[i] == 'Z')
        {
            codificada[i] = 'A';
        }
        else if (palavra[i] == ' ')
        {
            codificada[i] = ' ';
        }
        else
        {
            codificada[i] = palavra[i] + 1;
        }
    }
    return codificada;
}

string decodifica(string palavra)
{
    string decodificada = palavra;
    for (int i = 0; i < palavra.length(); i++)
    {
        if (palavra[i] == 'a')
        {
            decodificada[i] = 'z';
        }
        else if (palavra[i] == 'A')
        {
            decodificada[i] = 'Z';
        }
        else if (palavra[i] == ' ')
        {
            decodificada[i] = ' ';
        }
        else
        {
            decodificada[i] = palavra[i] - 1;
        }
    }
    return decodificada;
}