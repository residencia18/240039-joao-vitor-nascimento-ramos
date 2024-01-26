package br.com.cepedi.strings;

public class StringBuilderTest {
	
	public static void main(String[] args) {
		String nome = "Willian Suane";
		StringBuilder sb = new StringBuilder(nome);
		sb.append(" Dev Dojo");
		sb.reverse();
		sb.delete(5, 8);
		System.out.println(sb);
	}

}
