package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.cepedi.exceptions.cartaoPassagem.TentativaRecargaIdosoException;
import br.com.cepedi.exceptions.cartaoPassagem.TipoDePassagemInvalidoException;
import br.com.cepedi.exceptions.cartaoPassagem.ValorRecargaInvalidoException;
import br.com.cepedi.model.passagem.CartaoPassagem;
import br.com.cepedi.model.pessoa.Passageiro;

class TestePessoaANDPassageiro {

	@Test
	void test() {
		testeInstanciaPassageiro();
		testeNomeInvalido();
		testeCPFInvalido();
		passarCartaoNulo();
	}
	
	public void testeInstanciaPassageiro() {
		CartaoPassagem cartao = null;
		try {
			cartao = new CartaoPassagem(2);
			cartao.recarregar(new BigDecimal("15"));
			Passageiro passageiro = new Passageiro("Joao","049.996.955-37",cartao);
			System.out.println(passageiro);
		} catch (TipoDePassagemInvalidoException e) {
			fail("Não era pra gerar exception");
		}catch (ValorRecargaInvalidoException | TentativaRecargaIdosoException e) {
			fail("Não era pra gerar exception");
		}catch(Exception e ) {
			e.getStackTrace();
		}
		

	}
	
	public void testeNomeInvalido() {
		CartaoPassagem cartao = null;
		Passageiro passageiro = null;
		try {
			cartao = new CartaoPassagem(2);
		} catch (TipoDePassagemInvalidoException e) {
			fail("Não era pra gerar exception");
		}
		try {
			cartao.recarregar(new BigDecimal("15"));
		} catch (ValorRecargaInvalidoException | TentativaRecargaIdosoException e) {
			fail("Não era pra gerar exception");
		}
		try {
			passageiro = new Passageiro("João15","049.996.955-37",cartao);			
		}catch(Exception e) {
			assertEquals("Nome inválido. deve possuir apenas letras e espaços",e.getMessage());
		}
		assertNull(passageiro);
	}
	
	public void testeCPFInvalido() {
		CartaoPassagem cartao = null;
		Passageiro passageiro = null;
		try {
			cartao = new CartaoPassagem(2);
		} catch (TipoDePassagemInvalidoException e) {
			fail("Não era pra gerar exception");
		}
		try {
			cartao.recarregar(new BigDecimal("15"));
		} catch (ValorRecargaInvalidoException | TentativaRecargaIdosoException e) {
			fail("Não era pra gerar exception");
		}
		try {
			passageiro = new Passageiro("Joao","049.996.111-37",cartao);			
		}catch(Exception e) {
			assertEquals("CPF inválido",e.getMessage());
		}
		assertNull(passageiro);
	}
	
	public void passarCartaoNulo() {
		Passageiro passageiro = null;
		try {
			passageiro = new Passageiro("Joao","049.996.955-37",null);			
		}catch(Exception e) {
			assertEquals("Cartão inválido",e.getMessage());
		}
		assertNull(passageiro);
	}
	

}
