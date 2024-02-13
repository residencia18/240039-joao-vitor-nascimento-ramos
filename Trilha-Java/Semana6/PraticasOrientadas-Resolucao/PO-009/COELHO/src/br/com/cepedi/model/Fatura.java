package br.com.cepedi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.cepedi.dao.PagamentoDAO;
import br.com.cepedi.dao.ReembolsoDAO;

public class Fatura {
	
	//----ATRIUTOS
	
	public static final BigDecimal valorKW = new BigDecimal("10");

	int id;
	private LocalDate data;
	private BigDecimal valor;
	private BigDecimal ultimaLeitura;
	private BigDecimal leituraAtual;
	private boolean quitado = false;
	List<Pagamento> pagamentos;
	
	
	//----CONSTRUTORES
	
	public Fatura(LocalDate data , Relogio relogio) {
		
		setData(data);
		setLeituras(relogio);
		setValor(relogio.leituraDoPeriodo());
		pagamentos = new ArrayList<>();
	}

	
	//-----GETTERS E SETTERS


	public Fatura(int id, LocalDate data, BigDecimal valor, BigDecimal ultimaLeitura, BigDecimal leituraAtual,
			boolean quitado) {
		super();
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.ultimaLeitura = ultimaLeitura;
		this.leituraAtual = leituraAtual;
		this.quitado = quitado;
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

	
	//-----TO STRING

	@Override
	public String toString() {
		return "Fatura [id=" + id + ", data=" + data + ", valor=" + valor + ", ultimaLeitura=" + ultimaLeitura
				+ ", leituraAtual=" + leituraAtual + ", quitado=" + quitado + "]";
	}
	
	
	//----- METODO PELO REGISTRO DO PAGAMENTO
	
	public void registraPagamento(BigDecimal valorPago) {
	    
		if (this.quitado) {
	        throw new IllegalArgumentException("A fatura está quitada");
	    }
	    
	    if(valorPago.compareTo(BigDecimal.ZERO) < 0 ) {
	    	throw new IllegalArgumentException("Valor inválido para pagamento");
	    }
	    
	    Pagamento pagamento = new Pagamento(LocalDate.now(), valorPago);
	    BigDecimal valorAntesDoPagamento = this.valor;
	    this.valor = this.valor.subtract(valorPago);
	    
	    if (this.valor.compareTo(BigDecimal.ZERO) <= 0) {
	        this.quitado = true;
	    }
	    
	    int idPagamento = PagamentoDAO.proximoId();
	    PagamentoDAO.adicionarPagamento(pagamento, this.id);
	    if (this.valor.compareTo(BigDecimal.ZERO) < 0) {
	        BigDecimal valorDoReembolso = valorPago.subtract(valorAntesDoPagamento);
	        pagamento.registraReembolso(LocalDate.now(), valorDoReembolso,idPagamento);
	    }
	    
	    System.out.println("Pagamento Realizado com sucesso!");
	}


	
	
	
}
