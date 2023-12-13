#include<iostream>
#include<vector>
#include<string>
#include<stdlib.h>

using namespace std;

typedef struct{
    string nome;
    float notas[2];
}Aluno;

void bubbleSort(vector<Aluno>&);
int insereLimiteDeAlunos();
void insereAluno(vector<Aluno> &, Aluno );

void recebeInformacoes(vector<Aluno> &, Aluno &);
void recebeNome(vector<Aluno> &, Aluno &);
void recebeNotas(vector<Aluno> &, Aluno &, int );

void inclusoesExclusoes(vector<Aluno>&, int );
void tentaInserirAluno(vector<Aluno> &, int , int &);
void escolhaDeInsercao(vector<Aluno> & , int , int &, char &);

void tentaExcluirAluno(vector<Aluno> &, int &);

void imprimeAlunos(vector<Aluno> &);

void impressaoETrocaNota(vector<Aluno> &);

void permiteTrocaNota(vector<Aluno> &);
void tentaTrocaNota(vector<Aluno> &);
void imprimeResultadoFinal(vector<Aluno> &);

void limparTela();


int main(){

    int limiteDeAlunos;
    vector<Aluno> listaDeAlunos;

    limiteDeAlunos = insereLimiteDeAlunos();
    inclusoesExclusoes(listaDeAlunos,limiteDeAlunos);
    impressaoETrocaNota(listaDeAlunos);
    imprimeResultadoFinal(listaDeAlunos);


    return 0;
}

void imprimeResultadoFinal(vector<Aluno> &listaDeAlunos){
    cout << "RESULTADO FINAL" << endl << endl;
    imprimeAlunos(listaDeAlunos);
}

void impressaoETrocaNota(vector<Aluno> &listaDeAlunos){
    imprimeAlunos(listaDeAlunos);
    permiteTrocaNota(listaDeAlunos);
}

void permiteTrocaNota(vector<Aluno> &listaDeAlunos){
    char escolha;
    do{
        cout << "Deseja alterar alguma nota(s/n) ? ";
        cin >> escolha;
        if(escolha!= 's' && escolha!= 'n'){
            cout << "**Escolha Invalida**" << endl;
        }else if(escolha=='s'){
            tentaTrocaNota(listaDeAlunos);
            impressaoETrocaNota(listaDeAlunos);
        }
    }while(escolha!= 's' && escolha!= 'n');
}

void trocaNota(Aluno &aluno, int prova){
    float nota;
    do{
    cout << "Insira a nova nota : ";
    cin >> nota;
    if(nota > 10 || nota < 0){
        cout << "**Nota Invalida" << endl;
    }else{
        aluno.notas[prova-1] = nota;
    }
    }while(nota > 10 || nota < 0);
}

void tentaTrocaNota(vector<Aluno> &listaDeAlunos){
    string alunoTrocaNota;
    int notaParaAlterar=1;
    float nota;

    cout << "Deseja alterar nota de que aluno ? :";
    cin >> alunoTrocaNota;
    for (auto it = listaDeAlunos.begin(); it != listaDeAlunos.end(); ++it) {
        if (it->nome == alunoTrocaNota) {
            do{
                cout << "Do aluno " << it->nome << " alterar a primeira nota (1), a segunda nota (2) ou nenhuma (0)? ";
                cin >> notaParaAlterar;
                if(notaParaAlterar==1){
                    trocaNota(*it,notaParaAlterar);
                }else if(notaParaAlterar==2){
                    trocaNota(*it,notaParaAlterar);
                }else if(notaParaAlterar==0){
                    return;
                }else{
                    cout <<"**Escolha Invalida**" << endl;
                }
            }while(notaParaAlterar!=0);
        }
    }
    cout << "Aluno " << alunoTrocaNota << " nao foi encontrado na lista." << endl;
    tentaTrocaNota(listaDeAlunos);
}

void imprimeAlunos(vector<Aluno> &listaDeAlunos){
    for (auto it = listaDeAlunos.begin(); it != listaDeAlunos.end(); ++it) {
        cout << endl;
        cout << "Aluno " << it->nome << endl;
        cout << "Nota 1 : " << it->notas[0] << endl;
        cout << "Nota 2 : " << it->notas[1] << endl;
        float media = ((it->notas[0]+it->notas[1])/2);
        cout << "Media  : " << media << endl;
        if(media >= 7.0){
            cout << "Aprovado" << endl;
        }else{
            cout << "Reprovado" << endl;
        }
        cout << endl;
    }
}

void recebeInformacoes(vector<Aluno> &listaDeAlunos, Aluno &alunoNovo){
    recebeNome(listaDeAlunos,alunoNovo);
    recebeNotas(listaDeAlunos,alunoNovo,1);
    recebeNotas(listaDeAlunos,alunoNovo,2);
}

