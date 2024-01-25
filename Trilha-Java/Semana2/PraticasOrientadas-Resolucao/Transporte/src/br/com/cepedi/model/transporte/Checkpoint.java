package br.com.cepedi.model.transporte;

import java.time.LocalDateTime;

public class Checkpoint {
	
	private PontoDeParada ponto;
	private LocalDateTime horaChegada;
	
	
	public Checkpoint(PontoDeParada ponto) {
		setPonto(ponto);
	}
	
	
	@Override
	public String toString() {
		return "Checkpoint [ponto=" + ponto + ", horaChegada=" + horaChegada + "]";
	}

	public PontoDeParada getPonto() {
		return ponto;
	}

	public void setPonto(PontoDeParada ponto) {
		if(ponto==null) {
			throw new NullPointerException("Foi inserido um ponto nulo");
		}
		this.ponto = ponto;
	}


	public LocalDateTime getHoraChegada() {
		return horaChegada;
	}


	public void setHoraChegada(LocalDateTime horaChegada) {
		if(horaChegada==null) {
			throw new NullPointerException("Foi inserido uma hora nula");
		}
		
		this.horaChegada = horaChegada;
	}

	
	

	


}
