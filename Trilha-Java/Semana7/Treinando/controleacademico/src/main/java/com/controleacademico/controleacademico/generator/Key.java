package com.controleacademico.controleacademico.generator;

import java.math.BigInteger;

public class Key {

	private BigInteger componente;
	private BigInteger modulo;

	public Key(BigInteger componente, BigInteger modulo) {
		super();
		this.componente = componente;
		this.modulo = modulo;
	}

	public BigInteger getComponente() {
		return componente;
	}

	public void setComponente(BigInteger componente) {
		this.componente = componente;
	}

	public BigInteger getModulo() {
		return modulo;
	}

	public void setModulo(BigInteger modulo) {
		this.modulo = modulo;
	}

	
	
}
