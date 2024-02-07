package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.conjuntos.Imoveis;
import br.com.cepedi.model.Endereco;
import br.com.cepedi.model.Estado;
import br.com.cepedi.model.Imovel;
import br.com.cepedi.model.Relogio;

class TesteImoveis {

	@Test
	void test() {
		testAdicionar();
		testAdicionarImovelNulo();
		testAdicionarDuplicado();
		testBuscar();
		testBuscarImovelInexistente();
		testAtualizarMatricula();
		testAtualizarEndereco();
		testAtualizarMatriculaValorNulo();
		testAtualizarEnderecoValorNulo();
	}
	


    void testAdicionar() {
    	Imoveis imoveis = null;
    	Imovel imovel = null;
    	
    	try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(imovel);
    	}catch(Exception e ) {
    		fail("não deve cair aqui");
    	}
    }

    void testAdicionarImovelNulo() {
    	Imoveis imoveis = null;
    	Imovel imovel = null;
    	
    	try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(null);
    	}catch(Exception e ) {
    		assertEquals("tentativa de inserir valor nulo ou vazio",e.getMessage());
    	}
    }
    
    void testAdicionarDuplicado() {
    	Imoveis imoveis = null;
    	Imovel imovel = null;
    	
    	try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(imovel);
            imoveis.adicionar(imovel);
    	}catch(Exception e ) {
    		assertEquals("Imovel já existente",e.getMessage());
    	}

    }

    void testBuscar() {
    	Imoveis imoveis = null;
    	Imovel imovel = null , imovel2 = null;
    	
    	try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(imovel);
            imovel2 = imoveis.buscar(imovel.getId());
    	}catch(Exception e ) {
    		fail("nao deve cair aqui");
    	}
    	
    	assertEquals(imovel,imovel2);
    }
    
    void testBuscarImovelInexistente() {
    	Imoveis imoveis = null;
    	Imovel imovel = null , imovel2 = null;
    	
    	try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(imovel);
            imovel2 = imoveis.buscar(15);
    	}catch(Exception e ) {
    		assertEquals("Imovel não encontrado",e.getMessage());
    	}
    	
    	assertNotEquals(imovel,imovel2);
    }
    
    void testAtualizarMatricula() {
        Imoveis imoveis;
        Imovel imovel;
        try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(imovel);
            imoveis.atualizar(imovel.getId(), "456DEF");
            assertEquals("456DEF", imovel.getMatricula());
        } catch (Exception e) {
            fail("Não deve cair aqui");
        }
    }

    void testAtualizarEndereco() {
        Imoveis imoveis;
        Imovel imovel;
        try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(imovel);
            Endereco novoEndereco = new Endereco("Nova Rua", 456, "Novo Bairro", "Nova Cidade", Estado.ESPIRITO_SANTO, "12345-678");
            imoveis.atualizar(imovel.getId(), novoEndereco);
            assertEquals(novoEndereco, imovel.getEndereco());
        } catch (Exception e) {
            fail("Não deve cair aqui");
        }
    }


    void testDeletar() {
        Imoveis imoveis;
        Imovel imovel;
        try {
        	imoveis = new Imoveis();
        	imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));
            imoveis.adicionar(imovel);
            imoveis.deletar(imovel.getId());
            assertTrue(imoveis.isEmpty());
        } catch (Exception e) {
            fail("Não deve cair aqui");
        }
    }
    
    void testAdicionarValorNulo() {
        Imoveis imoveis = new Imoveis();
        Imovel imovel = null;
        
        try {
            imoveis.adicionar(imovel);
            fail("Deveria lançar uma exceção IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        } catch (Exception e) {
            fail("Deveria lançar uma exceção IllegalArgumentException");
        }
    }

    void testAtualizarMatriculaValorNulo() {
        Imoveis imoveis = new Imoveis();
        Imovel imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));

        try {
            imoveis.adicionar(imovel);
            imoveis.atualizar(imovel.getId(), (String)null);
            fail("Deveria lançar uma exceção IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("tentativa de inserir valor nulo ou vazio", e.getMessage());
        } catch (Exception e) {
            fail("Deveria lançar uma exceção IllegalArgumentException");
        }
    }

    void testAtualizarEnderecoValorNulo() {
        Imoveis imoveis = new Imoveis();
        Imovel imovel = new Imovel("123ABC", new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.BAHIA, "45880-000"));

        try {
            imoveis.adicionar(imovel);
            imoveis.atualizar(imovel.getId(), (Endereco)null);
            fail("Deveria lançar uma exceção IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Endereço nulo não é permitido", e.getMessage());
        } catch (Exception e) {
            fail("Deveria lançar uma exceção IllegalArgumentException");
        }
    }



}
