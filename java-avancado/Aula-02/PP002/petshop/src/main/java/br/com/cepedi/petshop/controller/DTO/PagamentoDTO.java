package br.com.cepedi.petshop.controller.DTO;

import br.com.cepedi.petshop.model.Pagamento;

public class PagamentoDTO {
	
    private String tipoPagamento;
    private Long idVenda;

    // Construtor com um objeto Pagamento
    public PagamentoDTO(Pagamento pagamento) {
        this.tipoPagamento = pagamento.getTipoPagamento().getNome();
        this.idVenda = pagamento.getVenda().getId();
    }

    // Getters e setters


    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }
}

