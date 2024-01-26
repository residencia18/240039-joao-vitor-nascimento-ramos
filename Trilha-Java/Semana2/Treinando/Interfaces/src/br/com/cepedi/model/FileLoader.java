package br.com.cepedi.model;

public class FileLoader implements DataLoader , DataRemover {
	
	public void load() {
		System.out.println("Carregando dados de um arquivo !");
	}
	
	public void remove() {
		System.out.println("Removendo dados de um arquivo !");
	}

}
