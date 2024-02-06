package br.com.cepedi.internal;

public class staticsAninhadasTest {
	
	public String name = "oi";

	static class  Nested{
		 public void print() {
			System.out.println(new staticsAninhadasTest().name);
		}
	}
	
	public static void main(String[] args) {
		Nested nestd = new Nested();
		nestd.print();
	}
	
}
