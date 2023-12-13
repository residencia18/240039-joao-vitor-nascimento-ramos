package manipulaArray;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ManipulaArray {

    public static void main(String[] args) {
        // Testa as funcionalidades
        int[] arrayLidoUsuario = lerArrayUsuario();
        System.out.println("Array lido do usuário: " + Arrays.toString(arrayLidoUsuario));

        int[] arrayAleatorio = criarArrayAleatorio(5); 
        System.out.println("Array criado aleatoriamente: " + Arrays.toString(arrayAleatorio));

        int soma = calcularSoma(arrayAleatorio);
        System.out.println("Soma dos elementos do array aleatório: " + soma);

        int maiorValor = encontrarMaiorValor(arrayAleatorio);
        System.out.println("Maior valor do array aleatório: " + maiorValor);

        int menorValor = encontrarMenorValor(arrayAleatorio);
        System.out.println("Menor valor do array aleatório: " + menorValor);
    }

    public static int[] lerArrayUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o tamanho do array: ");
        int tamanho = scanner.nextInt();

        int[] array = new int[tamanho];

        System.out.println("Digite os elementos do array:");

        for (int i = 0; i < tamanho; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        return array;
    }

    public static int[] criarArrayAleatorio(int tamanho) {
        Random random = new Random();
        int[] array = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(100) + 1 ;
        }

        return array;
    }

    public static int calcularSoma(int[] array) {
        int soma = 0;

        for (int elemento : array) {
            soma += elemento;
        }

        return soma;
    }

    public static int encontrarMaiorValor(int[] array) {
        int maiorValor = Integer.MIN_VALUE;

        for (int elemento : array) {
            if (elemento > maiorValor) {
                maiorValor = elemento;
            }
        }

        return maiorValor;
    }

    public static int encontrarMenorValor(int[] array) {
        int menorValor = Integer.MAX_VALUE;

        for (int elemento : array) {
            if (elemento < menorValor) {
                menorValor = elemento;
            }
        }

        return menorValor;
    }
}
