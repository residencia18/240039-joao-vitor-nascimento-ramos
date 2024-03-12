package br.com.cepedi.petshop.controller.FORM;

import java.math.BigDecimal;

public class VendaFORM {
    
    private Long idCliente;
    private BigDecimal valorTotal;
    
    
   
    public VendaFORM() {
		super();
	}

	public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    

}
