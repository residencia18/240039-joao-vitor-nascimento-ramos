package br.com.cepedi.petshop;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.exceptions.PrecoInvalidoException;
import br.com.cepedi.petshop.model.Produto;

public class ProdutoTest {

    @Test
    public void testSetNome_ValidNome() {
        Produto produto = new Produto();
        produto.setNome("Ração para Cães");
        assertEquals("Ração para Cães", produto.getNome());
    }

    @Test
    public void testSetNome_NullNome() {
        Produto produto = new Produto();
        assertThrows(IllegalArgumentException.class, () -> produto.setNome(null));
    }

    @Test
    public void testSetNome_EmptyNome() {
        Produto produto = new Produto();
        assertThrows(IllegalArgumentException.class, () -> produto.setNome(""));
    }

    @Test
    public void testSetPreco_ValidPreco() throws PrecoInvalidoException {
        Produto produto = new Produto();
        BigDecimal preco = new BigDecimal("10.50");
        produto.setPreco(preco);
        assertEquals(preco, produto.getPreco());
    }

    @Test
    public void testSetPreco_ZeroPreco() {
        Produto produto = new Produto();
        BigDecimal preco = BigDecimal.ZERO;
        assertThrows(PrecoInvalidoException.class, () -> produto.setPreco(preco));
    }

    @Test
    public void testSetPreco_NegativePreco() {
        Produto produto = new Produto();
        BigDecimal preco = new BigDecimal("-10.50");
        assertThrows(PrecoInvalidoException.class, () -> produto.setPreco(preco));
    }
}