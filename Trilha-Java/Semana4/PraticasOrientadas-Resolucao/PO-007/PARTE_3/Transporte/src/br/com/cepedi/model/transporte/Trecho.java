package br.com.cepedi.model.transporte;

import java.io.Serializable;
import java.util.Objects;

public class Trecho  implements Serializable {
	
	private static final long serialVersionUID = 17L;

	
	public static int qtdTrechos = 0;

	
	private int id;
	private PontoDeParada origem;
	private PontoDeParada destino;
	private int minutos;
	
	public Trecho(PontoDeParada origem, PontoDeParada destino, int minutos) {
		super();
		setOrigem(origem);
		setDestino(destino);
		setMinutos(minutos);
		qtdTrechos++;
		this.id = qtdTrechos;
	}
	
	public Trecho(int id ,PontoDeParada origem, PontoDeParada destino, int minutos) {
		super();
		setOrigem(origem);
		setDestino(destino);
		setMinutos(minutos);
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PontoDeParada getOrigem() {
		return origem;
	}
	public void setOrigem(PontoDeParada origem) {
		if(origem==null) {
			throw new NullPointerException("Foi inserido um ponto nulo");
		}
		this.origem = origem;
	}
	public PontoDeParada getDestino() {
		return destino;
	}
	public void setDestino(PontoDeParada destino) {
		if(destino==null) {
			throw new NullPointerException("Foi inserido um ponto nulo");
		}
		this.destino = destino;
	}
	public int getMinutos() {
		return minutos;
	}
	public void setMinutos(int minutos) {
		if(minutos==0) {
			throw new IllegalArgumentException("O tempo em minutos de um trecho deve ser de no mínimo 1");
		}else if(minutos<0) {
			throw new IllegalArgumentException("O tempo em minutos de um trecho não pode ser negativo");
		}
		
		this.minutos = minutos;
	}


	@Override
	public String toString() {
		return "Trecho [id=" + id + ", origem=" + origem + ", destino=" + destino + ", minutos=" + minutos + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(destino, minutos, origem);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trecho other = (Trecho) obj;
		return Objects.equals(destino, other.destino) && minutos == other.minutos
				&& Objects.equals(origem, other.origem);
	}
	
	



	
	
	
	

}
