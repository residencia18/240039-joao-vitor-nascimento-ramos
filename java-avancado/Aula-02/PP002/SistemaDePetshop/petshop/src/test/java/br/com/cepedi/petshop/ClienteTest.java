package br.com.cepedi.petshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.cepedi.petshop.exceptions.CPFInvalidoException;
import br.com.cepedi.petshop.exceptions.NomeInvalidoException;
import br.com.cepedi.petshop.model.Cliente;

class ClienteTest {

    @Test
    public void testSetNome_ValidNome() throws NomeInvalidoException {
        Cliente cliente = new Cliente();
        cliente.setNome("João da Silva");
        assertEquals("João da Silva", cliente.getNome());
    }

    @Test
    public void testSetNome_NullNome() {
        Cliente cliente = new Cliente();
        assertThrows(IllegalArgumentException.class, () -> cliente.setNome(null));
    }

    @Test
    public void testSetNome_EmptyNome() {
        Cliente cliente = new Cliente();
        assertThrows(IllegalArgumentException.class, () -> cliente.setNome(""));
    }

    @Test
    public void testSetNome_InvalidNome() {
        Cliente cliente = new Cliente();
        assertThrows(NomeInvalidoException.class, () -> cliente.setNome("12345"));
    }

    @Test
    public void testSetCpf_ValidCpf() throws CPFInvalidoException {
        Cliente cliente = new Cliente();
        cliente.setCpf("049.996.955-37");
        assertEquals("04999695537", cliente.getCpf());
    }

    @Test
    public void testSetCpf_NullCpf() {
        Cliente cliente = new Cliente();
        assertThrows(IllegalArgumentException.class, () -> cliente.setCpf(null));
    }

    @Test
    public void testSetCpf_EmptyCpf() {
        Cliente cliente = new Cliente();
        assertThrows(IllegalArgumentException.class, () -> cliente.setCpf(""));
    }

    @Test
    public void testSetCpf_InvalidCpf() {
        Cliente cliente = new Cliente();
        assertThrows(CPFInvalidoException.class, () -> cliente.setCpf("123.456.789-00"));
    }
}
