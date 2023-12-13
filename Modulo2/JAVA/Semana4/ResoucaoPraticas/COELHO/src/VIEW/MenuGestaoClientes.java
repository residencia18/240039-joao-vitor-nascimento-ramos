package VIEW;

import MODEL.Cliente;
import MODEL.Verificacoes;
import DAO.Cliente_DAO;
import java.util.List;
import java.util.Scanner;

public abstract class MenuGestaoClientes {

    private static final Cliente_DAO clienteDAO = new Cliente_DAO();
    private static Scanner scanner = new Scanner(System.in);


    public static void exibirMenu() {
        int opcao=-1;
        do {
            System.out.println("==== Menu Gestão de Clientes ====");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Todos os Clientes");
            System.out.println("3. Buscar Cliente por cpf");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Excluir Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                    System.out.println("Por favor, insira um número inteiro válido.");
                    continue;
           }

                switch (opcao) {
                    case 1:
                        adicionarCliente();
                        break;
                    case 2:
                        listarTodosClientes();
                        break;
                    case 3:
                        buscarClientePorCPF();
                        break;
                    case 4:
                        atualizarCliente();
                        break;
                    case 5:
                        excluirCliente();
                        break;
                    case 0:
                        System.out.println("Saindo do Menu. Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

        } while (opcao != 0);
    }


    private static void adicionarCliente() {
        System.out.println("==== Adicionar Cliente ====");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if(Verificacoes.validarCPF(cpf)) {
        	Cliente c = clienteDAO.obterClientePorCPF(cpf);
        	if(c == null) {
                Cliente novoCliente = new Cliente(nome,cpf);
                clienteDAO.adicionarCliente(novoCliente);
                System.out.println("Cliente adicionado com sucesso!");
        	}else {
        		System.out.println("Já existe um cliente com esse cpf em nosso banco de dados");
        	}
        }else {
        	System.out.println("CPF inválido");
        }


    }

    private static void listarTodosClientes() {
        System.out.println("==== Listar Todos os Clientes ====");
        List<Cliente> clientes = clienteDAO.obterTodosClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
            }
        }
    }

    private static void buscarClientePorCPF() {
        System.out.println("==== Buscar Cliente por CPF ====");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        if (Verificacoes.validarCPF(cpf)) {
            Cliente cliente = clienteDAO.obterClientePorCPF(cpf);

            if (cliente != null) {
                System.out.println("Cliente encontrado:");
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } else {
            System.out.println("CPF inválido.");
        }
    }


    private static void atualizarCliente() {
        System.out.println("==== Atualizar Cliente ====");
        System.out.print("Digite o CPF do cliente que deseja atualizar: ");
        String cpf = scanner.next();

        if (Verificacoes.validarCPF(cpf)) {
            Cliente cliente = clienteDAO.obterClientePorCPF(cpf);

            if (cliente != null) {
                exibirMenuAtualizacaoCliente(cliente);
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } else {
            System.out.println("CPF inválido.");
        }
    }

    private static void exibirMenuAtualizacaoCliente(Cliente cliente) {
        int opcao;
        do {
            System.out.println("=== Opções de Atualização ===");
            System.out.println("1. Atualizar Nome");
            System.out.println("2. Atualizar CPF");
            System.out.println("0. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner = new Scanner(System.in);

            switch (opcao) {
                case 1:
                    atualizarNome(cliente);
                    break;
                case 2:
                    atualizarCPF(cliente);
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Anterior.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void atualizarNome(Cliente cliente) {
        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();
        cliente.setNome(novoNome);
        clienteDAO.atualizarCliente(cliente);
        System.out.println("Nome atualizado com sucesso!");
    }

    private static void atualizarCPF(Cliente cliente) {
        System.out.print("Novo CPF: ");
        String novoCpf = scanner.nextLine();

        if (Verificacoes.validarCPF(novoCpf)) {
            cliente.setCpf(novoCpf);
            clienteDAO.atualizarCliente(cliente);
            System.out.println("CPF atualizado com sucesso!");
        } else {
            System.out.println("Novo CPF inválido.");
        }
    }

    private static void excluirCliente() {
    	scanner = new Scanner(System.in);
        System.out.println("==== Excluir Cliente ====");
        System.out.print("Digite o CPF do cliente que deseja excluir: ");
        String cpf = scanner.next();

        if (Verificacoes.validarCPF(cpf)) {
            Cliente cliente = clienteDAO.obterClientePorCPF(cpf);

            if (cliente != null) {
                clienteDAO.excluirCliente(cliente.getId());
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } else {
            System.out.println("CPF inválido.");
        }
    }

}
