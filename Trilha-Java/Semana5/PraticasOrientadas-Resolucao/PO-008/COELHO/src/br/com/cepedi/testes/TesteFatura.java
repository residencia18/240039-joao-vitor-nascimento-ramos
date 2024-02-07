package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.Fatura;
import br.com.cepedi.model.Relogio;

class TesteFatura {

    @Test
    void test() {
        testeInstanciaFatura();
        testeValoresIniciaisFatura();
        testeConstrutorComParametros();
        testeSetQuitado();
        testeSetValorNegativo();
		testRegistraPagamento();
		testRegistraPagamentoWithAlreadyPaidInvoice();
		testRegistraPagamentoWithNegativeValue();
		testRegistraPagamentoWithFullPayment();
		testRegistraPagamentoWithOverPayment();
		testRegistraPagamentoWithNegativeBalance();
    }
    
    void testeInstanciaFatura() {
        Fatura fatura = null;
        try {
            LocalDate data = LocalDate.now();
            Relogio relogio = new Relogio();
            fatura = new Fatura(data, relogio);
        } catch (Exception e) {
            fail("Não deve cair aqui");
        }
        assertNotNull(fatura);
    }
    
    void testeValoresIniciaisFatura() {
        LocalDate data = LocalDate.now();
        Relogio relogio = new Relogio();
        Fatura fatura = new Fatura(data, relogio);
        assertNotNull(fatura);
        assertEquals(data, fatura.getData());
        assertEquals(relogio.getUltimaLeitura(), fatura.getUltimaLeitura());
        assertEquals(relogio.getLeituraAtual(), fatura.getLeituraAtual());
        assertTrue(fatura.getValor().compareTo(BigDecimal.ZERO) >= 0);
        assertTrue(fatura.isQuitado());
    }
    
    void testeConstrutorComParametros() {
        LocalDate data = LocalDate.now();
        Relogio relogio = new Relogio();
        BigDecimal valor = new BigDecimal("500");
        relogio.registraNovaLeitura(valor);
        Fatura fatura = new Fatura(data, relogio);
        assertNotNull(fatura);
        assertEquals(data, fatura.getData());
        assertEquals(relogio.getUltimaLeitura(), fatura.getUltimaLeitura());
        assertEquals(relogio.getLeituraAtual(), fatura.getLeituraAtual());
        assertEquals(valor.multiply(Fatura.valorKW), fatura.getValor());
        assertTrue(fatura.getValor().compareTo(BigDecimal.ZERO) >= 0);
        assertFalse(fatura.isQuitado());
    }
    
    void testeSetQuitado() {
        LocalDate data = LocalDate.now();
        Relogio relogio = new Relogio();
        Fatura fatura = new Fatura(data, relogio);
        fatura.setQuitado(true);
        assertTrue(fatura.isQuitado());
    }
    
    void testeSetValorNegativo() {
        LocalDate data = LocalDate.now();
        Relogio relogio = new Relogio();
        Fatura fatura = new Fatura(data, relogio);
        try {
            fatura.setValor(new BigDecimal("-50.00"));
            fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("valor não pode ser negativo", e.getMessage());
        }
    }
    
    void testRegistraPagamento() {
        LocalDate data = LocalDate.now();
        BigDecimal valorPago = new BigDecimal("100.0");
        BigDecimal ultimaLeitura = new BigDecimal("10.0");
        BigDecimal leituraAtual = new BigDecimal("20.0");
        Relogio relogio = new Relogio(1, ultimaLeitura, leituraAtual);
        Fatura fatura = new Fatura(data, relogio);
        fatura.registraPagamento(valorPago);

        assertTrue(fatura.isQuitado());
        assertEquals(1, fatura.getPagamentos().size());
    }

    
    void testRegistraPagamentoWithAlreadyPaidInvoice() {
        LocalDate data = LocalDate.now();
        BigDecimal ultimaLeitura = new BigDecimal("10.0");
        BigDecimal leituraAtual = new BigDecimal("20.0");
        Relogio relogio = new Relogio(1, ultimaLeitura, leituraAtual);
        Fatura fatura = new Fatura(data, relogio);
        fatura.registraPagamento(new BigDecimal("500"));

        assertThrows(IllegalArgumentException.class, () -> fatura.registraPagamento(new BigDecimal("50.0")));
    }

    
    void testRegistraPagamentoWithNegativeValue() {
        LocalDate data = LocalDate.now();
        BigDecimal ultimaLeitura = new BigDecimal("10.0");
        BigDecimal leituraAtual = new BigDecimal("20.0");
        Relogio relogio = new Relogio(1, ultimaLeitura, leituraAtual);
        Fatura fatura = new Fatura(data, relogio);
        assertThrows(IllegalArgumentException.class, () -> fatura.registraPagamento(new BigDecimal("-50.0")));
    }

    
    void testRegistraPagamentoWithFullPayment() {
        LocalDate data = LocalDate.now();
        BigDecimal ultimaLeitura = new BigDecimal("10.0");
        BigDecimal leituraAtual = new BigDecimal("20.0");
        Relogio relogio = new Relogio(1, ultimaLeitura, leituraAtual);
        Fatura fatura = new Fatura(data, relogio);
        fatura.registraPagamento(new BigDecimal("100"));

        assertTrue(fatura.isQuitado());
        assertEquals(1, fatura.getPagamentos().size());
    }

    
    void testRegistraPagamentoWithOverPayment() {
        LocalDate data = LocalDate.now();
        BigDecimal ultimaLeitura = new BigDecimal("10.0");
        BigDecimal leituraAtual = new BigDecimal("20.0");
        Relogio relogio = new Relogio(1, ultimaLeitura, leituraAtual);
        Fatura fatura = new Fatura(data, relogio);
        fatura.registraPagamento(new BigDecimal("150.0"));

        assertTrue(fatura.isQuitado());
        assertEquals(1, fatura.getPagamentos().size());
    }

    
    void testRegistraPagamentoWithNegativeBalance() {
        LocalDate data = LocalDate.now();
        BigDecimal ultimaLeitura = new BigDecimal("10.0");
        BigDecimal leituraAtual = new BigDecimal("20.0");
        Relogio relogio = new Relogio(1, ultimaLeitura, leituraAtual);
        Fatura fatura = new Fatura(data, relogio);
        fatura.registraPagamento(new BigDecimal("50.0"));
        fatura.registraPagamento(new BigDecimal("40.0"));

        assertFalse(fatura.isQuitado());
        assertEquals(2, fatura.getPagamentos().size());

        BigDecimal totalPayments = fatura.getPagamentos().stream()
                                        .map(p -> p.getValor())
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        assertTrue(new BigDecimal("90").compareTo(totalPayments) == 0);


        assertEquals(new BigDecimal("10.0"), fatura.getValor());
    }



}
