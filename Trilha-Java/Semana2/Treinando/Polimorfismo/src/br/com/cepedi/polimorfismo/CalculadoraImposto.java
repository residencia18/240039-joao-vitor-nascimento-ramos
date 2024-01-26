package br.com.cepedi.polimorfismo;

public class CalculadoraImposto {
	
	public void calcularImposto(Produto produto) {
		System.out.println("Calculando imposto de " + produto.getNome());
		System.out.println("Imposto : " + produto.calcularImposto());
		if(produto instanceof Tomate) {
			String dataValidade = ((Tomate) produto).getDataValidade();
			System.out.println("Data de validade :" + dataValidade );
		}
	}
	

}
