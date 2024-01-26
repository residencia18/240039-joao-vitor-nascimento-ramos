package br.com.cepedi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWriterTest {
	
	public static void main(String[] args) {
		File file = new File("arquivo.txt");
		
		try(FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write("Elliot alderson");
			bw.newLine();
			bw.write("use kali Linux");
			bw.flush();
			
		}catch(IOException e) {
			e.getStackTrace();
		}
	}

}
