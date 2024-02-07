package br.com.cepedi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fatura {
	
	public static int qntIdsGerados=0;
	public static final BigDecimal valorKW = new BigDecimal("10");

	int id;
	private LocalDate data;
	private BigDecimal valor;
	private BigDecimal ultimaLeitura;
	private BigDecimal leituraAtual;
	private boolean quitado = false;
	List<Pagamento> pagamentos;
	
	
	
	public Fatura(LocalDate data , Relogio relogio) {
		
		setData(data);
		setLeituras(relogio);
		setValor(relogio.leituraDoPeriodo());
		pagamentos = new ArrayList<>();
		qntIdsGerados++;
		this.id = qntIdsGerados;
	}


	public LocalDate getData() {
		return data;
	}
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setData(LocalDate data) {
		
		if(data==null) throw new IllegalArgumentException("data não deve ser nula");
				
		this.data = data;
	}





	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}


	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}


	public BigDecimal getUltimaLeitura() {
		return ultimaLeitura;
	}


	public BigDecimal getLeituraAtual() {
		return leituraAtual;
	}


	public void setLeituras(Relogio relogio) {
		
		if(relogio==null) throw new IllegalArgumentException("Relogio não deve ser nulo");

		
		this.ultimaLeitura = relogio.getUltimaLeitura();
		this.leituraAtual = relogio.getLeituraAtual();
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal kwUsados) {
		
		if(kwUsados==null) throw new IllegalArgumentException("Tentativa de inserir valor nulo");

		
		BigDecimal valor = kwUsados.multiply(valorKW);

		if(valor.compareTo(BigDecimal.ZERO) < 0)  throw new IllegalArgumentException("valor não pode ser negativo");
		
		if(valor.compareTo(BigDecimal.ZERO) == 0) this.quitado = true;
		
		this.valor = valor;
	}


	public boolean isQuitado() {
		return quitado;
	}


	public void setQuitado(boolean quitado) {
		this.quitado = quitado;
	}


	@Override
	public String toString() {
		return "Fatura [id=" + id + ", data=" + data + ", valor=" + valor + ", ultimaLeitura=" + ultimaLeitura
				+ ", leituraAtual=" + leituraAtual + ", quitado=" + quitado + "]";
	}
	
	public void registraPagamento(BigDecimal valorPago) {
	    if (this.quitado) {
	        throw new IllegalArgumentException("A fatura está quitada");
	    }
	    
	    Pagamento pagamento = new Pagamento(LocalDate.now(), valorPago);
	    BigDecimal valorAntesDoPagamento = this.valor;
	    this.valor = this.valor.subtract(valorPago);
	    
	    if (this.valor.compareTo(BigDecimal.ZERO) <= 0) {
	        this.quitado = true;
	    }
	    
	    if (this.valor.compareTo(BigDecimal.ZERO) < 0) {
	        BigDecimal valorDoReembolso = valorPago.subtract(valorAntesDoPagamento);
	        pagamento.registraReembolso(LocalDate.now(), valorDoReembolso);
	    }
	    
	    this.pagamentos.add(pagamento);
	    System.out.println("Pagamento Realizado com sucesso!");
	}


	
	
	
}
