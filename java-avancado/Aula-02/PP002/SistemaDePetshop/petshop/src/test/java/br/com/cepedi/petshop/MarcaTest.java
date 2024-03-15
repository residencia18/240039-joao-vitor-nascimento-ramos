package br.com.cepedi.petshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.model.Marca;

public class MarcaTest {

    @Test
    public void testSetNome_ValidNome() {
        Marca marca = new Marca();
        marca.setNome("Marca Teste");
        assertEquals("Marca Teste", marca.getNome());
    }

    @Test
    public void testSetNome_NullNome() {
        Marca marca = new Marca();
        assertThrows(IllegalArgumentException.class, () -> marca.setNome(null));
    }

    @Test
    public void testSetNome_EmptyNome() {
        Marca marca = new Marca();
        assertThrows(IllegalArgumentException.class, () -> marca.setNome(""));
    }
}