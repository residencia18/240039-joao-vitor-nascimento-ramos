package VIEW;

import java.util.Scanner;

public abstract class MenuGeral {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenuGeral() {
        int opcao;
        do {
            System.out.println("==== Menu Geral ====");
            System.out.println("1. Menu de Clientes");
            System.out.println("2. Menu de Imóveis");
            System.out.println("3. Menu de Faturas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    MenuGestaoClientes.exibirMenu();
                    break;
                case 2:
                    MenuGestaoImoveis.exibirMenu();
                    break;
                case 3:
                    MenuGestaoFaturas.exibirMenu();
                    break;
                case 0:
                    System.out.println("Saindo do Menu Geral. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
        System.exit(0);
    }
}
