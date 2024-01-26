package br.com.cepedi;

import java.io.File;
import java.io.IOException;

public class Directory {
	
	public static void main(String[] args) throws IOException {
		File file = new File("pasta");
		file.mkdir();
		System.out.println(file.isDirectory());
		
		File fileArquivoDiretorio = new File("//home//joao//Documentos//arquivo.txt");
		System.out.println(fileArquivoDiretorio.isDirectory());
		System.out.println(fileArquivoDiretorio.createNewFile());
		File fileRename = new File("//home//joao//Documentos//arquivo_renomeado.txt");
		fileArquivoDiretorio.renameTo(fileRename);
	}

}
