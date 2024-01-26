package br.com.cepedi.locale;

import java.text.NumberFormat;
import java.util.Locale;

public class LocaleTestNumber {
	
	public static void main(String[] args) {
		Locale localeJP = Locale.JAPAN;
		Locale LocaleIT = Locale.ITALIAN;
		Locale localeFranca = Locale.FRANCE;
		
		NumberFormat[] nfa = new NumberFormat[4];
		
		nfa[0] = NumberFormat.getInstance();
		nfa[1] = NumberFormat.getInstance(localeJP);
		nfa[2] = NumberFormat.getInstance(LocaleIT);
		nfa[3] = NumberFormat.getInstance(localeFranca);
		
		double valor = 10_300.2130;
		
		for(NumberFormat n : nfa) {
			System.out.println(n.format(valor));
		}
		
	}

}
