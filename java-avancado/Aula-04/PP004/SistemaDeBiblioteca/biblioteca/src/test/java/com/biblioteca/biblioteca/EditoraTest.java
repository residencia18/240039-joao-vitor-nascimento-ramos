package com.biblioteca.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.biblioteca.biblioteca.model.Editora;

class EditoraTest {

    @Test
    void testSetNome_ValidNome() {
        Editora editora = new Editora();
        editora.setNome("Editora XYZ");
        assertEquals("Editora XYZ", editora.getNome());
    }

    @Test
    void testSetNome_NullNome() {
        Editora editora = new Editora();
        assertThrows(IllegalArgumentException.class, () -> editora.setNome(null));
    }

    @Test
    void testSetNome_EmptyNome() {
        Editora editora = new Editora();
        assertThrows(IllegalArgumentException.class, () -> editora.setNome(""));
    }
}
