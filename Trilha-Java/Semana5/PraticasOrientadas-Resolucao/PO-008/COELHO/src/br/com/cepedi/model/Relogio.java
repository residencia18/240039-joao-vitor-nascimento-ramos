package br.com.cepedi.model;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.cepedi.verificacoes.geral.VerificacoesGeral;

public class Relogio {
	
	public static int qntIdsGerados=0;
	int id;
	BigDecimal ultimaLeitura;
	BigDecimal leituraAtual;
	
	
	public Relogio() {
		ultimaLeitura =BigDecimal.ZERO;
		leituraAtual = BigDecimal.ZERO;
		qntIdsGerados++;
		this.id = qntIdsGerados;
	}
	
	public Relogio(int id ,BigDecimal ultimaLeitura,BigDecimal leituraAtual) {
		setUltimaLeitura(ultimaLeitura);
		setLeituraAtual(leituraAtual);
		this.id = id;
	}
	
	
	
		
	public int getId() {
		return id;
	}



	public void setId(int id) {
		VerificacoesGeral.verificaID(id);	
		this.id = id;
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
	
	public void registraNovaLeitura(BigDecimal novaLeitura) {
	    VerificacoesGeral.verificaLeituraNegativa(novaLeitura);
	    this.ultimaLeitura = this.leituraAtual;
	    this.leituraAtual = novaLeitura;
	}

	@Override
	public String toString() {
		return "Relogio [id=" + id + ", ultimaLeitura=" + ultimaLeitura + ", leituraAtual=" + leituraAtual + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}

	
	
	

}
