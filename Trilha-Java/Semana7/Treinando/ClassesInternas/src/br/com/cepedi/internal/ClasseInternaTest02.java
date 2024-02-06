package br.com.cepedi.internal;

public class ClasseInternaTest02 {
	
	
	private String nome = "Kiba";
	
	void print() {
		
		final String lastName="Inuzuka";
		class localClass{
		   public void printLocal() {
			   System.out.println(nome + " " + lastName);
		   }
		}
		
		localClass l = new localClass();
		l.printLocal();
	}
	
	public static void main(String[] args) {
		ClasseInternaTest02 c = new ClasseInternaTest02();
		c.print();
	}

}
