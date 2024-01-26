package br.com.cepedi.locale;

import java.text.NumberFormat;
import java.util.Locale;

public class LocaleTestMoney {
	
	public static void main(String[] args) {
		Locale localeJP = Locale.JAPAN;
		Locale LocaleIT = Locale.ITALIAN;
		Locale localeFranca = Locale.FRANCE;
		
		NumberFormat[] nfa = new NumberFormat[4];
		
		nfa[0] = NumberFormat.getCurrencyInstance();
		nfa[1] = NumberFormat.getCurrencyInstance(localeJP);
		nfa[2] = NumberFormat.getCurrencyInstance(LocaleIT);
		nfa[3] = NumberFormat.getCurrencyInstance(localeFranca);
		
		double valor = 10_300.2130;
		
		for(NumberFormat n : nfa) {
			n.setMaximumFractionDigits(2);
			System.out.println(n.format(valor));
		}
		
		String valorS = "10_300.2130";

		
		try {
			System.out.println(nfa[0].parse("R$ 10.300,21"));
		}catch(Exception e ) {
			e.getStackTrace();
		}
		
	}

}
