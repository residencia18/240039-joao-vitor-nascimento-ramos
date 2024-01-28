package br.com.cepedi.LocalDateTimeTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeTest {
	
	public static void main(String[] args) {
		
		
		LocalDateTime ldt = LocalDateTime.now();
		
		System.out.println(ldt.getDayOfMonth());
		
		LocalDate ld = LocalDate.of(2024, 08, 20);
		LocalTime lt = LocalTime.of(15, 30);
	}

}
