package br.com.cepedi.controller.menus;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.cepedi.conjuntos.Imoveis;
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
                imovel.getFaturas().stream()
                        .filter(fatura -> !fatura.isQuitado())
                        .forEach(System.out::println);

                System.out.println("Digite o id da fatura que deseja pagar : ");
                int idFatura = Integer.parseInt(sc.nextLine());
                Fatura fatura = imovel.buscarFaturaEmAberto(idFatura);
                System.out.println("O valor que falta de pagamento é " + fatura.getValor());
                System.out.println("Digite o valor de pagamento!");
                BigDecimal valorPago = new BigDecimal(sc.nextLine());
                fatura.registraPagamento(valorPago);
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
                Fatura fatura = imovel.buscarFatura(idFatura);
                System.out.println("Pagamentos da fatura:");
                fatura.getPagamentos().forEach(System.out::println);
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

                Fatura fatura = imovel.getFaturas().stream()
                                    .filter(f -> f.getId() == idFatura)
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException("Fatura não encontrada."));

                System.out.println("Pagamento com reembolso da fatura " + idFatura + ":");

                long countReembolso = fatura.getPagamentos().stream()
                                        .filter(p -> p.getReembolso() != null)
                                        .count();

               if (countReembolso == 0) {
                    System.out.println("Aviso: Nenhum pagamento com reembolso encontrado para esta fatura.");
                } else {
                    fatura.getPagamentos().stream()
                            .filter(p -> p.getReembolso() != null)
                            .forEach(System.out::println);
                }

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
