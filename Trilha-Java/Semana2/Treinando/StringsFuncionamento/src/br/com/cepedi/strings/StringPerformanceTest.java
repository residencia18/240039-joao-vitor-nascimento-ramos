package br.com.cepedi.strings;

public class StringPerformanceTest {
	
	
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		concat(200_000);
		long time2 = System.currentTimeMillis();
		
		long time3 = System.currentTimeMillis();
		contatBuilder(200_000);
		long time4 = System.currentTimeMillis();
		
		long time5 = System.currentTimeMillis();
		contatBuffer(200_000);
		long time6 = System.currentTimeMillis();

		System.out.println("String normal = " + (time2-time) + "ms");
		System.out.println("String builder = " + (time4-time3) + "ms");
		System.out.println("String buffer = " + (time6-time5) + "ms");

	}
	
	
	private static void concat(int tam) {
		String test="";
		for(int i = 0 ; i < tam ; i++) {
			test+=i;
		}
	}
	
	private static void contatBuilder(int tam) {
		StringBuilder sb1 = new StringBuilder(tam);
		for(int i = 0 ; i < tam ; i++) {
			sb1.append(i);
		}
	}
	
	private static void contatBuffer(int tam) {
		StringBuffer sb1 = new StringBuffer(tam);
		for(int i = 0 ; i < tam ; i++) {
			sb1.append(i);
		}
	}

}
