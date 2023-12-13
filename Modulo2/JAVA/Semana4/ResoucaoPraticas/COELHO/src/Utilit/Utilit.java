package Utilit;

public class Utilit {
	public static void LimparTela() {
		if (System.console() == null) {
            System.out.println("O console não suporta sequências ANSI.");
            return;
        }

        // Limpa o console usando sequência ANSI
        System.out.print("\033[H\033[2J");
	}
	
}
