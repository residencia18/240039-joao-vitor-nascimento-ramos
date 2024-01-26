package br.com.cepedi.test;

import java.util.Scanner;

import br.com.cepedi.modelos.DatasDeNascimento;
import br.com.cepedi.modelos.ListaTemperaturas;

public class Teste {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DatasDeNascimento datas = new DatasDeNascimento();
		datas.add(12, 7, 1998);
		datas.add(25, 9, 2022);
		datas.add(1, 02, 1982);
		datas.maximo();
		datas.minimo();
		datas.ordenado();
		datas.buscar(sc);
		
		ListaTemperaturas temperaturas = new ListaTemperaturas();
		temperaturas.add(150);
		temperaturas.add(150.8);
		temperaturas.add(150.45);
		
		
		
		
	}

}
