package br.com.cepedi.localDate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

public class LocalDateTest {
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyyy ");
		LocalDate date = LocalDate.of(26, 01, 2024);
//		
//		System.out.println(sdf.format(Calendar.getInstance()));
		
		LocalDate date2 = LocalDate.of(25,Month.JANUARY,2024);
//		System.out.println(sdf.format(date));
		System.out.println(date.lengthOfMonth());
		System.out.println(date.lengthOfYear());
	}

}
