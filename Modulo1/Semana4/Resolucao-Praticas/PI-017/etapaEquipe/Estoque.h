#ifndef ESTOQUE_H
#define ESTOQUE_H
#include <iostream>
#include <vector>
#include "Produto.h"
#include <string>

using namespace std;

#pragma once

class Estoque
{
public:

    vector<Produto> produtosEmEstoque;

    Estoque() {}

    ~Estoque() {}

    void cadastrarProduto(Produto &produto)
    {
        Estoque estoque;
        cout << "\n\tDigite o nome do produto: ";
        getline(cin, produto.nome);

        cout << "\n\tDigite o preço do produto: ";
        cin >> produto.preco;

        cout << "\n\tDigite a quantidade do produto: ";
        cin >> produto.quantidadeEmEstoque;

        produto.codigoDoProduto();
        this->produtosEmEstoque.push_back(produto);
    }

    void removerProduto(Produto &produto)
    {
        for (auto it = produtosEmEstoque.begin(); it != produtosEmEstoque.end(); it++)
        {
            if (it->getNome() == produto.getNome())
            {
                produtosEmEstoque.erase(it);
                break;
            }
        }
    }

    void listarProdutos()
    {
        cout << "\n\tProdutos cadastrados: " << endl;
        for (auto it = produtosEmEstoque.begin(); it != produtosEmEstoque.end(); it++)
        {
            cout << "\n\tNome: " << it->getNome() << endl;
            cout << "\n\tPreço: " << it->getPreco() << endl;
            cout << "\n\tQuantidade: " << it->getQuantidadeEmEstoque() << endl;
            cout << "\n\tCódigo: " << it->getCodigo() << endl;
        }
    }

private:
};

#endif