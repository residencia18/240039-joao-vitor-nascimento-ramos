package br.com.cepedi.utils;

public class Util {
	
	public static boolean caracterRepetido(String msg) {
		for(int i = 0 ; i < msg.length()-1 ; i++) {
			for(int j = i+1 ; j < msg.length() ; j++) {
				if(msg.charAt(i)==msg.charAt(j)) {
		            throw new IllegalArgumentException("Não podem haver caracteres repetidos no alfabeto"); 
				}
			}
			
		}
		return false;
	}

}
