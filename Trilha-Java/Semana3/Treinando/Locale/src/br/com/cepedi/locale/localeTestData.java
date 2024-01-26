package br.com.cepedi.locale;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class localeTestData {
	
public static void main(String[] args) {
	Locale localItaly = new Locale("it","IT");
	Locale localeCH = new Locale("es","ES");
	Locale localeHolanda = new Locale("nl","NL");
	Calendar calendar = Calendar.getInstance();
	DateFormat df1 = DateFormat.getDateInstance(DateFormat.FULL,localItaly);
	DateFormat df2 = DateFormat.getDateInstance(DateFormat.FULL,localeCH);
	DateFormat df3 = DateFormat.getDateInstance(DateFormat.FULL,localeHolanda);

	System.out.println(df1.format(calendar.getTime()));
	System.out.println(df2.format(calendar.getTime()));
	System.out.println(df3.format(calendar.getTime()));

	
	System.out.println(localItaly.getDisplayCountry());
	System.out.println(localeCH.getDisplayCountry());
	System.out.println(localeHolanda.getDisplayCountry());

}
}
