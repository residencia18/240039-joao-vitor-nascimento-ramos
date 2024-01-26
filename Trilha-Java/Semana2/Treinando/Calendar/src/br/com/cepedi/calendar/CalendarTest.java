package br.com.cepedi.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		System.out.println(date);
		if(c.getFirstDayOfWeek()==c.SUNDAY) {
			System.out.println("Domingo é o primeiro dia da semana");
		}
		
		c.add(Calendar.HOUR, 4);
		Date date2 = c.getTime();
		System.out.println(date2);
		
		System.out.println(c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR));
	}
}
