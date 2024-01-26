package br.com.cepedi.strings;

public class StringTest {
	
	public static void main(String[] args) {
		String nome = "Joao";
		String nome2 = "Joao";
		
		System.out.println(nome==nome2);
		
		nome = nome.concat(nome2);
		System.out.println(nome==nome2);
		String nome3 = new String("Joao");
		System.out.println(nome2==nome3);
		System.out.println(nome2==nome3.intern());
	
	}

}
