/*Exercício 4: Manipulando arrays multidimensionais
● Como parte do exercício 3 foi se trabalhou com a geração de strings aleatórias. Modifique a sua implementação para:

a. Em vez de gerar apenas duas strings, gerar uma lista de 10 strings aleatórias;

b. Substitua o primeiro caractere de cada string por maiúscula;*/

#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

int main()
{
    srand(time(NULL));

    string lista[10];
    char letra;

    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 10; j++)
            lista[i] += (rand() % 26) + 97;
        letra = lista[i][0];
        lista[i][0] = toupper(letra);
    }
    for (int i = 0; i < 10; i++)
        cout << lista[i] << endl;
    return 0;
}
