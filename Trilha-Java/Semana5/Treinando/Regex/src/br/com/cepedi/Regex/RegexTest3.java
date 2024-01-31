package br.com.cepedi.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest3 {

	public static void main(String[] args) {
		String regex = "\\d"; // tudo que for digito
		String regex2 = "\\D"; // Dutod que não for digito
		String regex3 = "\\s"; //Espaços em branco \t \n \f \r
		String regex4 = "\\S"; // todos os caracteres que nao forem em branco
		String regex5 = "\\w"; //a-zA-z,digitos
		String regex6 = "\\W"; //tudo que nao for incuso no \w
		String texto = "abaaba";
		String texto2 = "abababa";
		String textoComDigitos = "das4 5d@ád5 s1d";
		Pattern pattern = Pattern.compile(regex6);
		Matcher matcher = pattern.matcher(textoComDigitos);
		System.out.println("texto:   " + textoComDigitos);
		System.out.println("indice:  0123456789");
		System.out.println("regex: " +regex);
		System.out.println("Posicoes encontradas : " );
		while(matcher.find()) {
			System.out.println(matcher.start()+" "+matcher.group()+ " ");
		}
	}
	
}
