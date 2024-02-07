package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Relogio;

class TesteRelogio {

	@Test
	void test() {
		testeInstanciaRelogioPuro();
		testValoresZeroParaORelogio();
		testConstrutorComParametros();
		testLeituraDoPeriodo();
		testRegistraNovaLeitura();
	}
	
	void testeInstanciaRelogioPuro() {
		Relogio r = null;
		try {
			r = new Relogio();
		}catch(Exception e ) {
			fail("não deve cair aqui");
		}
        assertNotNull(r);

	}
	
    void testValoresZeroParaORelogio() {
        Relogio relogio = new Relogio();
        assertNotNull(relogio);
        assertEquals(BigDecimal.ZERO, relogio.getUltimaLeitura());
        assertEquals(BigDecimal.ZERO, relogio.getLeituraAtual());
        assertEquals(2, relogio.getId());
    }
    
    void testConstrutorComParametros() {
        BigDecimal ultimaLeitura = new BigDecimal("10.0");
        BigDecimal leituraAtual = new BigDecimal("20.0");
        Relogio relogio = new Relogio(1, ultimaLeitura, leituraAtual);
        assertNotNull(relogio);
        assertEquals(ultimaLeitura, relogio.getUltimaLeitura());
        assertEquals(leituraAtual, relogio.getLeituraAtual());
        assertEquals(1, relogio.getId());
    }
    
    void testLeituraDoPeriodo() {
        Relogio relogio = new Relogio();
        relogio.setUltimaLeitura(new BigDecimal("10.0"));
        relogio.setLeituraAtual(new BigDecimal("30.0"));
        assertEquals(new BigDecimal("20.0"), relogio.leituraDoPeriodo());
    }
    
    void testRegistraNovaLeitura() {
        Relogio relogio = new Relogio();
        BigDecimal leitura = new BigDecimal("10.0");
        BigDecimal novaLeitura = new BigDecimal("20.0");
        relogio.registraNovaLeitura(leitura);
        relogio.registraNovaLeitura(novaLeitura);
        assertEquals(leitura, relogio.getUltimaLeitura());
        assertEquals(novaLeitura.add(leitura), relogio.getLeituraAtual());
    }

}
