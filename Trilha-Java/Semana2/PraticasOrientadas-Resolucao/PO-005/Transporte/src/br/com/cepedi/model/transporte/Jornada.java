package br.com.cepedi.model.transporte;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.veiculo.Veiculo;

public class Jornada {
	
	public static int qtdIDsGerados = 0;
	int id;
	private Veiculo veiculo;
	private ListaTrajetos trajetos;
	private Cobrador cobrador;
	private Motorista motorista;
	private String nome;
	private LocalDateTime dataInicio;
	
	public Jornada(Veiculo veiculo, Cobrador cobrador, Motorista motorista, String nome, LocalDateTime dataInicio) {
		super();
		setVeiculo(veiculo);
		setCobrador(cobrador);
		setMotorista(motorista);
		setNome(nome);
		setDataInicio(dataInicio);
		trajetos = new ListaTrajetos();
		qtdIDsGerados++;
		id = qtdIDsGerados;
	}
	
	public Jornada(Veiculo veiculo, Motorista motorista, String nome, LocalDateTime dataInicio) {
		super();
		setVeiculo(veiculo);
		setMotorista(motorista);
		setNome(nome);
		setDataInicio(dataInicio);
		trajetos = new ListaTrajetos();
		qtdIDsGerados++;
		id = qtdIDsGerados;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		if(veiculo==null) {
			throw new IllegalArgumentException("Tentativa de inserir valor nulo");
		}
		this.veiculo = veiculo;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		if(cobrador==null) {
			throw new IllegalArgumentException("Tentativa de inserir valor nulo");
		}
		this.cobrador = cobrador;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		if(motorista==null) {
			throw new IllegalArgumentException("Tentativa de inserir valor nulo");
		}
		this.motorista = motorista;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
	    if (dataInicio == null) {
			throw new IllegalArgumentException("Tentativa de inserir valor nulo");
	    }

	    if (dataInicio.isBefore(LocalDateTime.now())) {
	        throw new IllegalArgumentException("Horario da jornada não pode ser anterior ao atual");
	    }

	    this.dataInicio = dataInicio;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome==null) {
			throw new IllegalArgumentException("Tentativa de inserir valor nulo");
		}
		
		
		this.nome = nome;
	}

	public ListaTrajetos getTrajetos() {
		return trajetos;
	}

	@Override
	public String toString() {
		return "Jornada [id=" + id + ", veiculo=" + veiculo + ", trajetos=" + trajetos + ", cobrador=" + cobrador
				+ ", motorista=" + motorista + ", nome=" + nome + ", dataInicio=" + dataInicio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cobrador, dataInicio, motorista, nome, trajetos, veiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jornada other = (Jornada) obj;
		return Objects.equals(cobrador, other.cobrador) && Objects.equals(dataInicio, other.dataInicio)
				&& Objects.equals(motorista, other.motorista) && Objects.equals(nome, other.nome)
				&& Objects.equals(trajetos, other.trajetos) && Objects.equals(veiculo, other.veiculo);
	}


	
	
	

}
