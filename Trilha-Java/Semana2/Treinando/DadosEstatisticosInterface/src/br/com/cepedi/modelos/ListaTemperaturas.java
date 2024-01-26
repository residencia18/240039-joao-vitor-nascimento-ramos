package br.com.cepedi.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.cepedi.Interfaces.DadosEstatisticos;

public class ListaTemperaturas implements DadosEstatisticos{
	
	ArrayList<Number> temperaturas;

	public ListaTemperaturas() {
		temperaturas = new ArrayList<>();
	}
	
	public void add(double valor) {
		temperaturas.add(valor);
	}
	
	private void imprimeTemperatura(Number temperatura) {
		System.out.println(temperatura + "ºC");
	}
	
	
	private boolean verificaListaVazia() {
		if(temperaturas.size()==0) {
			System.out.println("Não há temperaturas inseridas");
			return temperaturas.size()==0;
		}else {
			return false;
		}
	}

	private void mostraLista(ArrayList<Number> temperaturas) {
		for(Number temp : temperaturas) {
			imprimeTemperatura(temp);
		}
	}
	
	@Override
	public void maximo() {
		
		
	}

	@Override
	public void minimo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ordenado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscar(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
