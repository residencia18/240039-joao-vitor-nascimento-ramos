package br.com.cepedi.test;

import br.com.cepedi.model.DataLoader;
import br.com.cepedi.model.DatabaseLoader;
import br.com.cepedi.model.FileLoader;

public class classTest {
	
	public static void main(String[] args) {
		DatabaseLoader db1 = new DatabaseLoader();
		FileLoader fl1 = new FileLoader();
		
		db1.load();
		fl1.load();
		db1.checkPermission();
		fl1.checkPermission();
		DataLoader.maxDateSize();
	}
	
}
