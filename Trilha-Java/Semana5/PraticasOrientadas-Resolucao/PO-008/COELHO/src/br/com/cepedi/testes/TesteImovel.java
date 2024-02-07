package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Endereco;
import br.com.cepedi.model.Estado;
import br.com.cepedi.model.Imovel;
import br.com.cepedi.model.Relogio;

class TesteImovel {
	
	@Test
	void test(){
		testInstanciaImovel();
		testSetMatricula();
		testSetEndereco();
		testSetRelogio();
		testEnderecoNulo();
		testRelogioNulo();
	}

    void testInstanciaImovel() {
        Imovel imovel;
        Endereco endereco;
        try {
            endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
            imovel = new Imovel("12415", endereco);
            
            assertNotNull(imovel);
            
            assertEquals("12415", imovel.getMatricula());
            assertEquals(endereco, imovel.getEndereco());
            assertNotNull(imovel.getRelogio());
            
        } catch (Exception e) {
            fail("Não deveria cair aqui");
        }
    }

    void testSetMatricula() {
        Imovel imovel;
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
        imovel = new Imovel("12415", endereco);

        imovel.setMatricula("54321");
        assertEquals("54321", imovel.getMatricula());
    }

    void testSetEndereco() {
        Imovel imovel;
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
        imovel = new Imovel("12415", endereco);

        Endereco novoEndereco = new Endereco("Nova Rua", 456, "Novo Bairro", "Nova Cidade", Estado.BAHIA, "12345-678");
        imovel.setEndereco(novoEndereco);
        assertEquals(novoEndereco, imovel.getEndereco());
    }

    void testSetRelogio() {
        Imovel imovel;
        Endereco endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
        imovel = new Imovel("12415", endereco);

        Relogio novoRelogio = new Relogio();
        imovel.setRelogio(novoRelogio);
        assertEquals(novoRelogio, imovel.getRelogio());
    }
    
    
    void testEnderecoNulo() {
        Imovel imovel = null;
        Endereco endereco;
        try {
            endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
            imovel = new Imovel("12415", null);
            
            
        } catch (Exception e) {
            assertEquals("Endereço nulo não é permitido",e.getMessage()) ;
        }
        
        
        assertNull(imovel);

    }
    
    
    void testRelogioNulo() {
        Imovel imovel = null;
        Endereco endereco;
        try {
            endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
            imovel = new Imovel("12415", endereco);
            imovel.setRelogio(null);
            
        } catch (Exception e) {
            assertEquals("Relogio nulo não é permitido",e.getMessage()) ;
        }
        
        
    }
    
    

}
