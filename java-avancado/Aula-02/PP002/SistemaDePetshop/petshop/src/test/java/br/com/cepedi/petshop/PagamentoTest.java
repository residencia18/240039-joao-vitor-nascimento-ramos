package br.com.cepedi.petshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.model.Pagamento;
import br.com.cepedi.petshop.model.TipoPagamento;
import br.com.cepedi.petshop.model.Venda;


public class PagamentoTest {

    @Test
    public void testSetTipoPagamento_ValidTipoPagamento() {
        TipoPagamento tipoPagamento = new TipoPagamento();
        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(tipoPagamento);
        assertNotNull(pagamento.getTipoPagamento());
        assertSame(tipoPagamento, pagamento.getTipoPagamento());
    }

    @Test
    public void testSetVenda_ValidVenda() {
        Venda venda = new Venda();
        Pagamento pagamento = new Pagamento();
        pagamento.setVenda(venda);
        assertNotNull(pagamento.getVenda());
        assertSame(venda, pagamento.getVenda());
    }
}