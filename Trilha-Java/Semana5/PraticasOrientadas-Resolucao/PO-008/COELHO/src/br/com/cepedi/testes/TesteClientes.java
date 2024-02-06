package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.conjuntos.Clientes;
import br.com.cepedi.exceptions.ConjuntoClientes.ClienteNaoEncontradoException;
import br.com.cepedi.model.Cliente;

class TesteClientes {

	@Test
	void test() {
		adicionarCliente();
		buscarClienteExistente();
		buscarClienteNaoExistente();
		deletarClienteExistente();
		deletarClienteNaoExistente();
	}
	
    void adicionarCliente() {
        Clientes clientes = new Clientes();
        Cliente cliente = null; 

        try {
        	cliente = new Cliente("João", "04999695537");
            clientes.adicionar(cliente);
        } catch (Exception e) {
            fail("Não era esperada nenhuma exceção durante a adição do cliente");
        }

        assertTrue(clientes.contains(cliente));
    }

    void buscarClienteExistente() {
        Clientes clientes = new Clientes();
        Cliente cliente = null; 
        Cliente clienteEncontrado = null;

        try {
        	cliente = new Cliente("João", "04999695537");
            clientes.adicionar(cliente);
        } catch (Exception e) {
            fail("Não era esperada nenhuma exceção durante a adição do cliente");
        }

        try {
        	clienteEncontrado = clientes.buscar(cliente.getId());
            assertEquals(cliente, clienteEncontrado);
        } catch (Exception e) {
            fail("Não era esperada nenhuma exceção durante a busca do cliente");
        }
        
        assertEquals(cliente,clienteEncontrado);
    }

    void buscarClienteNaoExistente() {
        Clientes clientes = new Clientes();

        assertThrows(ClienteNaoEncontradoException.class, () -> {clientes.buscar(50);});
    }

    void deletarClienteExistente() {
        Clientes clientes = new Clientes();
        Cliente cliente = null;
        try {
        	cliente = new Cliente("João", "04999695537");
            clientes.adicionar(cliente);
        } catch (Exception e) {
            fail("Não era esperada nenhuma exceção durante a adição do cliente");
        }

        try {
            clientes.deletar(cliente.getId());
        } catch (Exception e) {
            fail("Não era esperada nenhuma exceção durante a remoção do cliente");
        }

        assertFalse(clientes.contains(cliente));
    }

    void deletarClienteNaoExistente() {
        Clientes clientes = new Clientes();

        assertThrows(ClienteNaoEncontradoException.class, () -> {
            clientes.deletar(1);
        });
    }

}
