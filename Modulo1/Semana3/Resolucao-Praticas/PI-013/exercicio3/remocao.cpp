#include "remocao.h"

void preparaRemocao(vector<Produto> &produtos , int &quantidadeDeProdutos){
    int escolha;
    bool escolhaErrada = false;
    cout << "Deseja remover pelo codigo ou pelo nome ? " << endl;
    do{
        cout << "1 - Codigo" << endl;
        cout << "2 -   Nome" << endl;
        cout << "Escolha : ";
        cin >> escolha;
        escolhaErrada = (escolha!=1 && escolha!=2);
        if(escolhaErrada){
            cout << "**Escolha Inválida**" << endl;
        }
    }while(escolhaErrada);
    
    if(escolha==1){
        removerPorCodigo(produtos,quantidadeDeProdutos);
    }else{
        removerPorNome(produtos,quantidadeDeProdutos);
    }
}

void removerPorCodigo(vector<Produto> &produtos, int &quantidadeDeProdutos){

    string codigo;

    do{
        cout << "Digite o codigo do produto : ";
        cin >> codigo;
    }while(!codigoPadrao(codigo));

    for(auto it=produtos.begin(); it!=produtos.end() ;++it){
        if(it->codigo == codigo){
            produtos.erase(it);
            quantidadeDeProdutos--;
            system("clear");
            cout << "Produto removido com sucesso! " << endl;
            return;
        }
    }
}

void removerPorNome(vector<Produto> &produtos, int &quantidadeDeProdutos){

    string nome;

    do{
        cout << "Digite o nome do produto : ";
        cin >> nome;
    }while(!nomePadrao(nome));

    for(auto it=produtos.begin(); it!=produtos.end() ;++it){
        if(it->nome == nome){
            produtos.erase(it);
            quantidadeDeProdutos--;
            system("clear");
            cout << "Produto removido com sucesso! " << endl;
            return;
        }
    }

}


