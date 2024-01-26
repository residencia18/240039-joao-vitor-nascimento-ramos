package br.com.cepedi.dateformat;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatTest {
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		DateFormat[] df = new DateFormat[7];
		df[0] = DateFormat.getInstance();
		df[1] = DateFormat.getDateInstance();
		df[2] = DateFormat.getDateTimeInstance();
		df[3] = DateFormat.getDateInstance(DateFormat.SHORT);
		df[4] = DateFormat.getDateInstance(DateFormat.MEDIUM);
		df[5] = DateFormat.getDateInstance(DateFormat.LONG);
		df[6] = DateFormat.getDateInstance(DateFormat.FULL);
		
		for(DateFormat d : df) {
			System.out.println(d.format(date));
		}
		
	}

}
