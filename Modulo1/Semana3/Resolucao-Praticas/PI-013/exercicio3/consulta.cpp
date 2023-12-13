#include "consulta.h"

void consultarProduto(vector<Produto> &produtos){

    string codigo;
    
    do{
        cout << "Digite o codigo do produto que deseja consultar : ";
        cin >> codigo;
    }while(!codigoPadrao(codigo));

    for(auto it=produtos.begin(); it!=produtos.end() ;++it){
        if(it->codigo == codigo){
            cout << endl;
            cout << "Nome   : " << it->nome << endl;
            cout << "Preço  : R$" << it->preco << endl;
            cout << endl ;
            return;
        }
    }
    
}