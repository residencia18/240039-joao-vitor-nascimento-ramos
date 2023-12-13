/*Exercício 2: Trabalhando com arrays
● A seguinte expressão em C gera valores aleatórios de ponto flutuante entre 0 e 10: ((float)rand()/RAND_MAX)*10. Utilizando esta expressão implemente uma aplicação que:

a. Simule as notas de uma turma de 15 alunos em uma avaliação e guarde num array;

b. Simule as notas da mesma turma numa segunda avaliação e guarde em um segundo array;

c. Compare as notas de cada aluno na segunda avaliação, em relação à primeira, e imprima na tela as mensagens “Melhorou”, “Piorou” ou “Manteve a nota” de acordo com o desempenho de cada aluno;

d. Preencha um terceiro array com a média de cada aluno nas duas avaliações.*/

#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;
constexpr int Max = 15;

int main()
{

    srand(time(NULL));
    float notas1[15], notas2[15], media[15];

    for (int i = 0; i < Max; i++)
    {
        notas1[i] = ((float)rand() / RAND_MAX) * 10;
    }

    for (int i = 0; i < Max; i++)
    {
        notas2[i] = ((float)rand() / RAND_MAX) * 10;
    }

    printf("\n***************BOLETIM:***************\n");
    for (int y = 0; y < Max; y++)
    {
        printf("\n--------Aluno %i--------", y + 1);
        printf("\nAluno: %i : Nota1 = %.1f", y + 1, notas1[y]);
        printf("\nAluno: %i : Nota2 = %.1f", y + 1, notas2[y]);
        printf("\n------------------------\n");
    }

    printf("\n***************COMPARAÇÃO DAS NOTAS:***************\n");
    for (int i = 0; i < Max; i++)
    {
        if (notas2[i] > notas1[i])
        {
            printf("\n--------Aluno %i--------", i + 1);
            printf("\nAluno %d : Melhorou! %.1f", i + 1, notas2[i]);
            printf("\n------------------------\n");
        }
        else
        {
            if (notas2[i] < notas1[i])
            {
                printf("\n--------Aluno %i--------", i + 1);
                printf("\nAluno %d : Piorou! %.1f", i + 1, notas2[i]);
                printf("\n------------------------\n");
            }
            else
            {
                if (notas2[i] == notas1[i])
                {
                    printf("\n--------Aluno %i--------", i + 1);
                    printf("\nAluno %d : Manteve! %1.f", i + 1, notas2[i]);
                    printf("\n------------------------\n");
                }
            }
        }
        media[i] = (notas1[i] + notas2[i]) / 2;
    }

    printf("\n***************MÉDIA DOS ALUNOS:***************\n");
    for (int i = 0; i < Max; i++)
    {
        printf("\n--------Aluno %i--------", i + 1);
        printf("\nMédia do aluno %i = %.1f", i + 1, media[i]);
        printf("\n------------------------\n");
    }
}
