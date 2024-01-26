package br.com.cepedi.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import br.com.cepedi.Interfaces.DadosEstatisticos;

public class DatasDeNascimento implements DadosEstatisticos{
	
	private ArrayList<LocalDate> datas;

	public DatasDeNascimento() {
		this.datas = new ArrayList<>();
	}
	
	public void add(int dia , int mes , int ano) {
        datas.add(LocalDate.of(ano, mes, dia));
	}
	
	private void imprimeData(LocalDate data) {
		System.out.println(data.getDayOfMonth() + " de " + data.getMonth() + " de " + data.getYear());
	}
	
	private boolean verificaListaVazia() {
		if(datas.size()==0) {
			System.out.println("Não há datas inseridas");
			return datas.size()==0;
		}else {
			return false;
		}
	}
	
	private void mostraLista(ArrayList<LocalDate> datasImpressao) {
		for(LocalDate data : datasImpressao) {
			imprimeData(data);
		}
	}

	@Override
	public void maximo() {
		if(verificaListaVazia()) {
			return;
		}
		int ultimoIndice = datas.size()-1;
		ArrayList<LocalDate> datasTemporarias = new ArrayList<>(datas);
		Collections.sort(datasTemporarias);
		imprimeData(datasTemporarias.get(ultimoIndice));
	}
	


	@Override
	public void minimo() {
		if(verificaListaVazia()) {
			return;
		}
		ArrayList<LocalDate> datasTemporarias = new ArrayList<>(datas);
		Collections.sort(datasTemporarias);
		imprimeData(datasTemporarias.get(0));
	}

	@Override
	public void ordenado() {
		ArrayList<LocalDate> datasTemporarias = new ArrayList<>(datas);
		Collections.sort(datasTemporarias);
		mostraLista(datasTemporarias);
	}

	@Override
	public void buscar(Scanner sc) {
		int dia,mes,ano;
		System.out.println("Digite o dia : ");
		dia = Integer.parseInt(sc.nextLine());
		System.out.println("Digite o mes : ");
		mes = Integer.parseInt(sc.nextLine());
		System.out.println("Digite o ano : ");
		ano = Integer.parseInt(sc.nextLine());
		
		
		for(LocalDate date : datas) {
			if(date.equals(LocalDate.of(ano, mes, dia))) {
				System.out.println("Encontrou");
				return;
			}
		}
		System.out.println("Não encontrou");
		
	}

	
	
	

	
	

}
