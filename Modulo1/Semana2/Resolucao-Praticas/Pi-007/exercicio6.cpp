/* Exercício 6:
Desenvolva um programa que receba a matrícula (apenas números) e 3 notas de um grupo de alunos. A cada aluno cadastrado o programa deve perguntar se deseja prosseguir com outro. Em seguida, calcule a média de cada aluno e imprima os dados da seguinte forma:

MATRICULA NOTA1 NOTA2 NOTA3 MEDIA
===========================================
123456789 10.0 8.0 9.9 8.5
MATRICULA NOTA1 NOTA2 NOTA3 MEDIA
===========================================
123456789 10.0 8.0 9.9 8.5
MATRICULA NOTA1 NOTA2 NOTA3 MEDIA
===========================================
123456789 10.0 8.0 9.9 8.5*/

#include <iostream>
#include <vector>
using namespace std;

typedef struct {
    int matricula;
    float notas[3];
    float media = 0;
} Aluno;

int main() {
    vector<Aluno> listaAlunos;
    char escolha;
    int quantidadeDeAlunos = 0;

    while (escolha != '0') {
        Aluno aluno;
        quantidadeDeAlunos++;

        printf("Digite a matricula do %d aluno : ", quantidadeDeAlunos);
        scanf("%d", &aluno.matricula);

        for (int i = 0; i < 3; i++) {
            printf("Nota %d : ", i + 1);
            scanf("%f", &aluno.notas[i]);
            aluno.media += aluno.notas[i]/3;
        }

        printf("Deseja inserir mais um aluno (Digite 0 para não e qualquer outro caracter para sim) ? ");
        cin >> escolha;
        listaAlunos.push_back(aluno);
    }

    printf("\nMATRICULA      NOTA1    NOTA2    NOTA3    MEDIA\n");
    for (int i = 0; i < listaAlunos.size(); ++i) {
        printf("%-10d      %-5.1f    %-5.1f    %-5.1f    %-5.1f\n", listaAlunos[i].matricula, listaAlunos[i].notas[0], listaAlunos[i].notas[1], listaAlunos[i].notas[2],listaAlunos[i].media);
        printf("-----------------------------------------------\n");
        printf("-----------------------------------------------\n");
    }

    return 0;
}
