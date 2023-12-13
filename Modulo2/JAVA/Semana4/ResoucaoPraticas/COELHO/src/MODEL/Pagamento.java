package MODEL;

import java.util.Date;

public class Pagamento {
	private Date data;
	private double valor;
	private Fatura fatura;
	private Reembolso reembolso;

	public Pagamento(double valor, Fatura fatura) {
		this.data = new Date();
		this.valor = valor;
		this.fatura = fatura;
		this.gerarPagamento(this.valor, this.fatura);
	}

	public void gerarReembolso() {
		if (this.fatura != null) {
			if (this.valor > this.fatura.getValor()) {
				this.reembolso = new Reembolso();
				this.reembolso.setValor(this.valor - this.fatura.getValor());
				this.reembolso.setData(this.data);
				this.reembolso.setPagamento(this);
			} else {
				this.reembolso = null;
			}
		} else {
			this.reembolso = null;
		}
	}

	public void gerarPagamento(double valor, Fatura fatura) {
		if (this.fatura != null) {
			if (this.valor == this.fatura.getValor()) {
				this.fatura.setQuitado(true);				
			} else if (this.valor > this.fatura.getValor()) {
				this.fatura.setQuitado(true);
				gerarReembolso();
			} else {
				this.fatura.setQuitado(false);
				this.fatura.setValor(this.fatura.getValor() - this.valor);
			}
		}
	}

	public Pagamento() {
		this.data = new Date();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

}
