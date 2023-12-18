package br.com.cepedi.atividade3.model;

import java.util.ArrayList;

public class Garagem {
	
	ArrayList<Veiculo> veiculos;
	boolean tomadaEletrica;
	
	public Garagem(boolean tomadaEletrica) {
		super();
		veiculos = new ArrayList<>();
		this.tomadaEletrica = tomadaEletrica;
	}
	
	public void adicionarVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}
	
	

}
