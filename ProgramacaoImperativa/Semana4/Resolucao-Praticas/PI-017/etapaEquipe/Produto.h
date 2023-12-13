#ifndef PRODUTO_H
#define PRODUTO_H
#include <iostream>
#include <string>

using namespace std;

#pragma once

class Produto
{
public:

    int codigo = 0;
    string nome;
    double preco;
    int quantidadeEmEstoque;
    vector<Produto> produtos;

    Produto(string nome, double preco)
    {
        this->nome = nome;
        this->preco = preco;
    }

    Produto(string nome, double preco, int quantidadeEmEstoque)
    {
        this->nome = nome;
        this->preco = preco;
        this->quantidadeEmEstoque = quantidadeEmEstoque;
    }

    Produto() {}

    ~Produto() {}

    int getCodigo()
    {
        return codigo;
    }

    string getNome()
    {
        return this->nome;
    }

    void setNome(string nome)
    {
        this->nome = nome;
    }

    double getPreco()
    {
        return this->preco;
    }

    void setPreco(double preco)
    {
        this->preco = preco;
    }

    int getQuantidadeEmEstoque()
    {
        return this->quantidadeEmEstoque;
    }

    void setQuantidadeEmEstoque(int quantidadeEmEstoque)
    {
        this->quantidadeEmEstoque = quantidadeEmEstoque;
    }

    int codigoDoProduto()
    {
        return codigo++;
    }

private:

};

#endif