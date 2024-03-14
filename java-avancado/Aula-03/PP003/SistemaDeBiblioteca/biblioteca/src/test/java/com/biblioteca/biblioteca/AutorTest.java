package com.biblioteca.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.biblioteca.biblioteca.model.Autor;

class AutorTest {

    @Test
    void testSetNome_ValidNome() {
        Autor autor = new Autor();
        autor.setNome("Autor Teste");
        assertEquals("Autor Teste", autor.getNome());
    }

    @Test
    void testSetNome_NullNome() {
        Autor autor = new Autor();
        assertThrows(IllegalArgumentException.class, () -> autor.setNome(null));
    }

    @Test
    void testSetNome_EmptyNome() {
        Autor autor = new Autor();
        assertThrows(IllegalArgumentException.class, () -> autor.setNome(""));
    }
}
