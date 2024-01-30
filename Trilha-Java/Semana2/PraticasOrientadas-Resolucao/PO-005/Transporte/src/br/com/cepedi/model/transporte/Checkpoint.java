package br.com.cepedi.model.transporte;

import java.time.LocalDateTime;

public class Checkpoint {
	
	private PontoDeParada ponto;
	private int tempoParaChegar;
	
	
	public Checkpoint(PontoDeParada ponto) {
		setPonto(ponto);
	}
	
	
	@Override
	public String toString() {
		return "Checkpoint [ponto=" + ponto + ", tempoParaChegar=" + tempoParaChegar + "]";
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


	public int getHoraChegada() {
		return tempoParaChegar;
	}


	public void setHoraChegada(int horaChegadaMinutos) {
		if(horaChegadaMinutos<0) {
			throw new IllegalArgumentException("Foi inserido um tempo negativo");
		}
		
		this.tempoParaChegar = horaChegadaMinutos;
	}

	
	

	


}
