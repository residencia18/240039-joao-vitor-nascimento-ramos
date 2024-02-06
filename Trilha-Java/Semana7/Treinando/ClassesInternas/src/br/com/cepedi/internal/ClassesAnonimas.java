package br.com.cepedi.internal;


class Animal{
	public void walk() {
		System.out.println("Animal andando");
	}
}

public class ClassesAnonimas {
	
	public static void main(String[] args) {
		Animal animal = new Animal() {
			@Override
			public void walk() {
				System.out.println("Cachorro andando");
			}
		};
		animal.walk();
	}
	
}
