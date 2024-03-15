package br.com.cepedi.petshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.exceptions.PrecoInvalidoException;
import br.com.cepedi.petshop.model.Cliente;
import br.com.cepedi.petshop.model.Venda;

public class VendaTest {

    @Test
    public void testSetCliente_ValidCliente() {
        Cliente cliente = new Cliente();
        Venda venda = new Venda();
        venda.setCliente(cliente);
        assertNotNull(venda.getCliente());
        assertSame(cliente, venda.getCliente());
    }

    @Test
    public void testAdicionarValor_ValidValor() throws PrecoInvalidoException {
        Venda venda = new Venda();
        BigDecimal valor = new BigDecimal("10.50");
        venda.adicionarValor(valor);
        assertEquals(valor, venda.getValor_total());
    }

    @Test
    public void testAdicionarValor_InvalidValor() {
        Venda venda = new Venda();
        BigDecimal valor = BigDecimal.ZERO;
        assertThrows(PrecoInvalidoException.class, () -> venda.adicionarValor(valor));
    }

    @Test
    public void testRetirarValor_ValidValor() throws PrecoInvalidoException {
        Venda venda = new Venda();
        BigDecimal valorInicial = new BigDecimal("20.00");
        BigDecimal valorRetirado = new BigDecimal("10.50");
        venda.setValor_total(valorInicial);
        venda.retiraValor(valorRetirado);
        assertEquals(new BigDecimal("9.50"), venda.getValor_total());
    }

    @Test
    public void testRetirarValor_InvalidValor() {
        Venda venda = new Venda();
        BigDecimal valor = BigDecimal.ZERO;
        assertThrows(PrecoInvalidoException.class, () -> venda.retiraValor(valor));
    }
}