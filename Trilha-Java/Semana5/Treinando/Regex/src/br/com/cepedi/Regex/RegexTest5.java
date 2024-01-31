package br.com.cepedi.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest5 {
	public static void main(String[] args) {
		String regex = "\\d"; // tudo que for digito
		String regex2 = "\\D"; // Dutod que não for digito
		String regex3 = "\\s"; //Espaços em branco \t \n \f \r
		String regex4 = "\\S"; // todos os caracteres que nao forem em branco
		String regex5 = "\\w"; //a-zA-z,digitos
		String regex6 = "\\W"; //tudo que nao for incuso no \w
		String regex7 = "[a-zA-C]"; // range
		String regexHex = "0[xX]([A-F0-9a-f])+(\\s|$)";
		
		/*Quantificadores
		 * ? Zero ou uma
		 * * Zero ou mais
		 * + uma ou mais
		 * {n,m} de n até m
		 * $fim da linha
		 * 
		 * 
		 * Agrupamento
		 * ()
		 * |
		 * o(v|c)o  match para ovo ou oco
		 * 
		 * 
		 * 
		 * */
		
		
		
		String texto = "abaaba";
		String texto2 = "abababa";
		String texto3 = "cafeBABE";
		String textoHex = "12 0x 0X 0XFFABC 0X109 0X1";
		String textoComDigitos = "das4 5d@ád5 s1d";
		
		
		Pattern pattern = Pattern.compile(regexHex);
		Matcher matcher = pattern.matcher(textoHex);
		System.out.println("texto:   " + textoHex);
		System.out.println("indice:  0123456789");
		System.out.println("regex: " +regex);
		System.out.println("Posicoes encontradas : " );
		while(matcher.find()) {
			System.out.println(matcher.start()+" "+matcher.group()+ " ");
		}

	}
}
