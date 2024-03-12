package br.com.cepedi.petshop.controller.FORM;

import br.com.cepedi.petshop.model.TipoPagamento;

public class TipoPagamentoFORM {
    
    private String nome;

    public TipoPagamentoFORM() {
        // Construtor padrão vazio
    }

    public TipoPagamentoFORM(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public TipoPagamento toTipoPagamento() {
    	TipoPagamento tipo = new TipoPagamento();
    	tipo.setNome(nome);
    	return tipo;
    }
    
}