package br.com.cepedi.petshop.controller.FORM;


public class PagamentoFORM {
    
    private Long idTipoPagamento;
    private Long idVenda;

    public PagamentoFORM() {
        // Construtor padrão vazio
    }

    public PagamentoFORM(Long idTipoPagamento, Long idVenda) {
        this.idTipoPagamento = idTipoPagamento;
        this.idVenda = idVenda;
    }

    public Long getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(Long idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }
}