void recebeNome(vector<Aluno> &listaDeAlunos, Aluno &alunoNovo){
    cout << "Qual o nome do aluno ? ";
    cin  >> alunoNovo.nome;
}

void recebeNotas(vector<Aluno> &listaDeAlunos, Aluno &alunoNovo, int prova){
    do{
        cout << (prova == 1 ? "Primeira nota" : "Segunda nota") << " de " << alunoNovo.nome << ": ";

        cin >> alunoNovo.notas[prova-1];
        if(alunoNovo.notas[prova-1] > 10 || alunoNovo.notas[prova-1] < 0){
            cout << "***Nota invalida***" << endl;
        }
    }while(alunoNovo.notas[prova-1] > 10 || alunoNovo.notas[prova-1] < 0);

}

void tentaInserirAluno(vector<Aluno> &listaDeAlunos, int limiteDeAlunos, int &numeroDeAlunosVigente){
    if(numeroDeAlunosVigente < limiteDeAlunos){
        Aluno alunoNovo;
        recebeInformacoes(listaDeAlunos,alunoNovo);
        insereAluno(listaDeAlunos,alunoNovo);
        numeroDeAlunosVigente++;
    }else{
        cout << "Numero maximo de alunos atingido";
    }
}

void escolhaDeInsercao(vector<Aluno> &listaDeAlunos , int limiteDeAlunos, int &numeroDeAlunosVigente, char &escolha1){
    do{
        cout << "Deseja inserir mais alunos(s/n)? ";
        cin >> escolha1;
        if(escolha1!= 's' && escolha1!= 'n'){
            limparTela();
            cout << "Escolha invalida" << endl;
        }else if(escolha1=='s'){
            tentaInserirAluno(listaDeAlunos,limiteDeAlunos,numeroDeAlunosVigente);
        }

    }while(escolha1!= 's' && escolha1!= 'n');
}

void escolhaDeExclusao(vector<Aluno> &listaDeAlunos , int limiteDeAlunos, int &numeroDeAlunosVigente, char &escolha2){
    do{
        cout << "Deseja excuir um aluno(s/n)? ";
        cin >> escolha2;
        if(escolha2!= 's' && escolha2!= 'n'){
            limparTela();
            cout << "Escolha invalida" << endl;
        }else if(escolha2=='s'){
            tentaExcluirAluno(listaDeAlunos,numeroDeAlunosVigente);
        }

    }while(escolha2!= 's' && escolha2!= 'n');
}

void tentaExcluirAluno(vector<Aluno> &listaDeAlunos, int &numeroDeAlunosVigente){
    string nomeParaExcluir;

    cout << "Qual aluno deseja excluir? :";
    cin >> nomeParaExcluir;
    for (auto it = listaDeAlunos.begin(); it != listaDeAlunos.end(); ++it) {
        if (it->nome == nomeParaExcluir) {
            listaDeAlunos.erase(it);
            cout << "Aluno " << nomeParaExcluir << " foi excluido." << endl;
            numeroDeAlunosVigente--;
            return;
        }
    }
    cout << "Aluno " << nomeParaExcluir << " nao foi encontrado na lista." << endl;

}

void inclusoesExclusoes(vector<Aluno> &listaDeAlunos , int limiteDeAlunos){
    char escolha1='s',escolha2='s';
    int numeroDeAlunosVigente = 0 ;
    do{
        escolhaDeInsercao(listaDeAlunos , limiteDeAlunos, numeroDeAlunosVigente, escolha1);
        escolhaDeExclusao(listaDeAlunos , limiteDeAlunos, numeroDeAlunosVigente, escolha2);
    }while(escolha1=='s' || escolha2=='s');

}

void insereAluno(vector<Aluno> &listaDeAlunos, Aluno alunoNovo){
    int posicao=0;

    while (posicao < listaDeAlunos.size() && listaDeAlunos[posicao].nome < alunoNovo.nome) {
        posicao++;
    }
    listaDeAlunos.insert(listaDeAlunos.begin()+posicao,alunoNovo);
}

int insereLimiteDeAlunos(){
    int limiteDeAlunos;
    cout << "Insira um limite de alunos : ";
    cin >> limiteDeAlunos;
    return limiteDeAlunos;
}

void bubbleSort(vector<Aluno>& listaDeAlunos) {
    int n = listaDeAlunos.size();
    bool trocou = false;
    do {
        trocou = false;
        for (int j = 0; j < n - 1; j++) {
            if (listaDeAlunos[j].nome > listaDeAlunos[j + 1].nome) {
                Aluno temp = listaDeAlunos[j];
                listaDeAlunos[j] = listaDeAlunos[j + 1];
                listaDeAlunos[j + 1] = temp;
                trocou = true;
            }
        }
        n--;
    } while (trocou);
}

void limparTela() {
    #ifdef _WIN32 // Se for Windows
        system("cls");
    #else // Se for Linux ou macOS
        system("clear");
    #endif
}