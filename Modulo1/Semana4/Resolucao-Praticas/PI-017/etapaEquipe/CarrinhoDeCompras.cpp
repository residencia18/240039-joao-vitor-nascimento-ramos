#include "CarrinhoDeCompras.h"
#include <iostream>
#include <vector>
#include "Produto.h"

using namespace std;

CarrinhoDeCompras::CarrinhoDeCompras() {}

CarrinhoDeCompras::~CarrinhoDeCompras() {}

void CarrinhoDeCompras::adicionarProduto(Produto produto, double quantidade)
{
  itens_no_carrinho.push_back(make_pair(produto, quantidade));
}

void CarrinhoDeCompras::adicionarProduto(Produto produto, double quantidade)
{
  itens_no_carrinho.push_back(make_pair(produto, quantidade));
}

int CarrinhoDeCompras::getQuantidadeDeProdutos(Produto produto)
{
  int quantidade = 0;
  for (auto it = itens_no_carrinho.begin(); it != itens_no_carrinho.end(); it++)
  {
    if (it->second > 5)
    {
      quantidade = 5;
      break;
    }
  }
  return quantidade;
}

double CarrinhoDeCompras::calcularValorTotal()
{
  int valorTotal = 0;
  for (auto it = itens_no_carrinho.begin(); it != itens_no_carrinho.end(); it++)
  {
    valorTotal += (it->first.getPreco() * it->second);
  }
  return valorTotal;
}