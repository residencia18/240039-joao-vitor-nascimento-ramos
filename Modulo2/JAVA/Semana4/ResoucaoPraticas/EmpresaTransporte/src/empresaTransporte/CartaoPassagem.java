package empresaTransporte;

public class CartaoPassagem {
	
	private static int numeroDeCartoes;
	
	private int id;
	private int tipo;
	private String numero;
	private Boolean ativo = true;
	private int quantidadeDeCredito; // armazena quantidade de passagens que o pasageiro tem em disposição
	
	public void atualizaQuantidadeDeCredito(int creditoNovo) {
		quantidadeDeCredito += creditoNovo;
	}

	public CartaoPassagem(int tipo, String numero, Boolean ativo, int quantidadeDeCredito) {
		super();
		numeroDeCartoes++;
		id = numeroDeCartoes;
		this.tipo = tipo;
		this.numero = numero;
		this.ativo = ativo;
		this.quantidadeDeCredito = quantidadeDeCredito;
	}
	
	

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public int getQuantidadeDeCredito() {
		return quantidadeDeCredito;
	}

	public void setQuantidadeDeCredito(int quantidadeDeCredito) {
		this.quantidadeDeCredito = quantidadeDeCredito;
	}

	public int getId() {
		return id;
	}
	
	
	
	

}
