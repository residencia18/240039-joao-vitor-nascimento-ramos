package br.com.cepedi.LocalTime;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class LocalTimeTest {
	
	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		LocalTime lt = LocalTime.now();
		
		System.out.println(lt.get(ChronoField.HOUR_OF_DAY));
		
	}

}
