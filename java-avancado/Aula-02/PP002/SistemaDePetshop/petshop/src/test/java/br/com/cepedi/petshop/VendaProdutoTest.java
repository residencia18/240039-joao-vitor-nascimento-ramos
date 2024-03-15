package br.com.cepedi.petshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.model.Produto;
import br.com.cepedi.petshop.model.Venda;
import br.com.cepedi.petshop.model.Venda_Produto;

public class VendaProdutoTest {

    @Test
    public void testSetVenda_ValidVenda() {
        Venda venda = new Venda();
        Venda_Produto vendaProduto = new Venda_Produto();
        vendaProduto.setVenda(venda);
        assertNotNull(vendaProduto.getVenda());
        assertSame(venda, vendaProduto.getVenda());
    }

    @Test
    public void testSetProduto_ValidProduto() {
        Produto produto = new Produto();
        Venda_Produto vendaProduto = new Venda_Produto();
        vendaProduto.setProduto(produto);
        assertNotNull(vendaProduto.getProduto());
        assertSame(produto, vendaProduto.getProduto());
    }

    @Test
    public void testSetQuantidade_ValidQuantidade() {
        BigInteger quantidade = BigInteger.valueOf(5);
        Venda_Produto vendaProduto = new Venda_Produto();
        vendaProduto.setQuantidade(quantidade);
        assertEquals(quantidade, vendaProduto.getQuantidade());
    }
}