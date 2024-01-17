package br.com.cepedi;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderTest {
	public static void main(String[] args) {
		File file = new File("arquivo.txt");
		
		try(FileWriter fw = new FileWriter(file)){
			fw.write("A Quanto mais silencioso você fica, mais você escuta");
			fw.flush();
		}catch(IOException e ) {
			e.getStackTrace();
		}
		
		
		
		try(FileReader fr = new FileReader(file)) {
			int c;
			while((c=fr.read()) != -1) {
				System.out.printf("%c",c);
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
		
	}
}
