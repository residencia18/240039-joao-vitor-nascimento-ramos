package br.com.cepedi.petshop.controller.DTO;

import java.math.BigDecimal;

import br.com.cepedi.petshop.model.Venda;

public class VendaDTO {
	
    private BigDecimal valorTotal;
    private String nomeCliente;

    // Construtor com um objeto Venda
    public VendaDTO(Venda venda) {
        this.valorTotal = venda.getValor_total();
        this.nomeCliente = venda.getCliente().getNome();
    }

    // Getters e setters

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
