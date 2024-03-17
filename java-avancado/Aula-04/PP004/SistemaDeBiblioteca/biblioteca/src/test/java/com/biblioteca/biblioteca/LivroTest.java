package com.biblioteca.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.model.Editora;
import com.biblioteca.biblioteca.model.Livro;

class LivroTest {

    @Test
    void testSetNome_ValidNome() {
        Livro livro = new Livro();
        livro.setNome("Livro Teste");
        assertEquals("Livro Teste", livro.getNome());
    }

    @Test
    void testSetNome_NullNome() {
        Livro livro = new Livro();
        assertThrows(IllegalArgumentException.class, () -> livro.setNome(null));
    }

    @Test
    void testSetNome_EmptyNome() {
        Livro livro = new Livro();
        assertThrows(IllegalArgumentException.class, () -> livro.setNome(""));
    }

    @Test
    void testSetAutor_ValidAutor() {
        Livro livro = new Livro();
        Autor autor = mock(Autor.class);
        livro.setAutor(autor);
        assertEquals(autor, livro.getAutor());
    }

    @Test
    void testSetAutor_NullAutor() {
        Livro livro = new Livro();
        assertThrows(IllegalArgumentException.class, () -> livro.setAutor(null));
    }

    @Test
    void testSetEditora_ValidEditora() {
        Livro livro = new Livro();
        Editora editora = mock(Editora.class);
        livro.setEditora(editora);
        assertEquals(editora, livro.getEditora());
    }

    @Test
    void testSetEditora_NullEditora() {
        Livro livro = new Livro();
        assertThrows(IllegalArgumentException.class, () -> livro.setEditora(null));
    }
}
