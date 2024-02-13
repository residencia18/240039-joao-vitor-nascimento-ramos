package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Cliente;
import br.com.cepedi.model.Endereco;
import br.com.cepedi.model.Estado;
import br.com.cepedi.model.Fatura;
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
		testBuscarFaturaEmAberto();
		testBuscarFatura();
		testBuscarFaturaWithNonexistentId();
		testRealizaLeitura();
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
    
    
    void testBuscarFaturaEmAberto() {
        LocalDate data = LocalDate.now();
        Relogio relogio = new Relogio();
        Cliente cliente;
        Endereco endereco;
        Imovel imovel = null;
        Fatura fatura1 = null;
        Fatura fatura2 = null;

		try {
			cliente = new Cliente("João", "04999695537");
	        endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
	        imovel = new Imovel("123", endereco);
	        imovel.setProprietario(cliente);
	        BigDecimal valor1 = new BigDecimal("10.0");
	        BigDecimal valor2 = new BigDecimal("17.0");
	        imovel.realizaLeitura(data, valor1);
	        imovel.realizaLeitura(data,valor2);

	        fatura1 = imovel.buscarFatura(1);
	        fatura2 = imovel.buscarFatura(2);
		} catch (Exception e ) {
			fail("nao deve cair aqui");
		}


        assertEquals(fatura2, imovel.buscarFaturaEmAberto(fatura2.getId()));
    }



    void testBuscarFatura() {
        LocalDate data = LocalDate.now();
        Relogio relogio = new Relogio();
        Cliente cliente;
        Endereco endereco;
        Imovel imovel = null;
        Fatura fatura = null;

		try {
			cliente = new Cliente("João", "04999695537");
	        endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
	        imovel = new Imovel("123", endereco);
	        imovel.setProprietario(cliente);
	        BigDecimal valor1 = new BigDecimal("10.0");
	        BigDecimal valor2 = new BigDecimal("17.0");
	        imovel.realizaLeitura(data, valor1);
	        imovel.realizaLeitura(data,valor2);
	        fatura = imovel.getFaturas().get(0);
		} catch (Exception e ) {
			fail("nao deve cair aqui");
		}



        assertEquals(fatura, imovel.buscarFatura(fatura.getId()));
    }

    void testBuscarFaturaWithNonexistentId() {
        LocalDate data = LocalDate.now();
        Relogio relogio = new Relogio();
        Cliente cliente;
        Endereco endereco;
        Imovel imovel = null;
        Fatura fatura = null;

		try {
			cliente = new Cliente("João", "04999695537");
	        endereco = new Endereco("Rua Teste", 123, "Bairro Teste", "Cidade Teste", Estado.ESPIRITO_SANTO , "45880-000");
	        imovel = new Imovel("123", endereco);
	        imovel.setProprietario(cliente);
	        BigDecimal valor1 = new BigDecimal("10.0");
	        BigDecimal valor2 = new BigDecimal("17.0");
	        imovel.realizaLeitura(data, valor1);
	        imovel.realizaLeitura(data,valor2);
	        fatura = imovel.buscarFatura(9999);
		} catch (Exception e ) {
			assertEquals("Fatura não encontrada com o ID: 9999",e.getMessage());
		}
	
    }
    
    
    void testRealizaLeitura() {
        Imovel imovel;
        Endereco endereco;
        Cliente cliente;
        Fatura fatura;

        try {
            cliente = new Cliente("João", "04999695537");
            endereco = new Endereco("Rua A", 123, "Bairro X", "Cidade Y", Estado.ACRE, "12345-678");
            imovel = new Imovel("123", endereco);
            imovel.setProprietario(cliente);

            BigDecimal valor1 = new BigDecimal("100.0");
            BigDecimal valor2 = new BigDecimal("150.0");

            LocalDate data1 = LocalDate.of(2024, 2, 1);
            LocalDate data2 = LocalDate.of(2024, 2, 15);

            imovel.realizaLeitura(data1, valor1);
            imovel.realizaLeitura(data2, valor2);

            // Verifica se a fatura foi criada corretamente
            assertEquals(2, imovel.getFaturas().size());

            // Verifica se as faturas têm os valores corretos
            fatura = imovel.getFaturas().get(0);
            assertEquals(data1, fatura.getData());
            assertEquals(valor1.multiply(new BigDecimal("10")), fatura.getValor());

            fatura = imovel.getFaturas().get(1);
            assertEquals(data2, fatura.getData());
            assertEquals((valor2.subtract(valor1)).multiply(new BigDecimal("10")), fatura.getValor());
        } catch (Exception e) {
            fail("Não deveria cair aqui: " + e.getMessage());
        }
    }
    

}
