package br.com.cepedi.date;

import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		Date data = new Date(999999999999999999L);
		System.out.println(data);
		data.setTime(data.getTime()+7200000);
		System.out.println(data);
	}
}
