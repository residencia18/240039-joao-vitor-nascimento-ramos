//package View;
//
//import java.time.LocalDate;
//import java.util.Scanner;
//
//import DAO.Fatura_DAO;
//import DAO.Pagamento_DAO;
//import MODEL.Fatura;
//import MODEL.Pagamento;
//import MODEL.Reembolso;
//
//public abstract class MenuGestaoPagamentos {
//
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final Fatura_DAO faturaDAO = new Fatura_DAO();
//    private static final Pagamento_DAO pagamentoDAO = new Pagamento_DAO();
//
//    public static void exibirMenu() {
//        int opcao;
//        do {
//            System.out.println("==== Menu Pagamentos ====");
//            System.out.println("1. Incluir Pagamento");
//            System.out.println("2. Listar Todos os Pagamentos");
//            System.out.println("3. Listar Pagamentos de uma Fatura");
//            System.out.println("4. Listar Todos os Reembolsos");
//            System.out.println("0. Voltar ao Menu Anterior");
//            System.out.print("Escolha uma opção: ");
//            opcao = scanner.nextInt();
//
//            switch (opcao) {
//                case 1:
//                    incluirPagamento();
//                    break;
//                case 2:
//                    listarTodosPagamentos();
//                    break;
//                case 3:
//                    listarPagamentosDeUmaFatura();
//                    break;
//                case 4:
//                    listarTodosReembolsos();
//                    break;
//                case 0:
//                    System.out.println("Voltando ao Menu Anterior.");
//                    break;
//                default:
//                    System.out.println("Opção inválida. Tente novamente.");
//            }
//        } while (opcao != 0);
//    }
//
//    private static void incluirPagamento() {
//        System.out.println("==== Incluir Pagamento ====");
//        System.out.print("id da Fatura: ");
//        int numeroFatura = scanner.nextInt();
//
//        Fatura fatura = faturaDAO.obterFaturaPorNumero(numeroFatura);
//
//        if (fatura != null && !fatura.isQuitado()) {
//            System.out.print("Valor do Pagamento: ");
//            double valorPagamento = scanner.nextDouble();
//
//            // Verifica se o pagamento excede o valor da fatura
//            if (valorPagamento >= fatura.getValor()) {
//                // Gera um reembolso
//                double valorReembolso = valorPagamento - fatura.getValor();
//                Reembolso reembolso = new Reembolso(valorReembolso, LocalDate.now());
//                System.out.println("Reembolso gerado: Valor " + valorReembolso + " em " + LocalDate.now());
//            }
//
//            // Registra o pagamento
//            Pagamento pagamento = new Pagamento(valorPagamento, LocalDate.now(), fatura);
//            pagamentoDAO.adicionarPagamento(pagamento);
//
//            // Atualiza o status da fatura se totalmente quitada
//            if (pagamentoDAO.verificarFaturaQuitada(fatura.getId())) {
//                fatura.setQuitada(true);
//                faturaDAO.atualizarFatura(fatura);
//                System.out.println("Fatura totalmente quitada!");
//            } else {
//                System.out.println("Pagamento registrado com sucesso!");
//            }
//        } else if (fatura != null && fatura.isQuitada()) {
//            System.out.println("Esta fatura já está quitada. Não é possível realizar mais pagamentos.");
//        } else {
//            System.out.println("Fatura não encontrada.");
//        }
//    }
//
//
//    
//    private static void listarTodosPagamentos() {
//        System.out.println("==== Listar Todos os Pagamentos ====");
//        for (Pagamento pagamento : pagamentoDAO.obterTodosPagamentos()) {
//            exibirDetalhesPagamento(pagamento);
//        }
//    }
//
//    private static void listarPagamentosDeUmaFatura() {
//        System.out.println("==== Listar Pagamentos de uma Fatura ====");
//        System.out.print("Número da Fatura: ");
//        int numeroFatura = scanner.nextInt();
//
//        Fatura fatura = faturaDAO.obterFaturaPorNumero(numeroFatura);
//
//        if (fatura != null) {
//            for (Pagamento pagamento : pagamentoDAO.obterPagamentosPorFatura(fatura.getId())) {
//                exibirDetalhesPagamento(pagamento);
//            }
//        } else {
//            System.out.println("Fatura não encontrada.");
//        }
//    }
//
//    private static void listarTodosReembolsos() {
//        System.out.println("==== Listar Todos os Reembolsos ====");
//        for (Reembolso reembolso : pagamentoDAO.obterTodosReembolsos()) {
//            exibirDetalhesReembolso(reembolso);
//        }
//    }
//
//    private static void exibirDetalhesPagamento(Pagamento pagamento) {
//        System.out.println("Data do Pagamento: " + pagamento.getData());
//        System.out.println("Valor do Pagamento: " + pagamento.getValor());
//        System.out.println("Número da Fatura: " + pagamento.getFatura().getNumero());
//        System.out.println("--------");
//    }
//
//    private static void exibirDetalhesReembolso(Reembolso reembolso) {
//        System.out.println("Data do Reembolso: " + reembolso.getData());
//        System.out.println("Valor do Reembolso: " + reembolso.getValor());
//        System.out.println("--------");
//    }
//
//    public static void main(String[] args) {
//        // Exemplo de uso
//        exibirMenu();
//    }
//}
package VIEW;


