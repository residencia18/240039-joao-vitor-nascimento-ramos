package br.com.cepedi.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileTest {
	public static void main(String[] args) {
		File file = new File(".arquivo.txt");
		try {
//			file.delete();
			boolean isCreate = file.createNewFile();
			boolean existe = file.exists();
			System.out.println(existe);
			System.out.println(file.getAbsolutePath());
			System.out.println(file.isDirectory());
			System.out.println(file.isFile());
			System.out.println(file.isHidden());
			System.out.println( new Date(file.lastModified()));
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
}
