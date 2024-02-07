package br.com.cepedi.controller.menus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.cepedi.conjuntos.Clientes;
import br.com.cepedi.conjuntos.Imoveis;
import br.com.cepedi.exceptions.ConjuntoClientes.ClienteNaoEncontradoException;
import br.com.cepedi.exceptions.imovel.ImovelNaoEncontrado;
import br.com.cepedi.model.Cliente;
import br.com.cepedi.model.Endereco;
import br.com.cepedi.model.Estado;
import br.com.cepedi.model.Imovel;
import br.com.cepedi.views.MenuImoveisView;

public abstract class MenuImoveisController {

	public static void selecionarAcao(Scanner sc, Imoveis imoveis , Clientes clientes) {

		int escolha;
		
		do {
			escolha = MenuImoveisView.selecionaAcao(sc);
			
			switch(escolha) {
			case 1:
				cadastra(sc,imoveis,clientes);
				break;
			case 2:
				mostra(sc,imoveis);
				break;
			case 3:
				listaTodos(imoveis);
				break;
			case 4:
				atualizar(sc,imoveis,clientes);
				break;
			case 5:
				deletar(sc,imoveis);
				break;
			case 0:
				break;
			
			}
		}while(escolha!=0);
	}
	
	private static void cadastra(Scanner sc, Imoveis imoveis, Clientes clientes) {
		
		Imovel imovel = null;
		Cliente cliente;
		int idCliente;
		String escolhaContinue = "";
		do {
			try {
		        System.out.println("Cadastro de Imóvel");
		        System.out.println("Informe a matrícula do imóvel: ");
		        String matricula = sc.nextLine();
		        System.out.println("Informe o endereço do imóvel: ");
		        System.out.println("Rua: ");
		        String rua = sc.nextLine();
		        System.out.println("Número: ");
		        int numero = Integer.parseInt(sc.nextLine());
		        System.out.println("Bairro: ");
		        String bairro = sc.nextLine();
		        System.out.println("Cidade: ");
		        String cidade = sc.nextLine();
		        System.out.println("Estado: ");
		        Estado estado = selecionaEstado(sc);
		        System.out.println("CEP: ");
		        String cep = sc.nextLine();
		        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
		        imovel = new Imovel(matricula, endereco);
		        System.out.println("---------------------------");
		        System.out.println("Clientes disponiveis");
		        clientes.listar();
		        System.out.println("---------------------------");
		        System.out.println("Digite o id do cliente que é propritario imovel");
		        idCliente = Integer.parseInt(sc.nextLine());
		        Cliente c = clientes.buscar(idCliente);	
		        imovel.setProprietario(c);
		        c.adicionaImovelNaLista(imovel);
		        imoveis.adicionar(imovel);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar o cadastro novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			break;
			
		}while(true);
		


        System.out.println("Imóvel cadastrado com sucesso!");
    }
	
	private static void mostra(Scanner sc , Imoveis imoveis) {
		Imovel imovel;
		int id;
		String escolhaContinue = "";
		do {
			try {
				System.out.println("Digite o id do imovel que deseja buscar");
				id = Integer.parseInt(sc.nextLine());
				imovel = imoveis.buscar(id);
			}catch(Exception e ) {
				System.out.println(e.getMessage());
				System.out.println("Deseja tentar a busca novamente (0 - para parar , qualquer outra tecla para continuar) ");
				escolhaContinue = sc.nextLine();
				if(escolhaContinue.equals("0")) {
					return;
				}
				continue;
			}
			
			System.out.println(imovel);
			break;
			
		}while(true);
	}
	
	private static void listaTodos(Imoveis imoveis){
		imoveis.listar();
	}
	
	private static void atualizar(Scanner sc, Imoveis imoveis , Clientes clientes) {
	    int id;
	    String escolhaContinue = "";
	    Imovel imovel;

	    do {
	        try {
	            System.out.println("Digite o ID do imóvel que deseja atualizar: ");
	            id = Integer.parseInt(sc.nextLine());
	            imovel = imoveis.buscar(id);
	            if (imovel != null) {
	                break;
	            } else {
	                System.out.println("Imóvel não encontrado. Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
	                escolhaContinue = sc.nextLine();
	                if (escolhaContinue.equals("0")) {
	                    return;
	                }
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("Deseja tentar novamente? (0 para parar, qualquer outra tecla para continuar)");
	            escolhaContinue = sc.nextLine();
	            if (escolhaContinue.equals("0")) {
	                return;
	            }
	        }
	    } while (true);

	    do {
	        try {
	            System.out.println("Selecione o que deseja atualizar:");
	            System.out.println("1. Matrícula");
	            System.out.println("2. Endereço");
	            System.out.println("3. Relógio");
	            System.out.println("4. Proprietário");
	            int opcao = Integer.parseInt(sc.nextLine());

	            switch (opcao) {
	                case 1:
	                	atualizaMatricula(sc, imoveis, id);
	                    break;
	                case 2:
	                	atualizaEndereco(sc, imoveis, id, imovel);
	                    break;
	                case 3:
	                	atualizaRelogio(sc, imovel);
	                    break;
	                case 4:
	                	atualizaCliente(sc, imoveis, clientes, id, imovel);
	                    break;
	                default:
	                    System.out.println("Opção inválida.");
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
	
	private static void deletar(Scanner sc, Imoveis imoveis) {
	    int id;
	    String escolhaContinue = "";
	    Imovel imovel = null;

	    do {
	        try {
	            System.out.println("Digite o ID do imóvel que deseja deletar: ");
	            id = Integer.parseInt(sc.nextLine());
	            imovel = imoveis.buscar(id);
		        Cliente proprietario = imovel.getProprietario();
		        proprietario.removeImovelNaLista(imovel); 
		        imoveis.deletar(id); 
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
	



	private static void atualizaCliente(Scanner sc, Imoveis imoveis, Clientes clientes, int id, Imovel imovel)
			throws Exception, ClienteNaoEncontradoException, ImovelNaoEncontrado {
		int idCliente;
		System.out.println("---------------------------");
		System.out.println("Clientes disponiveis");
		clientes.listar();
		System.out.println("---------------------------");
		System.out.println("Digite o id do novo propritario do imovel");
		idCliente = Integer.parseInt(sc.nextLine());
		if(idCliente==imovel.getProprietario().getId()) {
			throw new Exception("id : " + idCliente + " já é do cliente atual do imovel");
		}
		Cliente c = clientes.buscar(idCliente);	
		imoveis.atualizar(id, c);
	}

	private static void atualizaRelogio(Scanner sc, Imovel imovel) {
		System.out.println("Selecione qual leitura deseja atualizar:");
		System.out.println("1. Última leitura");
		System.out.println("2. Leitura atual");
		int escolhaLeitura = Integer.parseInt(sc.nextLine());
		
		switch (escolhaLeitura) {
		    case 1:
		        System.out.println("Informe a nova valor para a última leitura: ");
		        BigDecimal novaUltimaLeitura = new BigDecimal(sc.nextLine());
		        imovel.getRelogio().setUltimaLeitura(novaUltimaLeitura);
		        System.out.println("Última leitura atualizada com sucesso!");
		        break;
		    case 2:
		        System.out.println("Informe a nova valor para a leitura atual: ");
		        BigDecimal novaLeituraAtual = new BigDecimal(sc.nextLine());
		        imovel.getRelogio().setLeituraAtual(novaLeituraAtual);
		        System.out.println("Leitura atualizada com sucesso!");
		        break;
		    default:
		        System.out.println("Opção inválida.");
		}
	}

	private static void atualizaEndereco(Scanner sc, Imoveis imoveis, int id, Imovel imovel)
			throws ImovelNaoEncontrado {
		System.out.println("Informe o novo endereço: ");
		System.out.println("Rua: ");
		String rua = sc.nextLine();
		System.out.println("Número: ");
		int numero = Integer.parseInt(sc.nextLine());
		System.out.println("Bairro: ");
		String bairro = sc.nextLine();
		System.out.println("Cidade: ");
		String cidade = sc.nextLine();
		System.out.println("Estado: ");
		Estado estado = selecionaEstado(sc);
		System.out.println("CEP: ");
		String cep = sc.nextLine();
		Endereco novoEndereco = new Endereco();
		novoEndereco.setRua(rua);
		novoEndereco.setNumero(numero);
		novoEndereco.setBairro(bairro);
		novoEndereco.setCidade(cidade);
		novoEndereco.setEstado(estado);
		novoEndereco.setCEP(cep);
		novoEndereco.setId(imovel.getEndereco().getId());
		imoveis.atualizar(id, novoEndereco);
		System.out.println("Endereço atualizado com sucesso!");
	}

	private static void atualizaMatricula(Scanner sc, Imoveis imoveis, int id) throws ImovelNaoEncontrado {
		System.out.println("Informe a nova matrícula: ");
		String novaMatricula = sc.nextLine();
		imoveis.atualizar(id, novaMatricula);
	}



	private static Estado selecionaEstado(Scanner sc) {
	    System.out.println("Escolha o estado: ");
	    Estado[] estados = Estado.values();
	    for (int i = 0; i < estados.length; i++) {
	        System.out.println((i + 1) + ". " + estados[i]);
	    }
	    int escolha = Integer.parseInt(sc.nextLine());

	    if (escolha < 1 || escolha > estados.length) {
	        throw new IllegalArgumentException("Escolha um número dentro do range válido.");
	    }

	    return estados[escolha - 1];
	}

	
	
}
