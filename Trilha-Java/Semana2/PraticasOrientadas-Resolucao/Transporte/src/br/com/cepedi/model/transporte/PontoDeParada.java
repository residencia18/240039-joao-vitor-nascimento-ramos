package br.com.cepedi.model.transporte;

public class PontoDeParada {
	
	public static int qtdPontosParada = 0;
	
	
	private int id;
	private String nome;
	
	

	public PontoDeParada(String nome) {
		super();
		setNome(nome);
		qtdPontosParada++;
		this.id = qtdPontosParada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome==null) {
			throw new NullPointerException("Foi inserido um nome nulo");
		}
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + "]";
	}
	
	
	
	
	
	

}
