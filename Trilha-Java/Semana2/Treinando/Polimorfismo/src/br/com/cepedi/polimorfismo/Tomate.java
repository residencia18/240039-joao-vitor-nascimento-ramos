package br.com.cepedi.polimorfismo;

public class Tomate extends Produto{
	
	public static final double IMPOSTO_POR_CENTO = 0.15;
	
	private String dataValidade;

	public Tomate(String nome, double valor) {
		super(nome, valor);
		// TODO Auto-generated constructor stub
	}
	
	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public double calcularImposto() {
		System.out.println("Calculando imposto do tomate");
		return this.getValor()*IMPOSTO_POR_CENTO;
	}

}
