package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.cepedi.exceptions.cartaoPassagem.TipoDePassagemInvalidoException;
import br.com.cepedi.model.passagem.CartaoPassagem;

class TesteCartaoPassagem {

	@Test
	void test() {
		testInstanciaCartao();
		testRecargaValor();
		testRecarregaValorNegativo();
		testRecarregaValorIdoso();
		testTipoPassagemInvalida();
	}
	
	public void testInstanciaCartao() {
		try {
			CartaoPassagem cartao = new CartaoPassagem(1);
		}catch(Exception e) {
			fail("teste Falhou");
			System.out.println(e.getMessage());
		}
	}
	
	public void testRecargaValor() {
		try {
			CartaoPassagem cartao = new CartaoPassagem(2);
			try {
				cartao.recarregar(new BigDecimal("4"));
			}catch(Exception e) {
				fail("Erro no teste");
			}	
			assertTrue(cartao.getSaldo().compareTo(new BigDecimal("4.0"))==0);
		}catch(Exception e ) {
			e.getStackTrace();
		}
		
	}
	
	public void testRecarregaValorNegativo(){
		try {
			CartaoPassagem cartao = new CartaoPassagem(1);
			try {
				cartao.recarregar(new BigDecimal("-2"));
			}catch(Exception e) {
				assertEquals("Valor de recarga inválido",e.getMessage());
			}
			assertFalse(cartao.getSaldo().compareTo(new BigDecimal("-2")) ==0);

		}catch(Exception e ) {
			e.getStackTrace();
		}
	}
	
	public void testRecarregaValorIdoso() {
		
		try {
			CartaoPassagem cartao = new CartaoPassagem(3);
			try {
				cartao.recarregar(new BigDecimal("20"));
			}catch(Exception e) {
				assertEquals("Não é permitido realizar recarga no cartao de idoso",e.getMessage());
			}
			assertFalse(cartao.getSaldo().compareTo(new BigDecimal("20")) ==0);

		}catch(Exception e ) {
			e.getStackTrace();
		}
		
	}
	
	public void testTipoPassagemInvalida() {
		
		CartaoPassagem cartao = null;
		try {
			cartao = new CartaoPassagem(5);
		}catch(Exception e ) {
			assertEquals("Tipo de passagem inválido",e.getMessage());
		}
		
		assertNull(cartao);
	}
	


}
