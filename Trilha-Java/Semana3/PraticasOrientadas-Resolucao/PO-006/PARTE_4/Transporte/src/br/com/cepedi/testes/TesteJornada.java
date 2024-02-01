package br.com.cepedi.testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.transporte.Jornada;
import br.com.cepedi.model.veiculo.Veiculo;

class TesteJornada {

	@Test
	void test() {
		TesteInstanciaJornadaCompleto();
		TesteInstanciaJornadaSemCobrador();
		TesteInstanciaJornadaVeiculoNulo();
		TesteInstanciaJornadaMotoristaNulo();
		TesteInstanciaJornadaNomeNulo();
		TesteInstanciaJornadaHorarioNulo();
		TesteInstanciaCobradorNulo();
		TesteInstanciaDataAnterior();
	}
	
	public void TesteInstanciaJornadaCompleto() {
		Jornada jornada = null;
		try {
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Cobrador cobrador = new Cobrador("Paulo","04999695537","1320");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 5, 2, 17, 30);
			jornada = new Jornada(veiculo,cobrador,motorista,nome,ldt);
				
			
		}catch(Exception e ) {
			fail("nao deve cair aqui");
		}
		
		assertNotNull(jornada);
	}
	
	public void TesteInstanciaJornadaSemCobrador() {
		Jornada jornada = null;
		try {
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 05, 02, 17, 30);
			jornada = new Jornada(veiculo,motorista,nome,ldt);
				
			
		}catch(Exception e ) {
			fail("Nao deve cair aqui");
		}
		
		assertNotNull(jornada);
	}
	
	public void TesteInstanciaJornadaVeiculoNulo() {
		Jornada jornada = null;

		try {
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 05, 02, 17, 30);

			jornada = new Jornada(null,motorista,nome,ldt);
				
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir valor nulo",e.getMessage());
		}
		
		assertNull(jornada);
	
	}
	
	public void TesteInstanciaJornadaMotoristaNulo() {
		Jornada jornada = null;

		try {
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 05, 02, 17, 30);

			jornada = new Jornada(veiculo,null,nome,ldt);
				
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir valor nulo",e.getMessage());
		}
		
		assertNull(jornada);
	
	}
	
	public void TesteInstanciaJornadaNomeNulo() {
		Jornada jornada = null;

		try {
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 05, 02, 17, 30);

			jornada = new Jornada(veiculo,motorista,null,ldt);
				
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir valor nulo",e.getMessage());
		}
		
		assertNull(jornada);
	
	}
	
	public void TesteInstanciaJornadaHorarioNulo() {
		Jornada jornada = null;

		try {
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 05, 02, 17, 30);

			jornada = new Jornada(veiculo,motorista,nome,null);
				
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir valor nulo",e.getMessage());
		}
		
		assertNull(jornada);
	
	}
	
	public void TesteInstanciaCobradorNulo() {
		Jornada jornada = null;

		try {
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2024, 05, 02, 17, 30);

			jornada = new Jornada(veiculo,null,motorista,nome,ldt);
				
		}catch(Exception e ) {
			assertEquals("Tentativa de inserir valor nulo",e.getMessage());
		}
		
		assertNull(jornada);
	}
	
	public void TesteInstanciaDataAnterior() {
		Jornada jornada = null;

		try {
			Veiculo veiculo = new Veiculo("MOBI","FFF-7542","FIAT");
			Motorista motorista = new Motorista("Paulo","04999695537","1325","12345678901");
			String nome = "Viagem";
			LocalDateTime ldt = LocalDateTime.of(2022, 05, 02, 17, 30);

			jornada = new Jornada(veiculo,motorista,nome,ldt);
				
		}catch(Exception e ) {
			assertEquals("Horario da jornada não pode ser anterior ao atual",e.getMessage());
		}
		
		assertNull(jornada);
	}
	
	
	
	
}
