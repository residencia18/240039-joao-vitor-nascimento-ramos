package br.com.cepedi.model.transporte;

import java.util.Objects;

import br.com.cepedi.model.pessoa.Passageiro;

public class Embarque {
	
	private Passageiro passageiro;
	private Jornada jornada;
	private Trajeto trajeto;
	private Trecho trecho;
	private PontoDeParada ponto;
	
	public Embarque(Passageiro passageiro, Jornada jornada, Trajeto trajeto, Trecho trecho, PontoDeParada ponto) {
		super();
		setPassageiro(passageiro);
		setJornada(jornada);
		setTrajeto(trajeto);
		setTrecho(trecho);
		setPonto(ponto);
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		if(passageiro==null) {
			throw new NullPointerException("Tentativa de inserir valor nulo");
		}
		this.passageiro = passageiro;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		if(jornada==null) {
			throw new NullPointerException("Tentativa de inserir valor nulo");
		}
		this.jornada = jornada;
	}

	public PontoDeParada getPonto() {
		return ponto;
	}

	public void setPonto(PontoDeParada ponto) {
		if(ponto==null) {
			throw new NullPointerException("Tentativa de inserir valor nulo");
		}
		this.ponto = ponto;
	}
	
	
	

	public Trajeto getTrajeto() {
		return trajeto;
	}

	public void setTrajeto(Trajeto trajeto) {
		if(trajeto==null) {
			throw new NullPointerException("Tentativa de inserir valor nulo");
		}
		this.trajeto = trajeto;
	}

	public Trecho getTrecho() {
		return trecho;
	}

	public void setTrecho(Trecho trecho) {
		if(trecho==null) {
			throw new NullPointerException("Tentativa de inserir valor nulo");
		}
		this.trecho = trecho;
	}

	@Override
	public String toString() {
		return "Embarque [passageiro=" + passageiro + ", jornada=" + jornada + ", ponto=" + ponto + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(jornada, passageiro, ponto, trajeto, trecho);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Embarque other = (Embarque) obj;
		return Objects.equals(jornada, other.jornada) && Objects.equals(passageiro, other.passageiro)
				&& Objects.equals(ponto, other.ponto) && Objects.equals(trajeto, other.trajeto)
				&& Objects.equals(trecho, other.trecho);
	}
	
	
	
	
	

}
