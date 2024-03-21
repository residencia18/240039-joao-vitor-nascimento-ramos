package br.com.cepedi.petshop.controller.DTO;

import br.com.cepedi.petshop.model.TipoPagamento;

public class TipoPagamentoDTO {
	
	
    private String nome;



    public TipoPagamentoDTO(TipoPagamento tipoPagamento) {
        this.nome = tipoPagamento.getNome();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
