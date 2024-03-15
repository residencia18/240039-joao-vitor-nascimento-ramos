package br.com.cepedi.petshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.model.TipoProduto;

public class TipoProdutoTest {

    @Test
    public void testSetNome_ValidNome() {
        TipoProduto tipoProduto = new TipoProduto();
        tipoProduto.setNome("Ração");
        assertEquals("Ração", tipoProduto.getNome());
    }

    @Test
    public void testSetNome_NullNome() {
        TipoProduto tipoProduto = new TipoProduto();
        assertThrows(IllegalArgumentException.class, () -> tipoProduto.setNome(null));
    }

    @Test
    public void testSetNome_EmptyNome() {
        TipoProduto tipoProduto = new TipoProduto();
        assertThrows(IllegalArgumentException.class, () -> tipoProduto.setNome(""));
    }
}
