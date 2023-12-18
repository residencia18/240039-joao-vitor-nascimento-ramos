package br.com.cepedi.atividade2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.cepedi.atividade2.Exceptions.ProdutoNaoEncontradoException;
import br.com.cepedi.atividade2.listas.ListaProduto;

public class Pedido {

    public static int numeroPedidos = 0;

    private Map<Produto, Integer> itens;
    private Cliente cliente;
    private int codigo;

    public Pedido(Cliente cliente) {
        super();
        numeroPedidos++;
        this.codigo = numeroPedidos;
        this.itens = new HashMap<>();
        this.cliente = cliente;
    }

    public void adicionaItemPedido(Produto itemPedido, int quantidade) {
        if (itens.containsKey(itemPedido)) {
            int quantidadeAtual = itens.get(itemPedido);
            itens.put(itemPedido, quantidadeAtual + quantidade);
        } else {
            itens.put(itemPedido, quantidade);
        }
    }

    public void removeItemPedido(Produto itemPedido) {
        itens.remove(itemPedido);
    }

    public void mostraValorTotalPedido(int percentualDesconto) {
        double valorTotal = 0;
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto item = entry.getKey();
            int quantidade = entry.getValue();
            valorTotal += item.getPreco() * quantidade;
        }
        double valorDoDesconto = valorTotal * percentualDesconto;
        System.out.println("Valor total do pedido: " + valorTotal);
        System.out.println("Valor do desconto: " + valorDoDesconto);
        System.out.println("Valor final do pedido: " + (valorTotal - valorDoDesconto));
    }

    public void mostraValorTotalPedido(int qntPrestacoes, int percentualJuros) {
        double valorTotal = 0;
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto item = entry.getKey();
            int quantidade = entry.getValue();
            valorTotal += item.getPreco() * quantidade;
        }
        double juros = valorTotal * qntPrestacoes * percentualJuros / 100;
        System.out.println("Valor total do pedido: " + valorTotal);
        System.out.println("Numero de prestações : " + qntPrestacoes);
        System.out.println("Valor dos juros : " + juros);
        System.out.println("Valor Final : " + (valorTotal + juros));
    }

    public void preenchePedido(Scanner scanner, ListaProduto produtos) {
        String codigoProduto;
        int quantidade;
        do {
            System.out.println("Adicionar produto ao pedido:");
            produtos.mostrarProdutos();
            System.out.print("Digite o código do produto (ou 0 para encerrar o pedido): ");
            
            try {
                codigoProduto = scanner.nextLine();
                Produto produto;
                if (codigoProduto.equals("0")) {
                    break; // Sai do loop se o usuário escolher encerrar o pedido
                }
                try {
                    produto = produtos.buscarProdutoPorCodigo(codigoProduto);
                    System.out.print("Digite a quantidade desejada: ");
                    quantidade = Integer.parseInt(scanner.nextLine());
                    if (quantidade > 0) {
                        this.adicionaItemPedido(produto, quantidade);
                        System.out.println("Produto adicionado ao pedido: " + produto + " (Quantidade: " + quantidade + ")");
                    } else {
                        System.out.println("Quantidade inválida. Tente novamente.");
                    }
                } catch (ProdutoNaoEncontradoException e) {
                    System.err.println("Erro : " + e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: escolha inválida. " + e.getMessage());
            }
        } while (true);
    }
}
