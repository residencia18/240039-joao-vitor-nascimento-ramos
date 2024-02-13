package br.com.cepedi.controller.menus;

import java.math.BigDecimal;
import java.util.Scanner;

import br.com.cepedi.conjuntos.Imoveis;
import br.com.cepedi.dao.FaturaDAO;
import br.com.cepedi.dao.PagamentoDAO;
import br.com.cepedi.model.Fatura;
import br.com.cepedi.model.Imovel;
import br.com.cepedi.views.MenuPagamentosView;

public abstract class MenuPagamentosController {

    public static void selecionarAcao(Scanner sc, Imoveis imoveis) {

        int escolha;

        do {
            escolha = MenuPagamentosView.selecionaAcao(sc);

            switch (escolha) {
                case 1:
                    registraPagamento(sc, imoveis);
                    break;
                case 2:
                    listaPagamentosDeUmaFatura(sc, imoveis);
                    break;
                case 3:
                    listaPagamentoComReembolso(sc, imoveis);
                    break;
                case 0:
                    break;
            }
        } while (escolha != 0);
    }

    private static void registraPagamento(Scanner sc, Imoveis imoveis) {
        int id;
        String escolhaContinue = "";
        Imovel imovel = null;

        do {
            try {
                System.out.println("Digite o ID do imóvel que deseja registrar um novo pagamento de fatura : ");
                id = Integer.parseInt(sc.nextLine());
                imovel = imoveis.buscar(id);
                System.out.println("---------------------------");
                System.out.println("Faturas em aberto do imóvel:");
                FaturaDAO.listarFaturasEmAbertoPorIdImovel(id).forEach(System.out::println);

                System.out.println("Digite o id da fatura que deseja pagar : ");
                int idFatura = Integer.parseInt(sc.nextLine());
                Fatura fatura = FaturaDAO.buscarFaturaPorId(idFatura);
                System.out.println("O valor que falta de pagamento é " + fatura.getValor());
                System.out.println("Digite o valor de pagamento!");
                BigDecimal valorPago = new BigDecimal(sc.nextLine());
                fatura.registraPagamento(valorPago);
                FaturaDAO.atualizarFatura(fatura);
                break;
            } catch (Exception  e) {
                System.out.println(e.getMessage());
                System.out.println("Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
                escolhaContinue = sc.nextLine();
                if (escolhaContinue.equals("0")) {
                    return;
                }
            }
        } while (true);
    }

    private static void listaPagamentosDeUmaFatura(Scanner sc, Imoveis imoveis) {
        int id;
        String escolhaContinue = "";
        Imovel imovel = null;

        do {
            try {
                System.out.println("Digite o ID do imóvel para listar os pagamentos : ");
                id = Integer.parseInt(sc.nextLine());
                imovel = imoveis.buscar(id);
                System.out.println("---------------------------");
                System.out.println("Digite o id da fatura que visualizar pagamentos : ");
                int idFatura = Integer.parseInt(sc.nextLine());
                Fatura fatura = FaturaDAO.buscarFaturaPorId(idFatura);
                System.out.println("Pagamentos da fatura:");
                PagamentoDAO.listarPagamentosPorFatura(idFatura).forEach(System.out::println);
                break;
            } catch (Exception  e) {
                System.out.println(e.getMessage());
                System.out.println("Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
                escolhaContinue = sc.nextLine();
                if (escolhaContinue.equals("0")) {
                    return;
                }
            }
        } while (true);
    }
    private static void listaPagamentoComReembolso(Scanner sc, Imoveis imoveis) {
        int id;
        String escolhaContinue = "";
        Imovel imovel = null;

        do {
            try {
                System.out.println("Digite o ID do imóvel para listar os pagamentos com reembolso : ");
                id = Integer.parseInt(sc.nextLine());
                imovel = imoveis.buscar(id);
                System.out.println("---------------------------");
                System.out.println("Digite o id da fatura para visualizar os pagamentos com reembolso : ");
                int idFatura = Integer.parseInt(sc.nextLine());

                Fatura fatura = FaturaDAO.buscarFaturaPorId(idFatura);

                System.out.println("Pagamento com reembolso da fatura " + idFatura + ":");

                PagamentoDAO.listarPagamentosComReembolsos(idFatura).forEach(System.out::println);
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
