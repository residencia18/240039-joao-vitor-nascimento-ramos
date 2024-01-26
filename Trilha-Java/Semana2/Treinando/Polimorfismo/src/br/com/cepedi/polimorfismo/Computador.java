package br.com.cepedi.polimorfismo;

public class Computador extends Produto{
	
	public static final double IMPOSTO_POR_CENTO = 0.37;


	public Computador(String nome, double valor) {
		super(nome, valor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularImposto() {
		System.out.println("Calculando imposto do computador");
		return this.getValor()*IMPOSTO_POR_CENTO;
	}
	
	

}
