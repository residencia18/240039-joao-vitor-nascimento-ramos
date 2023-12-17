package br.com.cepedi.calculadoraDivisao.App;

import java.util.Scanner;

import br.com.cepedi.calculadoraDivisao.Calculadora.Calculadora;
import br.com.cepedi.calculadoraDivisao.Exceptions.DivisionByZeroException;

public class App {
	
    private static int exibirMenu(Scanner scanner) {
        System.out.println("Escolha a operação:");
        System.out.println("1. Adição");
        System.out.println("2. Subtração");
        System.out.println("3. Multiplicação");
        System.out.println("4. Divisão");
        System.out.println("0. Sair");
        System.out.print("Digite o número da operação desejada: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float num1=0,num2=0;
        int num1Int=0 , num2Int= 0;
        int escolha;
        boolean operacaoInteira = false;
        do {
            escolha = exibirMenu(scanner);
            
            if(escolha >=1 && escolha <=3) {
                System.out.print("Digite o primeiro número: ");
                num1 = scanner.nextFloat();
                System.out.print("Digite o segundo número: ");
                num2 = scanner.nextFloat();
                
                if(num1 == (int)num1 && num2 == (int)num2) {
                	operacaoInteira = true;
                	num1Int = (int)num1;
                	num2Int = (int)num2;
                }
            }else if(escolha == 4) {
                System.out.print("Digite o numerador: ");
                num1 = scanner.nextFloat();
                System.out.print("Digite o denominador: ");
                num2 = scanner.nextFloat();
                
                if(num1 == (int)num1 && num2 == (int)num2) {
                	operacaoInteira = true;
                	num1Int = (int)num1;
                	num2Int = (int)num2;
                }
            }

            switch (escolha) {
                case 1:
                	System.out.println("Resultado: " + (operacaoInteira ? Calculadora.somar(num1Int, num2Int) : Calculadora.somar(num1, num2)));
                    break;
                case 2:
                	System.out.println("Resultado: " + (operacaoInteira ? Calculadora.subtrair(num1Int, num2Int) : Calculadora.subtrair(num1, num2)));
                    break;
                case 3:
                	System.out.println("Resultado: " + (operacaoInteira ? Calculadora.multiplicar(num1Int, num2Int) : Calculadora.multiplicar(num1, num2)));
                    break;
                case 4:
                    try {
                    	System.out.println("Resultado: " + (operacaoInteira ? Calculadora.dividir(num1Int, num2Int) : Calculadora.dividir(num1, num2)));
                    } catch (DivisionByZeroException e) {
                        System.err.println("Erro: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        } while (escolha != 0);
    }

}
