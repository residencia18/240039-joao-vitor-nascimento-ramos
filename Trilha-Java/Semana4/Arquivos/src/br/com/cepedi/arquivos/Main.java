package br.com.cepedi.arquivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		process(args[0],args[1],args[3]);
		process(args[1],args[2],args[3]);
	}
	
	public static void process(String file1 , String file2, String senha) throws FileNotFoundException {
		try {
			FileInputStream entrada = new FileInputStream(file1);
			FileOutputStream saida = new FileOutputStream(file2);
			boolean eof=false;
			int input = -1;
			int cont =0;
			int limite = senha.length()-1;
			int valorByte;
			int valorXor;
			while(!eof) {
				input = entrada.read();
				if(input!=-1) {
					valorByte = (byte)senha.charAt(cont);
					valorXor = (byte)input^valorByte;
					if(cont==limite) {
						cont=0;
					}
					cont++;
					saida.write(valorXor);
				}else {
					eof=true;
				}
			}
			System.out.println("terminado");
			System.out.println(senha);
			entrada.close();
			saida.close();
		}catch(IOException e) {
		    e.printStackTrace();
		}
	}

}
