package br.com.cepedi.model.veiculo;

import br.com.cepedi.exceptions.veiculo.modeloVeiculoInvalidoException;
import br.com.cepedi.exceptions.veiculo.montadoraVeiculoInvalidaException;
import br.com.cepedi.exceptions.veiculo.placaVeiculoInvalidaException;
import br.com.cepedi.verificacoes.veiculo.VerificacoesVeiculo;

public class Veiculo {
	
	private static int numeroDeVeiculos = 0;
	private int id;
	private String modelo;
	private String placa;
	private String montadora;
	
	
	public Veiculo(String modelo, String placa, String montadora) throws modeloVeiculoInvalidoException, montadoraVeiculoInvalidaException, placaVeiculoInvalidaException {
		super();
		setModelo(modelo);
		setMontadora(montadora);
		setPlaca(placa);
		numeroDeVeiculos++;
		this.id = numeroDeVeiculos;
	}
	


	public int getId() {
		return id;
	}

	
	public String getModelo() {
		return modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public String getMontadora() {
		return montadora;
	}



	@Override
	public String toString() {
		return "[modelo=" + modelo + ", placa=" + placa + ", montadora=" + montadora + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setModelo(String modelo) throws modeloVeiculoInvalidoException {
		if(VerificacoesVeiculo.verificaModelo(modelo)) {
			this.modelo = modelo;			
		}else {
			throw new modeloVeiculoInvalidoException();
		}
	}
	
	public void setPlaca(String placa) throws placaVeiculoInvalidaException {
		if(VerificacoesVeiculo.verificaPlaca(placa)) {
			this.placa=placa;
		}else {
			throw new placaVeiculoInvalidaException();
		}
	}

	public void setMontadora(String montadora) throws montadoraVeiculoInvalidaException {
		if(VerificacoesVeiculo.verificaMontadora(montadora)) {
			this.montadora = montadora;
		}else {
			throw new montadoraVeiculoInvalidaException();			
		}
	}
	
	
	
	
	
	

}
