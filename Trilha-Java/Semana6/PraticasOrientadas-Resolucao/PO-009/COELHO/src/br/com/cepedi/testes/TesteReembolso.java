package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Reembolso;

class TesteReembolso {

	
	@Test
	void test(){
		testConstrutor();
		testSetId();
		testSetIdWithNegativeValue();
		testSetData();
		testSetDataWithNullValue();
		testSetValor();
		testSetValorWithZeroValue();
		testSetValorWithNegativeValue();
	}
	
    @Test
    void testConstrutor() {
        LocalDate data = LocalDate.now();
        BigDecimal valor = new BigDecimal("100.0");
        Reembolso reembolso = new Reembolso(data, valor);
        
        assertNotNull(reembolso);
        assertEquals(data, reembolso.getData());
        assertEquals(valor, reembolso.getValor());
    }

    
    void testSetId() {
        Reembolso reembolso = new Reembolso(LocalDate.now(), new BigDecimal("100.0"));
        reembolso.setId(1);
        assertEquals(1, reembolso.getId());
    }

    void testSetIdWithNegativeValue() {
        Reembolso reembolso = new Reembolso(LocalDate.now(), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> reembolso.setId(-1));
    }

    void testSetData() {
        LocalDate data = LocalDate.now();
        Reembolso reembolso = new Reembolso(LocalDate.of(2022, 05, 14), new BigDecimal("100.0"));
        reembolso.setData(data);
        assertEquals(data, reembolso.getData());
    }

    
    void testSetDataWithNullValue() {
        Reembolso reembolso = new Reembolso(LocalDate.of(2022, 05, 14), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> reembolso.setData(null));
    }

    @Test
    void testSetValor() {
        BigDecimal valor = new BigDecimal("100.0");
        Reembolso reembolso = new Reembolso(LocalDate.of(2022, 05, 14), new BigDecimal("100.0"));
        reembolso.setValor(valor);
        assertEquals(valor, reembolso.getValor());
    }

    void testSetValorWithZeroValue() {
        Reembolso reembolso = new Reembolso(LocalDate.of(2022, 05, 14), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> reembolso.setValor(BigDecimal.ZERO));
    }

    
    void testSetValorWithNegativeValue() {
        Reembolso reembolso = new Reembolso(LocalDate.of(2022, 05, 14), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> reembolso.setValor(new BigDecimal("-100.0")));
    }
}
