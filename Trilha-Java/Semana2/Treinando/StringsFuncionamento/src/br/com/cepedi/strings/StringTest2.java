package br.com.cepedi.strings;

public class StringTest2 {

	public static void main(String[] args) {
		String nome = "Luffy";
		String nome2 = "    Vamos Tricolores  ronaldo";
		System.out.println(nome.charAt(0));
		System.out.println(nome.length());
		System.out.println(nome.replace("f", "t"));
		System.out.println(nome.toLowerCase());
		System.out.println(nome.toUpperCase());
		System.out.println(nome2.substring(0,5));
		System.out.println(nome2.substring(3));
		System.out.println(nome2.trim());
	}
	
}
