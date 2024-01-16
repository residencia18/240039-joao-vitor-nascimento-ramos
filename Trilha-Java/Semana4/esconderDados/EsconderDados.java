package br.com.cepedi.esconderDados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class EsconderDados {
	
	public static void main(String[] args){
		
		processEsconder(args[0] , args[1] , "Quanto mais silencioso você se torna, mais você é capaz de ouvir");
		processMostrar(args[0] , args[1]);
		
	}
	
	public static void processEsconder(String file, String file2, String msg) {
		int tamanhoMsg = msg.length(); // estabelece o tamanho da mensagem
		long tamanhoArquivo = obterTamanhoArquivo(file); //obtem o tamanho do arquivo para ser valor maximo
		long contador = 1;
		int qntByesModificados=0;
		int input = -1;
		int valorXor;
		//cria uma arvore para armazenas os valores em um conjunto ja de forma ordenada
		Set<Long> bytesSorteados = new TreeSet<>();
		
		//gera os numeros aleatorios e armazena sem repetição
		while(bytesSorteados.size()<tamanhoMsg) {
			bytesSorteados.add(gerarNumeroAleatorio(1000,tamanhoArquivo));
		}

        ArrayList<Long> listaValores = new ArrayList<>(bytesSorteados);

		try {
			FileInputStream entrada = new FileInputStream(file);	
			FileOutputStream saida = new FileOutputStream(file2);

			while (contador <= tamanhoArquivo) {
			    input = entrada.read();
			    if (qntByesModificados < tamanhoMsg && listaValores.get(qntByesModificados) == contador ) {
			        input ^= msg.charAt(qntByesModificados);
			        qntByesModificados++;
			    }
			    saida.write(input);
			    contador++;
			}
		}catch(IOException e) {
		    e.printStackTrace();
		}
		

	}
	
    public static long gerarNumeroAleatorio(int limiteInferior, long limiteSuperior) {
        Random random = new Random();

        long numeroAleatorio = random.nextLong(limiteSuperior - limiteInferior + 1) + limiteInferior;

        return numeroAleatorio;
    }
    
    public static long obterTamanhoArquivo(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);

        if (arquivo.exists()) {
            long tamanhoEmBytes = arquivo.length();
            return tamanhoEmBytes;
        } else {
            System.out.println("O arquivo não existe.");
            return -1;
        }
    }
    
    public static void processMostrar(String fileOriginal , String fileModificado) {
		try {
			FileInputStream entradaOriginal = new FileInputStream(fileOriginal);
			FileInputStream entradaModificado = new FileInputStream(fileModificado);
			boolean eof=false;
			int input1 = -1;
			int input2 = -1;
			int cont =0;
			int valorByte;
			int valorXor;
			while(!eof) {
				input1 = entradaOriginal.read();
				input2 = entradaModificado.read();
				if(input1!=-1) {
					if( input1 != input2) {
						System.out.printf("%c",(input1^input2));
					}
				}else {
					eof=true;
				}
			}

		}catch(IOException e) {
		    e.printStackTrace();
		}
    }

}
