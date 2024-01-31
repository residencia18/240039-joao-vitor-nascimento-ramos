package br.com.cepedi.lista;

import java.util.ArrayList;
import java.util.List;

public class ListTest03 {
	
	public static void main(String[] args) {
		Smartphone s1 = new Smartphone("123","Iphone");
		Smartphone s2 = new Smartphone("144","Samsumg");
		Smartphone s3 = new Smartphone("125","Motorola");
		
		List<Smartphone> smarts = new ArrayList();
		smarts.add(s1);
		smarts.add(s2);
		smarts.add(s3);
//		smarts.clear();
		for(Smartphone s : smarts) {
			System.out.println(s);
		}
		Smartphone s4 = new Smartphone("125","Motorola");
		
		System.out.println(smarts.contains(s4));
		System.out.println(smarts.indexOf(s4));

	}

}
