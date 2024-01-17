package br.com.cepedi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
	
	public static void main(String[] args) {
		File file = new File("arquivo.txt");
		
		try(FileWriter fw = new FileWriter(file)) {
			fw.write("Elliot alderson");
			fw.flush();
			
		}catch(IOException e) {
			e.getStackTrace();
		}
	}



}
