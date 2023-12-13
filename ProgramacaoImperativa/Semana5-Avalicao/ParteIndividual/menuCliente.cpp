#include "menuCliente.hpp"
#include "listaClientes.hpp"

void mostraMenuCliente(){
    limpaTela();
    cout << "MODULO DE GESTÃO DE CLIENTES" << endl << endl;
    cout << "1. Inserir" << endl;
    cout << "2. Excluir" << endl;
    cout << "3. Alterar Informações" << endl;
    cout << "4. Listar" << endl;
    cout << "5. Localizar" << endl;
    cout << "0. Sair" << endl << endl;
    cout << "Escolha : ";

}

void menuCliente(vector<Cliente> &listaClientes){
    int escolha;
    do{
        escolha = recebeEscolha();
        realizaEscolha(escolha,listaClientes);
    }while(escolha!=0);
}

int recebeEscolha(){
    int escolha;
    bool escolhaValida;
    do{
        mostraMenuCliente();
        cin >> escolha;
        escolhaValida = (escolha== 0 || escolha== 1 || escolha== 2 || escolha== 3 || escolha== 4 || escolha== 5);
        if(!escolhaValida){
            limpaTela();
            cout << "------Escolha Inválida------" << endl;
            pause();
            limpaBuffer();
        }
    }while(!escolhaValida);
    return escolha;
}

void realizaEscolha(const int &escolha, vector<Cliente> &listaClientes){
  switch (escolha)
    {
    case 1 :
            insereCliente(listaClientes);
        break;
    case 2 :
            excluiCliente(listaClientes);
        break;
    case 3 :
            alteraCliente(listaClientes);
        break;
    case 4 :
            listarClientes(listaClientes);
        break;
    case 5 :
            localizaCliente(listaClientes);
        break;
    case 0 :
            limpaTela();
            return;
    default:
        break;
    }
}