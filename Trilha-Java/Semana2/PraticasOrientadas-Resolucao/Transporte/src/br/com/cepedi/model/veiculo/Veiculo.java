package br.com.cepedi.model.veiculo;

import br.com.cepedi.verificacoes.veiculo.*;
import br.com.cepedi.exceptions.veiculo.*;

public class Veiculo {
	
	private static int numeroDeVeiculos = 0;
	private int id;
	private String modelo;
	private String placa;
	private String montadora;
	
	
	public Veiculo(String modelo, String placa, String montadora) throws modeloVeiculoInvalidoException, montadoraVeiculoInvalidaException, placaVeiculoInvalidaException {
		super();
		if(VerificacoesVeiculo.verificaModelo(modelo)) {
			this.modelo = modelo;			
		}else {
			throw new modeloVeiculoInvalidoException();
		}
		
		if(VerificacoesVeiculo.verificaMontadora(montadora)) {
			this.montadora = montadora;
		}else {
			throw new montadoraVeiculoInvalidaException();			
		}
		
		if(VerificacoesVeiculo.verificaPlaca(placa)) {
			this.placa=placa;
		}else {
			throw new placaVeiculoInvalidaException();
		}
		numeroDeVeiculos++;
		this.id = numeroDeVeiculos;
	}
	
	public Veiculo() {
		super();
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
	
	
	
	

}
