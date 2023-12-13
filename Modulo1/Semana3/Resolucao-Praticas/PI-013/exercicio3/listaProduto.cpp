#include "listaProduto.h"

void listarTodosProdutos(vector<Produto> &produtos ){
    cout << endl << "*LISTA DE PRODUTOS*" << endl;
    for(auto it=produtos.begin() ; it!=produtos.end() ; ++it){
        cout << endl;
        cout << "Codigo : " << it->codigo << endl;
        cout << "Nome   : " << it->nome << endl;
        cout << "Preço  : R$" << setprecision(3) << it->preco << endl;
        cout << endl ;

    }
}
