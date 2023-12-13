#include "mostraFuncionario.h"

void mostraTodosFuncionarios(Funcionario funcionarios[], int numeroDeFuncionarios){
    for(int i = 0 ; i < numeroDeFuncionarios ; i++){
        cout << "Funcionario : " <<  funcionarios[i].nome << " " << funcionarios[i].sobrenome << endl;
        cout << "Ano de admissão : " << funcionarios[i].anoDeAdmissao << endl;
        cout << "RG : " << funcionarios[i].RG << endl;
        cout << "Salário : " << funcionarios[i].salario << endl;
        cout << endl;
    }
}