#include "menu.h"


void imprimeOpcoes(){
    cout << "------------MENU------------" << endl;
    cout << "1-    Cadastrar novo produto" << endl;
    cout << "2-           Remover produto" << endl;
    cout << "3-  Listar todos os produtos" << endl;
    cout << "4-         Consultar produto" << endl;
    cout << "5 -                     Sair" << endl;
    cout << endl;
    cout << "Escolha : ";
}

void menu(vector<Produto> &produtos, int &quantidadeDeProdutos){

    int escolha;

    while(1){
        imprimeOpcoes();
        cin >> escolha;
        switch (escolha)
        {
        case 1:
            preparaCadastro(produtos,quantidadeDeProdutos);
            break;
        case 2:
            preparaRemocao(produtos,quantidadeDeProdutos);
            break;
        case 3:
            listarTodosProdutos(produtos);
            break;
        case 4:
            consultarProduto(produtos);
            break;
        case 5:
            exit(0);
        default:
            break;
        }
    }

}