package br.com.cepedi.model.transporte;

import java.time.LocalDateTime;

import br.com.cepedi.model.listas.ListaTrajetos;
import br.com.cepedi.model.pessoa.Cobrador;
import br.com.cepedi.model.pessoa.Motorista;
import br.com.cepedi.model.veiculo.Veiculo;
import br.com.cepedi.verificacoes.pessoa.VerificacoesPessoa;

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
		qtdIDsGerados++;
		id = qtdIDsGerados;
	}
	
	public Jornada(Veiculo veiculo, Motorista motorista, String nome, LocalDateTime dataInicio) {
		super();
		setVeiculo(veiculo);
		setMotorista(motorista);
		setNome(nome);
		setDataInicio(dataInicio);
		qtdIDsGerados++;
		id = qtdIDsGerados;
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
	
	
	
	

	
	

}
