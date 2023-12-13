#include "cadastro.h"

void solicitaCadastroFuncionario(Funcionario funcionarios[], int &numeroDeFuncionarios){
    char escolha;
    bool escolhaErrada = false;
    do{
        cout << "Deseja Cadastrar Novo funcionario(s/n) ? " ;
        cin >> escolha;
        escolhaErrada = (escolha!='s' && escolha!='n');
        if(escolhaErrada){
            cout << "**Escolha Inválida**" << endl;
        }
        if(escolha=='s'){
            cadastraFuncionario(funcionarios,numeroDeFuncionarios);
        }
    }while(escolha!='n');

    system("clear");

}

void cadastraFuncionario(Funcionario funcionarios[], int &numeroDeFuncionarios){
    if(numeroDeFuncionarios < 50){
        cout << "Nome do funcionario : " ;
        cin >> funcionarios[numeroDeFuncionarios].nome ;

        cout << "Sobrenome : " ;
        cin >> funcionarios[numeroDeFuncionarios].sobrenome;

        cout << "Ano de Admissão : " ;
        cin >> funcionarios[numeroDeFuncionarios].anoDeAdmissao;

        cout << "RG : " ;
        cin >> funcionarios[numeroDeFuncionarios].RG;

        cout << "Salario : " ;
        cin >> funcionarios[numeroDeFuncionarios].salario ; 
        numeroDeFuncionarios++;
    }
}