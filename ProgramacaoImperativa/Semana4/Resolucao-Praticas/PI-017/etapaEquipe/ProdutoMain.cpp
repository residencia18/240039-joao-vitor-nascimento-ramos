#include <iostream>
#include <vector>
#include "Produto.h"
#include "CarrinhoDeCompras.h"

using namespace std;

int main()
{
  Produto produto1("Maçã", 2.5);
  Produto produto2("Arroz", 10.0);
  Produto produto3("Leite", 4.0);

  CarrinhoDeCompras carrinho;
  carrinho.adicionarProduto(produto1, 3);
  carrinho.adicionarProduto(produto2, 2);
  carrinho.adicionarProduto(produto3, 1);

  double valorTotal = carrinho.calcularValorTotal();
  cout << "\n\tValor total da compra: " << valorTotal << "\n" << endl;

  carrinho.removerProduto(produto2, 1);
  valorTotal = carrinho.calcularValorTotal();
  cout << "\n\tValor total após remoção: " << valorTotal << "\n" << endl;

  carrinho.esvaziarCarrinho();
  valorTotal = carrinho.calcularValorTotal();
  cout << "\n\tValor total após esvaziar o carrinho: " << valorTotal << "\n" << endl;

  Produto produto4("Chocolate", 3.0);
  carrinho.adicionarProduto(produto4, 10);
  cout << "\n\tQuantidade de chocolates no carinho: " << carrinho.getQuantidadeDeProdutos(produto4) << "\n" << endl;

  carrinho.esvaziarCarrinho();

  carrinho.adicionarProduto(produto1, 2);
  carrinho.adicionarProduto(produto2, 3);
  carrinho.adicionarProduto(produto3, 1);
  carrinho.adicionarProduto(produto4, 2);

  carrinho.exibirCarrinho();

  printf("\n\tValor total da compra: %.2f\n\n", carrinho.calcularValorTotal());
}