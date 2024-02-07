package br.com.cepedi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.cepedi.verificacoes.geral.VerificacoesGeral;

public class Pagamento {
	
	
	public static int qntIdsGerados=0;

	
	int id;
	private LocalDate data;
	private BigDecimal valor;
	private Reembolso reembolso;
	
	
	
	public Reembolso getReembolso() {
		return reembolso;
	}



	public void setReembolso(Reembolso reembolso) {
		this.reembolso = reembolso;
	}



	public Pagamento(LocalDate data, BigDecimal valor) {
		super();
		this.data = data;
		this.valor = valor;
		qntIdsGerados++;
		this.id = qntIdsGerados;
		
	}


	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		
		VerificacoesGeral.verificaID(id);	

		this.id = id;
	}



	public LocalDate getData() {
		return data;
	}



	public void setData(LocalDate data) {
		
		if(data==null) throw new IllegalArgumentException("data não deve ser nula");

		
		this.data = data;
	}



	public BigDecimal getValor() {
		return valor;
	}



	public void setValor(BigDecimal valor) {
		
		if(valor.compareTo(BigDecimal.ZERO) <= 0)  throw new IllegalArgumentException("valor deve ser maior que zero");

		
		this.valor = valor;
	}




	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", data=" + data + ", valor=" + valor + ", reembolso=" + reembolso + "]";
	}



	public void registraReembolso(LocalDate dataReembolso, BigDecimal valorReembolso) {
	    
		if (valorReembolso.compareTo(BigDecimal.ZERO) <= 0)  throw new IllegalArgumentException("O valor do reembolso deve ser maior que zero");

	    Reembolso reembolso = new Reembolso(dataReembolso, valorReembolso);
	    this.setReembolso(reembolso);
	}

	
	

}
