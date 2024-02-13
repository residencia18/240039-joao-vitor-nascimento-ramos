package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Pagamento;
import br.com.cepedi.model.Reembolso;

class TestePagamento {

	
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
		testRegistraReembolso();
		testRegistraReembolsoWithZeroValue();
		testRegistraReembolsoWithNegativeValue();
	}
	
    void testConstrutor() {
        LocalDate data = LocalDate.now();
        BigDecimal valor = new BigDecimal("100.0");
        Pagamento pagamento = new Pagamento(data, valor);
        
        assertNotNull(pagamento);
        assertEquals(data, pagamento.getData());
        assertEquals(valor, pagamento.getValor());
        System.out.println(pagamento.getId());
        assertEquals(1, pagamento.getId());
    }

    void testSetId() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        pagamento.setId(1);
        assertEquals(1, pagamento.getId());
    }

    void testSetIdWithNegativeValue() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> pagamento.setId(-1));
    }

    void testSetData() {
        LocalDate data = LocalDate.now();
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        pagamento.setData(data);
        assertEquals(data, pagamento.getData());
    }

    void testSetDataWithNullValue() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> pagamento.setData(null));
    }

    void testSetValor() {
        BigDecimal valor = new BigDecimal("100.0");
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        pagamento.setValor(valor);
        assertEquals(valor, pagamento.getValor());
    }

    void testSetValorWithZeroValue() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> pagamento.setValor(BigDecimal.ZERO));
    }

    void testSetValorWithNegativeValue() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        assertThrows(IllegalArgumentException.class, () -> pagamento.setValor(new BigDecimal("-100.0")));
    }

    void testRegistraReembolso() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        LocalDate dataReembolso = LocalDate.now();
        BigDecimal valorReembolso = new BigDecimal("50.0");
        pagamento.registraReembolso(dataReembolso, valorReembolso);

        Reembolso reembolso = pagamento.getReembolso();
        assertNotNull(reembolso);
        assertEquals(dataReembolso, reembolso.getData());
        assertEquals(valorReembolso, reembolso.getValor());
    }

    void testRegistraReembolsoWithZeroValue() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        LocalDate dataReembolso = LocalDate.now();
        BigDecimal valorReembolso = BigDecimal.ZERO;
        assertThrows(IllegalArgumentException.class, () -> pagamento.registraReembolso(dataReembolso, valorReembolso));
    }

    
    void testRegistraReembolsoWithNegativeValue() {
        Pagamento pagamento = new Pagamento(LocalDate.now(), new BigDecimal("100.0"));
        LocalDate dataReembolso = LocalDate.now();
        BigDecimal valorReembolso = new BigDecimal("-50.0");
        assertThrows(IllegalArgumentException.class, () -> pagamento.registraReembolso(dataReembolso, valorReembolso));
    }
}
