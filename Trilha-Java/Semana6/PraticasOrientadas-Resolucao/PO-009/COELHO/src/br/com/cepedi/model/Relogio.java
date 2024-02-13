package br.com.cepedi.model;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.cepedi.verificacoes.geral.VerificacoesGeral;

public class Relogio {
	
	
	//-------ATRIBUTOS
	
	int id_imovel;
	BigDecimal ultimaLeitura;
	BigDecimal leituraAtual;
	
	
	//---------CONSTRUTORES
	
	public Relogio() {
		ultimaLeitura =BigDecimal.ZERO;
		leituraAtual = BigDecimal.ZERO;
	}
	
	public Relogio(int id ,BigDecimal ultimaLeitura,BigDecimal leituraAtual) {
		setUltimaLeitura(ultimaLeitura);
		setLeituraAtual(leituraAtual);
		this.id_imovel = id;
	}
	
	
	//---------GETTERS E SETTERS
		
	public int getId() {
		return id_imovel;
	}



	public void setId(int id) {
		VerificacoesGeral.verificaID(id);	
		this.id_imovel = id;
	}

	public BigDecimal leituraDoPeriodo() {
		return leituraAtual.subtract(ultimaLeitura);
	}

	public BigDecimal getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(BigDecimal ultimaLeitura) {
		VerificacoesGeral.verificaLeituraNegativa(ultimaLeitura);
		this.ultimaLeitura = ultimaLeitura;
	}

	public BigDecimal getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(BigDecimal leituraAtual) {
		VerificacoesGeral.verificaLeituraNegativa(leituraAtual);
		VerificacoesGeral.verificaLeituraAtual(leituraAtual, this.ultimaLeitura);
		this.leituraAtual = leituraAtual;
	}
	
	//------ REGISTRAR LEITURA
	
	public void registraNovaLeitura(BigDecimal novaLeitura) {
	    VerificacoesGeral.verificaLeituraNegativa(novaLeitura);
	    this.ultimaLeitura = this.leituraAtual;
	    this.leituraAtual = novaLeitura;
	}
	
	
	//------------ TO STRING

	@Override
	public String toString() {
		return "Relogio [id_imovel=" + id_imovel + ", ultimaLeitura=" + ultimaLeitura + ", leituraAtual=" + leituraAtual + "]";
	}

	
	
	//-----EQUALS E HASHCODE


	@Override
	public int hashCode() {
		return Objects.hash(id_imovel);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relogio other = (Relogio) obj;
		return id_imovel == other.id_imovel;
	}

	
	
	

}
