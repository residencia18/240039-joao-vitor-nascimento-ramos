package com.controleacademico.controleacademico.generator;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.controleacademico.controleacademico.model.Key;

public class RSAGenerator {
	
	private Key publickey;
	private Key privatekey; 
	
	public Key getPublickey() {
		return publickey;
	}

	public Key getPrivatekey() {
		return privatekey;
	}

	public RSAGenerator(int numbits) {
		
		BigInteger p = BigInteger.probablePrime(numbits, new SecureRandom());
		BigInteger q = BigInteger.probablePrime(numbits, new SecureRandom());
		
		BigInteger n = p.multiply(q);

		BigInteger z = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

	}
	
	
	
	

}
