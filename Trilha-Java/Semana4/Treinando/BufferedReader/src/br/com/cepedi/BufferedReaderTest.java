package br.com.cepedi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderTest {
	
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
		
		try(FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr)	) {
			String texto;
			while((texto=br.readLine())!=null) {
				System.out.println(texto);
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
		
	}
}
