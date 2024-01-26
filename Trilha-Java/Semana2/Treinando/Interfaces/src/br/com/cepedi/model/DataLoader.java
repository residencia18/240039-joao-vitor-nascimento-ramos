package br.com.cepedi.model;

public interface DataLoader {
	
	public final int MAX_DATA_SIZE = 15;
	
	void load();
	
	default void checkPermission() {
		System.out.println("Fazendo checagem de permissões");
	}
	
	public static void maxDateSize() {
		System.out.println("Valor maximo : " + MAX_DATA_SIZE);
	}
}
