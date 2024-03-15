package br.com.cepedi.petshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.model.TipoPagamento;

public class TipoPagamentoTest {

    @Test
    public void testSetNome_ValidNome() {
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setNome("Cartão de Crédito");
        assertEquals("Cartão de Crédito", tipoPagamento.getNome());
    }

    @Test
    public void testSetNome_NullNome() {
        TipoPagamento tipoPagamento = new TipoPagamento();
        assertThrows(IllegalArgumentException.class, () -> tipoPagamento.setNome(null));
    }

    @Test
    public void testSetNome_EmptyNome() {
        TipoPagamento tipoPagamento = new TipoPagamento();
        assertThrows(IllegalArgumentException.class, () -> tipoPagamento.setNome(""));
    }
}