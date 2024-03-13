package br.com.cepedi.petshop.controller.DTO;

import java.math.BigDecimal;

import br.com.cepedi.petshop.model.Produto;

public class ProdutoDTO {
    
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String nomeTipoProduto;
    private String nomeMarca; 

    public ProdutoDTO(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.nomeTipoProduto = produto.getTipoProduto().getNome();
        this.nomeMarca = produto.getMarca().getNome(); 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getNomeTipoProduto() {
        return nomeTipoProduto;
    }

    public void setNomeTipoProduto(String nomeTipoProduto) {
        this.nomeTipoProduto = nomeTipoProduto;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
}
