package br.com.cepedi.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest6 {
	public static void main(String[] args) {
		String regex = "([a-zA-z0-9\\._-])+@([a-zA-z])+(\\.([a-zA-z])+)+";
		String texto = "luffy@hotmail.com, 123jotaro@gmail.com, #@!zoro@mail.br";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(texto);
		System.out.println("texto:   " + texto);
		System.out.println("indice:  0123456789");
		System.out.println("regex: " +regex);
		System.out.println("Posicoes encontradas : " );
		while(matcher.find()) {
			System.out.println(matcher.start()+" "+matcher.group()+ " ");
		}	}
}
