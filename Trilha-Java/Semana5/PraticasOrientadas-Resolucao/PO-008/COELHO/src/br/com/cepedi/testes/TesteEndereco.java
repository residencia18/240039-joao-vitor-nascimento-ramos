package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Endereco;
import br.com.cepedi.model.Estado;

class TesteEndereco {

	@Test
	void test() {
		testInstanciaEndereco();
        testSetRua();
        testSetNumero();
        testSetBairro();
        testSetCidade();
        testSetEstado();
        testSetCEP();
    }

	
	void testInstanciaEndereco() {
        Endereco endereco = null;
        try {
            endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
        } catch (Exception e) {
            fail("Não deve cair aqui");
        }
        assertNotNull(endereco);
    }
	
	
	void testSetRua() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000");
        endereco.setRua("Nova Rua");
        assertEquals("Nova Rua", endereco.getRua());

        // Teste para valor nulo
        try {
            endereco.setRua(null);
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio",e.getMessage());
        }

        // Teste para valor vazio
        try {
            endereco.setRua("");
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio",e.getMessage());
        }
    }
	
    void testSetNumero() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.RIO_GRANDE_DO_NORTE,  "45880-200");

        // Teste para número negativo
        try {
            endereco.setNumero(-1);
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("não existem casas com números negativos", e.getMessage());
        }

        // Teste para número zero
        try {
            endereco.setNumero(0);
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("não existem casas com número zero", e.getMessage());
        }

        // Teste para número positivo
        endereco.setNumero(456);
        assertEquals(456, endereco.getNumero());
    }

    void testSetBairro() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste",Estado.SAO_PAULO, "45880-200");
        endereco.setBairro("Novo Bairro");
        assertEquals("Novo Bairro", endereco.getBairro());

        // Teste para valor nulo
        try {
            endereco.setBairro(null);
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        }

        // Teste para valor vazio
        try {
            endereco.setBairro("");
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        }
    }
    
    void testSetCidade() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste" ,Estado.CEARA ,  "45880-200");
        endereco.setCidade("Nova Cidade");
        assertEquals("Nova Cidade", endereco.getCidade());

        // Teste para valor nulo
        try {
            endereco.setCidade(null);
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        }

        // Teste para valor vazio
        try {
            endereco.setCidade("");
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        }
    }

    void testSetEstado() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ACRE, "45880-000");
        endereco.setEstado(Estado.AMAPA);
        assertEquals(Estado.AMAPA, endereco.getEstado());

        // Teste para valor nulo
        try {
            endereco.setEstado(null);
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Tentativa de inserir valor nulo para o estado", e.getMessage());
        }

        // Teste para valor vazio
        try {
            endereco.setEstado(Estado.valueOf(""));
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Tentativa de inserir valor nulo ou vazio para o estado", e.getMessage());
        }

        // Teste para estado inválido
        try {
            endereco.setEstado(Estado.valueOf("ESTADO_INVALIDO"));
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Tentativa de inserir valor inválido para o estado", e.getMessage());
        }
    }

    void testSetCEP() {
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ACRE, "45880-200");

        // Teste para valor nulo
        try {
            endereco.setCEP(null);
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        }

        // Teste para valor vazio
        try {
            endereco.setCEP("");
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        }
        
        try {
        	endereco.setCEP("5555-412");
            fail("Deveria ter lançado uma exceção");
        }catch(Exception e ) {
            assertEquals("CEP inválido", e.getMessage());

        }
    }
	
}
