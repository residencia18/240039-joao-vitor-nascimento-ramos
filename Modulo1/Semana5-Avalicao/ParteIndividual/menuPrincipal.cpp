#include "menuPrincipal.hpp"

void menuPrincipal(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao){
    int escolha;
    do{ 
        limpaTela();
        cout << "MENU PRINCIPAL DE GESTÃO" << endl << endl;
        cout << "1. Menu de Clientes" << endl << "2. Menu de Veiculos" << endl << "3. Menu de Locacao" << endl << "0. Sair do Programa" << endl << endl;
        cout << "Escolha : ";
        cin >> escolha;
        switch (escolha)
        {
        case 1:
            menuCliente(listaClientes);
            break;
        case 2:
            menuVeiculo(listaVeiculos);
            break;
        case 3:
            menuLocacao(listaClientes,listaVeiculos,listaLocacao);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}