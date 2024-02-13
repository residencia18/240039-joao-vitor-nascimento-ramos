package br.com.cepedi.controller.menus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.cepedi.conjuntos.Imoveis;
import br.com.cepedi.dao.FaturaDAO;
import br.com.cepedi.dao.ImovelDAO;
import br.com.cepedi.dao.RelogioDAO;
import br.com.cepedi.model.Imovel;
import br.com.cepedi.model.Relogio;
import br.com.cepedi.views.MenuFaturasView;

public abstract class MenuFaturasController {

	public static void selecionarAcao(Scanner sc, Imoveis imoveis) {

		int escolha;
		
		do {
			escolha = MenuFaturasView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				registrarNovaLeitura(sc,imoveis);
				break;
			case 2:
				mostraTodasFaturas(sc,imoveis);
				break;
			case 3:
				mostraTodasFaturasEmAberto(sc,imoveis);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	
	private static void registrarNovaLeitura(Scanner sc , Imoveis imoveis) {
	    int id;
	    String escolhaContinue = "";
	    Imovel imovel = null;

	    do {
	        try {
	            System.out.println("Digite o ID do imóvel que deseja registrar nova leitura : ");
	            id = Integer.parseInt(sc.nextLine());
	            Relogio relogio = RelogioDAO.buscarRelogio(id);
	            System.out.println(relogio);
	            imovel = ImovelDAO.buscarImovel(id);
	            imovel.setRelogio(relogio);
	            System.out.println("Digite o valor da nova leitura");
	            BigDecimal novoValor = new BigDecimal(sc.nextLine());
	            imovel.realizaLeitura(LocalDate.now(), novoValor);
		        break;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
	            escolhaContinue = sc.nextLine();
	            if (escolhaContinue.equals("0")) {
	                return;
	            }
	        }
	    } while (true);

	}
	
	private static void mostraTodasFaturas(Scanner sc, Imoveis imoveis) {
	    int id;
	    String escolhaContinue = "";
	    Imovel imovel = null;

	    do {
	        try {
	            System.out.println("Digite o ID do imóvel que deseja ver as faturas: ");
	            id = Integer.parseInt(sc.nextLine());
	            imovel = imoveis.buscar(id);
	            System.out.println("Faturas do imóvel:");
	            FaturaDAO.listarFaturasPorIdImovel(id).forEach(System.out::println);
	            break;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
	            escolhaContinue = sc.nextLine();
	            if (escolhaContinue.equals("0")) {
	                return;
	            }
	        }
	    } while (true);
	}

	private static void mostraTodasFaturasEmAberto(Scanner sc, Imoveis imoveis) {
	    int id;
	    String escolhaContinue = "";
	    Imovel imovel = null;

	    do {
	        try {
	            System.out.println("Digite o ID do imóvel que deseja ver as faturas em aberto: ");
	            id = Integer.parseInt(sc.nextLine());
	            imovel = imoveis.buscar(id);
	            System.out.println("Faturas em aberto do imóvel:");
	            FaturaDAO.listarFaturasEmAbertoPorIdImovel(id).forEach(System.out::println);
	            break;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
	            escolhaContinue = sc.nextLine();
	            if (escolhaContinue.equals("0")) {
	                return;
	            }
	        }
	    } while (true);
	}
}
