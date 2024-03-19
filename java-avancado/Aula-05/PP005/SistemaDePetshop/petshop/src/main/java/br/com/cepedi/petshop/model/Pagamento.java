package br.com.cepedi.petshop.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="PAGAMENTO")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="ID_TIPO_PAGAMENTO" , nullable = false)
	private TipoPagamento tipoPagamento;
	
	@OneToOne
	@JoinColumn(name="ID_VENDA" , nullable = false)
	private Venda venda;

	public Pagamento(TipoPagamento tipoPagamento, Venda venda) {
		super();
		this.tipoPagamento = tipoPagamento;
		this.venda = venda;
	}

	public Pagamento() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	
}
