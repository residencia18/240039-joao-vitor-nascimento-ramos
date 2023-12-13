package VIEW;



import java.util.ArrayList;
import java.util.Scanner;

import DAO.Fatura_DAO;
import DAO.Imovel_DAO;
import MODEL.Fatura;
import MODEL.Imovel;
import MODEL.Leitura;
import MODEL.Pagamento;

public abstract class MenuGestaoFaturas {

    private static Scanner scanner = new Scanner(System.in);
    private static final Imovel_DAO imovelDAO = new Imovel_DAO();
    private static final Fatura_DAO faturaDAO = new Fatura_DAO();

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("==== Menu Faturas ====");
            System.out.println("1. Criar Fatura");
            System.out.println("2. Listar Todas as Faturas");
            System.out.println("3. Listar Faturas em Aberto");
			System.out.println("4. Pagar Fatura");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarFatura();
                    break;
                case 2:
                    listarTodasFaturas();
                    break;
                case 3:
                    listarFaturasEmAberto();
                    break;
				case 4:
					PagarFatura();
                case 0:
                    System.out.println("Voltando ao Menu Anterior.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void PagarFatura() {
		// TODO Auto-generated method stub
    	System.out.println("==== Pagar Fatura ====");
        System.out.print("Matrícula do Imóvel: ");
        String matricula = scanner.next();
        scanner = new Scanner(System.in);
        
        System.out.print("Codigo da Fatura: ");
        int id_fatura = scanner.nextInt();
        		        
        Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);
		Fatura fatura = faturaDAO.obterFaturaPorNumero(id_fatura);
		if (imovel != null && fatura != null) {
			System.out.println("Fatura: "+fatura.getValor());
			System.out.print("Valor da Fatura Pago: ");
	        double valor = scanner.nextDouble();
			fatura.setQuitado(true);
			Pagamento pagamento = new Pagamento(valor, fatura);
			faturaDAO.atualizarFatura(pagamento.getFatura());
		}
	}

	private static void criarFatura() {
        System.out.println("==== Criar Fatura ====");
        System.out.print("Matrícula do Imóvel: ");
        String matricula = scanner.next();
        scanner = new Scanner(System.in);

        Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);
        
        if (imovel != null) {
                                    
            // Criar instância de Fatura
			Fatura fatura = new Fatura(imovel.getUltimaLeitura(), imovel.getPenultimaLeitura(), imovel);			          
            ArrayList<Fatura> faturas = faturaDAO.obterFaturasPorImovel(imovel.getId());
            if (faturas.size() > 0) {
	            for (Fatura fat : faturas) {
		            if(fat.getUltimaLeitura() == fatura.getUltimaLeitura() && fat.getPenultimaLeitura() == fatura.getPenultimaLeitura()) {
		            	System.out.println("Fatura já existente!");
		            	return;
		            }
	            }
            }
            
            faturaDAO.adicionarFatura(fatura);
            
            System.out.println("Fatura criada com sucesso!");
        } else {
            System.out.println("Imóvel não encontrado.");
        }
    }

    private static void listarTodasFaturas() {
        System.out.println("==== Listar Todas as Faturas ====");
        System.out.print("Matrícula do Imóvel: ");
        String matricula = scanner.next();

        Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);

        if (imovel != null) {
        	ArrayList<Fatura> faturasDoImovel = faturaDAO.obterFaturasPorImovel(imovel.getId());
            for (Fatura fatura : faturasDoImovel) {
                exibirDetalhesFatura(fatura);
            }
        } else {
            System.out.println("Imóvel não encontrado.");
        }
    }

    private static void listarFaturasEmAberto() {
        System.out.println("==== Listar Faturas em Aberto ====");
        System.out.print("Matrícula do Imóvel: ");
        String matricula = scanner.next();

        Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);

        if (imovel != null) {
            ArrayList<Fatura> faturasDoImovel = faturaDAO.obterFaturasPorImovel(imovel.getId());
            for (Fatura fatura : faturasDoImovel) {
                if (!fatura.isQuitado()) {
                    exibirDetalhesFatura(fatura);
                }
            }
        } else {
            System.out.println("Imóvel não encontrado.");
        }
    }

    private static void exibirDetalhesFatura(Fatura fatura) {
        System.out.println("Data de Emissão: " + fatura.getData());
        System.out.println("Última Leitura: " + fatura.getUltimaLeitura());
        System.out.println("Penúltima Leitura: " + fatura.getPenultimaLeitura());
        System.out.println("Valor: " + String.format("%.2f", fatura.getValor()));
        System.out.println("Quitada: " + (fatura.isQuitado() ? "Sim" : "Não"));
        System.out.println("--------");
    }



}
