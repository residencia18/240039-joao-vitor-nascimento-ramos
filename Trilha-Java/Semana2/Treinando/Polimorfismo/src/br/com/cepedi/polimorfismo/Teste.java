package br.com.cepedi.polimorfismo;

public class Teste {
	

	
	public static void main(String[] args) {
		Produto produto1 = new Computador("Acer nitro 5", 4300);
		Produto produto2 = new Tomate("tomate brasileiro", 0.8);
		
		Tomate tomate = new Tomate("tomate validade", 1);
		tomate.setDataValidade("12/08/2024");
		
		System.out.println(produto1.calcularImposto());
		System.out.println(produto2.calcularImposto());
		System.out.println(tomate.calcularImposto());
		CalculadoraImposto c = new CalculadoraImposto();
		c.calcularImposto(tomate);
		c.calcularImposto(produto1);

	}
	

}
