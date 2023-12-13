#include "cadastro.h"
#include "verificacoes.h"

void preparaCadastro(vector<Produto> &produtos , int &quantidadeDeProdutos){
    string codigo;
    string nome;
    float preco;

    if(quantidadeDeProdutos < 300){
        do{
            cout << "Digite o codigo do produto : ";
            cin >> codigo;
        }while(!codigoPadrao(codigo) || !codigoDisponivel(produtos,codigo));

        do{
            cout << "Digite o nome do produto : ";
            cin >> nome;
        }while(!nomePadrao(nome) || !nomeDisponivel(produtos,nome));

        do{
            cout << "Digite o preço do produto : ";
            cin >> preco;
        }while(!verificaPreco(preco));

        cadastraProduto(produtos,quantidadeDeProdutos,codigo,nome,preco);
    }else{
        cout << "**Quantidade Máxima de produtos atingida**" << endl;
    }

}

void cadastraProduto(vector<Produto> &produtos , int &quantidadeDeProdutos,string codigo, string nome, float preco){
    Produto produto = {codigo,nome,preco};
    produtos.push_back(produto);
    quantidadeDeProdutos++;
    system("clear");
    cout << "Produto cadastrado com sucesso! " << endl;
}