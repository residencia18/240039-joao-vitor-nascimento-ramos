package VIEW;

import java.util.List;
import java.util.Scanner;

import DAO.Cliente_DAO;
import DAO.Imovel_DAO;
import DAO.Leitura_DAO;
import MODEL.Cliente;
import MODEL.Imovel;
import MODEL.Leitura;
import MODEL.Verificacoes;

public abstract class MenuGestaoImoveis {

    private static final Cliente_DAO clienteDAO = new Cliente_DAO();
    private static final Imovel_DAO imovelDAO = new Imovel_DAO();
	private static final Leitura_DAO leituraDAO = new Leitura_DAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("==== Menu Gestão de Imóveis ====");
            System.out.println("1. Adicionar Imóvel");
            System.out.println("2. Listar Todos os Imóveis");
            System.out.println("3. Buscar Imóvel por ID");
            System.out.println("4. Atualizar Imóvel");
            System.out.println("5. Excluir Imóvel");
			System.out.println("6. Realizar Leituras");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarImovel();
                    break;
                case 2:
                    listarTodosImoveis();
                    break;
                case 3:
                	buscarImovelPorMatricula();
                    break;
                case 4:
                    atualizarImovel();
                    break;
                case 5:
                    excluirImovel();
                    break;
				case 6:
					realizarLeituras();
					break;
                case 0:
                    System.out.println("Saindo do Menu. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    private static void adicionarImovel() {
        System.out.println("==== Adicionar Imóvel ====");
        System.out.print("Matrícula: ");
        String matricula = scanner.next();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();  // Consumir a quebra de linha pendente
        endereco = scanner.nextLine();  // Agora obter a entrada correta para o endereço
                
        System.out.println("Qual CPF do cliente dono do imóvel? ");
        String cpf = scanner.next();

        if (Verificacoes.validarCPF(cpf)) {
            Cliente cliente = clienteDAO.obterClientePorCPF(cpf);

            if (cliente != null) {
                Imovel novoImovel = new Imovel(matricula, endereco, cliente);
                // Utiliza o método que verifica se já existe um imóvel com a mesma matrícula
                if (imovelDAO.obterImovelPorMatricula(matricula) == null) {
                	cliente.adicionarImovel(novoImovel);
					imovelDAO.adicionarImovel(novoImovel);
                    System.out.println("Imóvel adicionado com sucesso!");
                } else {
                    System.out.println("Já existe um imóvel com a mesma matrícula.");
                }
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } else {
            System.out.println("CPF inválido.");
        }
    }

    private static void listarTodosImoveis() {
        System.out.println("==== Listar Todos os Imóveis ====");
        List<Imovel> imoveis = imovelDAO.obterTodosImoveis();

        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
        } else {
            for (Imovel imovel : imoveis) {
                System.out.println("ID: " + imovel.getId() + ", Matrícula: " + imovel.getMatricula()
                        + ", Endereço: " + imovel.getEndereco() + ", Última Leitura: " + imovel.getUltimaLeitura()
                        + ", Penúltima Leitura: " + imovel.getPenultimaLeitura());
            }
        }
    }

    private static void buscarImovelPorMatricula() {
        System.out.println("==== Buscar Imóvel por Matrícula ====");
        System.out.print("Digite a Matrícula do imóvel: ");
        String matricula = scanner.next();

        Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);

        if (imovel != null) {
            System.out.println("Imóvel encontrado:");
            System.out.println("Matrícula: " + imovel.getMatricula()
                    + ", Endereço: " + imovel.getEndereco() + ", Última Leitura: " + imovel.getUltimaLeitura()
                    + ", Penúltima Leitura: " + imovel.getPenultimaLeitura());
        } else {
            System.out.println("Imóvel não encontrado.");
        }
    }

    private static void atualizarImovel() {
        System.out.println("==== Buscar Imóvel por Matrícula ====");
        System.out.print("Digite a Matrícula do imóvel: ");
        String matricula = scanner.next();

        Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);

        if (imovel != null) {
            exibirMenuAtualizacaoImovel(imovel);
        } else {
            System.out.println("Imóvel não encontrado.");
        }
    }

    private static void exibirMenuAtualizacaoImovel(Imovel imovel) {
        int opcao;
        do {
            System.out.println("=== Opções de Atualização ===");
            System.out.println("1. Atualizar Matrícula");
            System.out.println("2. Atualizar Endereço");
            System.out.println("3. Atualizar Última Leitura");
            System.out.println("4. Atualizar Penúltima Leitura");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    atualizarMatricula(imovel);
                    break;
                case 2:
                    atualizarEndereco(imovel);
                    break;
                case 3:
                    atualizarUltimaLeitura(imovel);
                    break;
                case 4:
                    atualizarPenultimaLeitura(imovel);
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Anterior.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void atualizarMatricula(Imovel imovel) {
        System.out.print("Nova Matrícula: ");
        String novaMatricula = scanner.next();
        imovel.setMatricula(novaMatricula);
        imovelDAO.atualizarImovel(imovel);
        System.out.println("Matrícula atualizada com sucesso!");
    }

    private static void atualizarEndereco(Imovel imovel) {
        System.out.print("Novo Endereço: ");
        String novoEndereco = scanner.nextLine();  // Consumir a quebra de linha pendente
        novoEndereco = scanner.nextLine();  // Obter a entrada correta para o endereço
        imovel.setEndereco(novoEndereco);
        imovelDAO.atualizarImovel(imovel);
        System.out.println("Endereço atualizado com sucesso!");
    }

    private static void atualizarUltimaLeitura(Imovel imovel) {
        System.out.print("Nova Última Leitura: ");
        int novaUltimaLeitura = scanner.nextInt();
        imovel.setUltimaLeitura(novaUltimaLeitura);
        imovelDAO.atualizarImovel(imovel);
        System.out.println("Última Leitura atualizada com sucesso!");
    }

    private static void atualizarPenultimaLeitura(Imovel imovel) {
        System.out.print("Nova Penúltima Leitura: ");
        int novaPenultimaLeitura = scanner.nextInt();
        imovel.setPenultimaLeitura(novaPenultimaLeitura);
        imovelDAO.atualizarImovel(imovel);
        System.out.println("Penúltima Leitura atualizada com sucesso!");
    }

    private static void excluirImovel() {
        System.out.println("==== Excluir Imóvel ====");
        System.out.print("Digite a Matrícula do imóvel que deseja excluir: ");
        String matricula = scanner.next();

        Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);
        Cliente clienteDono = imovel.getCliente();
        if (imovel != null) {
        	clienteDono.removerImovel(imovel);
        	clienteDAO.atualizarCliente(clienteDono);
            imovelDAO.excluirImovelPorMatricula(matricula);
            System.out.println("Imóvel excluído com sucesso!");
        }else{
            System.out.println("Imóvel não encontrado.");
        }
    }
    
    private static void realizarLeituras() {
    	System.out.println("==== Realizar Leitura de Imóvel ====");
        System.out.print("Digite a Matrícula do imóvel que deseja Fazer a Leitura: ");
        String matricula = scanner.next();
        
		Imovel imovel = imovelDAO.obterImovelPorMatricula(matricula);
		if (imovel != null) {
			Leitura leitura = new Leitura();
			leitura.setId_imovel(imovel.getId());
			System.out.print("Digite o valor da Leitura: ");
	        int resposta = scanner.nextInt();
	        leitura.setLeitura(resposta);
	        leituraDAO.adicionarLeitura(leitura);
	        imovel.addLeitura(leitura);
	        imovelDAO.atualizarImovel(imovel);
		}
    }

}
