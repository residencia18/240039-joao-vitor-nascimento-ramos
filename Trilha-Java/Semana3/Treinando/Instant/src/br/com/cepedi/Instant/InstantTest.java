package br.com.cepedi.Instant;

import java.time.Instant;

public class InstantTest {
	
	public static void main(String[] args) {
		Instant i = Instant.now();
		System.out.println(i);
		System.out.println(i.getEpochSecond());
	}
}
