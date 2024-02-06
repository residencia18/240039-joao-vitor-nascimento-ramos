package br.com.cepedi.model.transporte;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class JornadaTrajetoHorario implements Serializable {
	
	private static final long serialVersionUID = 20L;

	Jornada jornada;
	Trajeto trajeto;
	LocalDateTime horarioInicioTrajeto;
	
	
	public JornadaTrajetoHorario(Jornada jornada, Trajeto trajeto, LocalDateTime horarioInicioTrajeto) {
		super();
		setJornada(jornada);
		setTrajeto(trajeto);
		setHorarioInicioTrajeto(horarioInicioTrajeto);
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


	public Trajeto getTrajeto() {
		return trajeto;
	}


	public void setTrajeto(Trajeto trajeto) {
		if(trajeto==null) {
			throw new NullPointerException("Tentativa de inserir valor nulo");
		}
		this.trajeto = trajeto;
	}


	public LocalDateTime getHorarioInicioTrajeto() {
		return horarioInicioTrajeto;
	}


	public void setHorarioInicioTrajeto(LocalDateTime horarioInicioTrajeto) {
		if(horarioInicioTrajeto==null) {
			throw new NullPointerException("Tentativa de inserir valor nulo");
		}
		this.horarioInicioTrajeto = horarioInicioTrajeto;
	}


	@Override
	public int hashCode() {
		return Objects.hash(horarioInicioTrajeto, jornada, trajeto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JornadaTrajetoHorario other = (JornadaTrajetoHorario) obj;
		return Objects.equals(horarioInicioTrajeto, other.horarioInicioTrajeto)
				&& Objects.equals(jornada, other.jornada) && Objects.equals(trajeto, other.trajeto);
	}


	@Override
	public String toString() {
		return "JornadaTrajetoHorario [jornada=" + jornada + ", trajeto=" + trajeto + ", horarioInicioTrajeto="
				+ horarioInicioTrajeto + "]";
	}
	
	
	
	

}
