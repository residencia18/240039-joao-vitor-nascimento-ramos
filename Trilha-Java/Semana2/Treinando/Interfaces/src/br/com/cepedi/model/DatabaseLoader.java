package br.com.cepedi.model;

public class DatabaseLoader implements DataLoader , DataRemover{
	
	public void load() {
		System.out.println("Carregando dados do banco de dados!");
	}
	
	public void remove() {
		System.out.println("Removendo dados de um arquivo");
	}
	
	public void checkPermission() {
		System.out.println("Fazendo checagem de permissões no banco de dados");

	}

}
