#include "inicializa.h"

void inicializa(Funcionario funcionarios[], int &numeroDeFuncionarios){

    solicitaCadastroFuncionario(funcionarios,numeroDeFuncionarios);

    cout << "-------------APÓS UM REAJUSTE DE 10%-------------"  << endl << endl;   

    reajusta_dez_porcento(funcionarios,numeroDeFuncionarios);

    mostraTodosFuncionarios(funcionarios,numeroDeFuncionarios);

}