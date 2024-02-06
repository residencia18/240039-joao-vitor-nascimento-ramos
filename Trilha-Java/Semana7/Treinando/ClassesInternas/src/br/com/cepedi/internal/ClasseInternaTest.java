package br.com.cepedi.internal;

public class ClasseInternaTest {
	
	private String name = "Naruto e sasuke";

	class Inner{
		public void printNameExternal() {
			System.out.println(name);
			System.out.println(ClasseInternaTest.this);
		}
	}
	
	public static void main(String[] args) {
		
		ClasseInternaTest c = new ClasseInternaTest();
		Inner i = c.new Inner();
		i.printNameExternal();
		
	}
}
