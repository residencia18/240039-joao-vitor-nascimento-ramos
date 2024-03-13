package br.com.cepedi.petshop.controller.FORM;

import br.com.cepedi.petshop.model.TipoProduto;

public class TipoProdutoFORM {
    
    private String nome;

    public TipoProdutoFORM() {

    }

    public TipoProdutoFORM(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    public TipoProduto toTipoProduto() {
    	TipoProduto tipo = new TipoProduto();
    	tipo.setNome(nome);
    	return tipo;
    }
    
}
