package br.com.cepedi.petshop.controller.DTO;

import java.math.BigInteger;

import br.com.cepedi.petshop.model.Venda_Produto;

public class Venda_ProdutoDTO {
	
    private String nomeCliente;
    private String nomeProduto;
    private BigInteger quantidade;

    public Venda_ProdutoDTO(Venda_Produto vendaProduto) {
        this.nomeCliente = vendaProduto.getVenda().getCliente().getNome();
        this.nomeProduto = vendaProduto.getProduto().getNome();
        this.quantidade = vendaProduto.getQuantidade();
    }


    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigInteger getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigInteger quantidade) {
        this.quantidade = quantidade;
    }
}
