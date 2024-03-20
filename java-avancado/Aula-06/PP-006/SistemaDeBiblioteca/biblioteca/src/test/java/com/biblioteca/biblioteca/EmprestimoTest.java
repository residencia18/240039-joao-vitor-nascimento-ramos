package com.biblioteca.biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.biblioteca.biblioteca.model.Cliente;
import com.biblioteca.biblioteca.model.Emprestimo;
import com.biblioteca.biblioteca.model.Livro;

class EmprestimoTest {

    @Test
    void testSetCliente_ValidCliente() {
        Emprestimo emprestimo = new Emprestimo();
        Cliente cliente = mock(Cliente.class);
        emprestimo.setCliente(cliente);
        assertEquals(cliente, emprestimo.getCliente());
    }

    @Test
    void testSetCliente_NullCliente() {
        Emprestimo emprestimo = new Emprestimo();
        assertThrows(IllegalArgumentException.class, () -> emprestimo.setCliente(null));
    }

    @Test
    void testSetLivro_ValidLivro() {
        Emprestimo emprestimo = new Emprestimo();
        Livro livro = mock(Livro.class);
        emprestimo.setLivro(livro);
        assertEquals(livro, emprestimo.getLivro());
    }

    @Test
    void testSetLivro_NullLivro() {
        Emprestimo emprestimo = new Emprestimo();
        assertThrows(IllegalArgumentException.class, () -> emprestimo.setLivro(null));
    }

    @Test
    void testSetDataEmprestimo_ValidData() {
        Emprestimo emprestimo = new Emprestimo();
        LocalDateTime dataEmprestimo = LocalDateTime.now();
        emprestimo.setData_emprestimo(dataEmprestimo);
        assertEquals(dataEmprestimo, emprestimo.getData_emprestimo());
    }

    @Test
    void testSetDataDevolucao_ValidData() {
        Emprestimo emprestimo = new Emprestimo();
        LocalDateTime dataDevolucao = LocalDateTime.now();
        emprestimo.setData_devolucao(dataDevolucao);
        assertEquals(dataDevolucao, emprestimo.getData_devolucao());
    }
}
