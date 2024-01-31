package br.com.cepedi.Equals;

import br.com.cepedi.dominio.Smartphone;

public class EqualsTest {
	public static void main(String[] args) {
		Smartphone s1 = new Smartphone("1231","iphone");
		Smartphone s2 = new Smartphone("1231","iphone");
		System.out.println(s1.equals(s2));
	}
	
}
