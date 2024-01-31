package br.com.cepedi.lista;

import java.util.ArrayList;
import java.util.List;

public class ListTest02 {
	
	public static void main(String[] args) {
//		List nomes = new ArrayList(); feito ate a 1.4 
		List<String> nomes = new ArrayList(16);	
		List<String> nomes2 = new ArrayList(16);	
		nomes.add("OI");
		nomes.add("Strings");
		nomes2.add("Willian");
		nomes2.add("Array");
		nomes.addAll(nomes2);

		for( int i = 0 ; i < nomes.size() ; i++) {
			System.out.println(nomes.get(i));
		}	
	
		
	}

}
