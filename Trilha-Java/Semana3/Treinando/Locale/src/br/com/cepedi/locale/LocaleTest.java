package br.com.cepedi.locale;

import java.util.Locale;

public class LocaleTest {
	
	public static void main(String[] args) {
		String countrys[] = Locale.getISOCountries();
		String languages[] = Locale.getISOLanguages();

//		for(String country : countrys) {
//			System.out.println(country);
//		}
//		for(String language : languages){
//			System.out.println(language);
//		}
		
		System.out.println(languages.length);
		System.out.println(countrys.length);
	}

}
