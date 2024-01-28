package br.com.cepedi.DurationTeste;

import java.time.Duration;
import java.time.LocalDateTime;

public class DurationTeste {
	
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		
		LocalDateTime twoYearsAfter = LocalDateTime.now().plusYears(2).plusMinutes(8);
		Duration d1 = Duration.between(now, twoYearsAfter);
		System.out.println(d1);
	}

}
