package br.com.cepedi.petshop.controller.DTO;

import br.com.cepedi.petshop.model.TipoProduto;

public class TipoProdutoDTO {

    private String nome;

    public TipoProdutoDTO(TipoProduto tipoProduto) {
        this.nome = tipoProduto.getNome();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
